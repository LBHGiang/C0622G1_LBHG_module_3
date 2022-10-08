package furama_resort.repository.impl;

import furama_resort.model.Customer;
import furama_resort.model.DatabaseConnection;
import furama_resort.model.PrintSQLException;
import furama_resort.repository.ICustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository implements ICustomerRepository {

    private static final String INSERT_CUSTOMER = "insert into customer(name, date_of_birth, gender, id_card," +
            "phone_number, email, address, customer_type_id) values(?,?,?,?,?,?,?,?);";
    private static final String SELECT_CUSTOMER_BY_ID = "select * from customer where id = ? and is_delete = 0;";
    private static final String SELECT_ALL_CUSTOMER = "select * from data_furama.customer where is_delete = 0;";
    private static final String SELECT_ALL_CUSTOMER_ORDER_BY_NAME = "select * from customer where is_delete = 0 order by name;";
    private static final String DELETE_CUSTOMER = "update customer set is_delete = 1 where id = ?;";
    private static final String UPDATE_CUSTOMER = "update customer set name = ?, date_of_birth = ?, gender = ?, id_card = ?, phone_number = ?, email = ?, address = ?, customer_type_id = ? where id = ? and is_delete = 0;";
    private static final String SELECT_CUSTOMER_TYPE = "SELECT * FROM data_furama.customer_type where is_delete = 0;";
    private static final String SEARCH_NAME_CUSTOMER_TYPE = "SELECT * FROM data_furama.customer where is_delete=0 and name like ? and customer_type_id = ?; ";
    private static final String SEARCH_NAME = "SELECT * FROM data_furama.customer where is_delete=0 and name like ?;";

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Customer customer = getCustomer(rs);
                customers.add(customer);
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER)) {

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getBirthday());
            preparedStatement.setInt(3, customer.getGender());
            preparedStatement.setString(4, customer.getIdCard());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setString(7, customer.getAddress());
            preparedStatement.setInt(8, customer.getCustomerTypeId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                customer = getCustomer(rs);
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return customer;
    }

    @Override
    public void update(int id, Customer customer) {
        try {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);) {
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getBirthday());
                preparedStatement.setInt(3, customer.getGender());
                preparedStatement.setString(4, customer.getIdCard());
                preparedStatement.setString(5, customer.getPhoneNumber());
                preparedStatement.setString(6, customer.getEmail());
                preparedStatement.setString(7, customer.getAddress());
                preparedStatement.setInt(8, customer.getCustomerTypeId());
                preparedStatement.setInt(9, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void remove(int id) {
        try {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Customer> searchCustomer(String searchName, int searchCustomerTypeId) {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NAME_CUSTOMER_TYPE);) {
            preparedStatement.setString(1, searchName);
            preparedStatement.setInt(2, searchCustomerTypeId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Customer customer = getCustomer(rs);
                customers.add(customer);
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return customers;
    }

    private Customer getCustomer(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String birthday = rs.getString("date_of_birth");
        int gender = rs.getInt("gender");
        String idCard = rs.getString("id_card");
        String phoneNumber = rs.getString("phone_number");
        String email = rs.getString("email");
        String address = rs.getString("address");
        int customerTypeId = rs.getInt("customer_type_id");

        return new Customer(id, name, birthday, gender, idCard, phoneNumber, email, address, customerTypeId);
    }

    @Override
    public List<Customer> searchCustomer(String searchName) {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NAME);) {
            preparedStatement.setString(1, searchName);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Customer customer = getCustomer(rs);
                customers.add(customer);
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return customers;
    }

    public Map<Integer, String> getCustomerType() {
        Map<Integer, String> customerType = new HashMap<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_TYPE);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                customerType.put(id, name);
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return customerType;
    }

    public Map<Integer, String> getGender() {
        Map<Integer, String> gender = new HashMap<>();
        gender.put(0, "Nữ");
        gender.put(1, "Nam");
        return gender;
    }

}

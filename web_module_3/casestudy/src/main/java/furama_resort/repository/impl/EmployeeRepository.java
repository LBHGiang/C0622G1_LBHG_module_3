package furama_resort.repository.impl;

import furama_resort.model.Employee;
import furama_resort.repository.IEmployeeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepository implements IEmployeeRepository {
    private String jdbcURL = "jdbc:mysql://localhost:3306/data_furama?useSSL=false";
    private String jdbcEmployeename = "root";
    private String jdbcPassword = "hellomodule3";

    private static final String INSERT_EMPLOYEE = "insert into employee(name, date_of_birth, id_card, " +
            "salary, phone_number, email, address, position_id, education_degree_id, " +
            "division_id) values(?,?,?,?,?,?,?,?,?,?);";
    private static final String SELECT_EMPLOYEE_BY_ID = "select * from employee where id = ? and is_delete = 0;";
    private static final String SELECT_ALL_EMPLOYEE = "select * from employee where is_delete = 0;";
    private static final String SELECT_ALL_EMPLOYEE_ORDER_BY_NAME = "select * from employee where is_delete = 0 order by name;";
    //    private static final String SELECT_EMPLOYEE_BY_COUNTRY = "select * from employees where country like ?;";
    private static final String DELETE_EMPLOYEE = "update employee set is_delete = 1 where id = ?;";
    private static final String UPDATE_EMPLOYEE = "update employee set name = ?, date_of_birth = ?, id_card = ?, salary = ?, phone_number = ?, email = ?, address = ?, position_id = ?, education_degree_id = ?, division_id = ? where id = ? and is_delete = 0;";

    private static final String SELECT_POSITION = "SELECT * FROM data_furama.position where is_delete = 0;";
    private static final String SELECT_EDUCATION = "SELECT * FROM data_furama.education_degree where is_delete=0;";
    private static final String SELECT_DIVISION = "SELECT * FROM data_furama.division where is_delete=0;";

    private static final String SEARCH_NAME_COST_TYPE = "SELECT * FROM data_furama.employee where is_delete=0 and name like ? and cost < ? and employee_type_id = ? ;";
    private static final String SEARCH_NAME_COST_ALL_TYPE = "SELECT * FROM data_furama.employee where is_delete=0 and name like ? and cost < ?";


    public List<Employee> findEmployeeByType(String query) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int area = rs.getInt("area");
                int maxPeople = rs.getInt("max_people");
                int floors = rs.getInt("number_of_floors");
                int rentTypeId = rs.getInt("rent_type_id");
                int employeeTypeId = rs.getInt("employee_type_id");

                double cost = rs.getDouble("cost");
                double poolArea = rs.getDouble("pool_area");

                String name = rs.getString("name");
                String description = rs.getString("description_other_convenience");
                String employeeFree = rs.getString("employee_free");
                String standard = rs.getString("standard_room");

//                employees.add(new Employee(id, name, area, cost, maxPeople, rentTypeId, employeeTypeId, standard, description, poolArea, floors, employeeFree));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employees;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEE);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String birthday = rs.getString("date_of_birth");
                String idCard = rs.getString("id_card");
                double salary = rs.getDouble("salary");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int positionId = rs.getInt("position_id");
                int educationDegreeId = rs.getInt("education_degree_id");
                int divisionId = rs.getInt("division_id");

                Employee employee = new Employee(id, name, birthday, idCard, salary, phoneNumber, email, address, positionId, educationDegreeId, divisionId);
                employees.add(employee);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employees;
    }

    @Override
    public void save(Employee employee) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE)) {

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getBirthday());
            preparedStatement.setString(3, employee.getIdCard());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setString(6, employee.getEmail());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setInt(8, employee.getPositionId());
            preparedStatement.setInt(9, employee.getEducationDegreeId());
            preparedStatement.setInt(10, employee.getDivisionId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Employee findById(int id) {
        Employee employee = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String birthday = rs.getString("date_of_birthday");
                String idCard = rs.getString("id_card");
                double salary = rs.getInt("salary");
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int positionId = rs.getInt("position_id");
                int educationDegreeId = rs.getInt("education_degree_id");
                int divisionId = rs.getInt("division_id");

                employee = new Employee(id, name, birthday, idCard, salary, phoneNumber, email, address, positionId, educationDegreeId, divisionId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employee;
    }

    @Override
    public void update(int id, Employee employee) {
        try {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE);) {
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setString(2, employee.getBirthday());
                preparedStatement.setString(3, employee.getIdCard());
                preparedStatement.setDouble(4, employee.getSalary());
                preparedStatement.setString(5, employee.getPhoneNumber());
                preparedStatement.setString(6, employee.getEmail());
                preparedStatement.setString(7, employee.getAddress());
                preparedStatement.setInt(8, employee.getPositionId());
                preparedStatement.setInt(9, employee.getEducationDegreeId());
                preparedStatement.setInt(10, employee.getDivisionId());
                preparedStatement.setInt(11, employee.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void remove(int id) {
        try {
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE);) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Employee> searchEmployee(String searchName, double searchCost, int searchServiceTypeId) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NAME_COST_TYPE);) {
            preparedStatement.setString(1, searchName);
            preparedStatement.setDouble(2, searchCost);
            preparedStatement.setDouble(3, searchServiceTypeId);

            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                int area = rs.getInt("area");
//                int maxPeople = rs.getInt("max_people");
//                int floors = rs.getInt("number_of_floors");
//                int rentTypeId = rs.getInt("rent_type_id");
//                int employeeTypeId = rs.getInt("employee_type_id");
//
//                double cost = rs.getDouble("cost");
//                double poolArea = rs.getDouble("pool_area");
//
//                String name = rs.getString("name");
//                String description = rs.getString("description_other_convenience");
//                String employeeFree = rs.getString("employee_free");
//                String standard = rs.getString("standard_room");

//                Employee employee = new Employee(id, name, birthday, idCard, salary, phoneNumber, email, address, positionId, educationDegreeId, divisionId);
//                employees.add(employee);

//            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employees;
    }

    public List<Employee> searchEmployee(String searchName, double searchCost) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NAME_COST_ALL_TYPE);) {
            preparedStatement.setString(1, searchName);
            preparedStatement.setDouble(2, searchCost);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int area = rs.getInt("area");
                int maxPeople = rs.getInt("max_people");
                int floors = rs.getInt("number_of_floors");
                int rentTypeId = rs.getInt("rent_type_id");
                int employeeTypeId = rs.getInt("employee_type_id");

                double cost = rs.getDouble("cost");
                double poolArea = rs.getDouble("pool_area");

                String name = rs.getString("name");
                String description = rs.getString("description_other_convenience");
                String employeeFree = rs.getString("employee_free");
                String standard = rs.getString("standard_room");

//                employees.add(new Employee(id, name, area, cost, maxPeople, rentTypeId, employeeTypeId, standard, description, poolArea, floors, employeeFree));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employees;
    }

    public Map<Integer, String> findPosition() {
        Map<Integer, String> rentType = new HashMap<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POSITION);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                rentType.put(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rentType;
    }

    public Map<Integer, String> findEducation() {
        Map<Integer, String> rentType = new HashMap<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EDUCATION);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                rentType.put(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rentType;
    }

    public Map<Integer, String> findDivision() {
        Map<Integer, String> rentType = new HashMap<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIVISION);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                rentType.put(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rentType;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcEmployeename, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

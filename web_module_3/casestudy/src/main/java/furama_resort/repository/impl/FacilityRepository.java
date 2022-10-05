package furama_resort.repository.impl;

import furama_resort.model.Facility;
import furama_resort.repository.IFacilityRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacilityRepository implements IFacilityRepository {
    private String jdbcURL = "jdbc:mysql://localhost:3306/login_data?useSSL=false";
    private String jdbcFacilityname = "root";
    private String jdbcPassword = "hellomodule3";

    private static final String INSERT_FACILITY_SQL = "INSERT INTO facilitys (name, email, country) VALUES (?, ?, ?);";
    private static final String SELECT_FACILITY_BY_ID = "select id,name,email,country from facilitys where id =?";
    private static final String SELECT_ALL_FACILITY = "select * from facility where is_delete = 0;";
    private static final String SELECT_ALL_VILLA = "select * from facility where is_delete = 0 and facility_type_id = 1;";
    private static final String SELECT_ALL_HOUSE = "select * from facility where is_delete = 0 and facility_type_id = 2;";
    private static final String SELECT_ALL_ROOM = "select * from facility where is_delete = 0 and facility_type_id = 3;";
    private static final String SELECT_ALL_FACILITY_ORDER_BY_NAME = "select * from facility where is_delete = 0 order by name;";
    private static final String SELECT_FACILITY_BY_COUNTRY = "select * from facilitys where country like ?;";
    private static final String DELETE_FACILITY_SQL = "delete from facilitys where id = ?;";
    private static final String UPDATE_FACILITY_SQL = "update facilitys set name = ?,email= ?, country =? where id = ?;";

    public List<Facility> findFacilityByType(String query) {
        List<Facility> facilitys = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int area = rs.getInt("area");
                int maxPeople = rs.getInt("max_people");
                int floors = rs.getInt("number_of_floors");
                int rentTypeId = rs.getInt("rent_type_id");
                int facilityTypeId = rs.getInt("facility_type_id");

                double cost = rs.getDouble("cost");
                double poolArea = rs.getDouble("pool_area");

                String name = rs.getString("name");
                String description = rs.getString("description_other_convenience");
                String facilityFree = rs.getString("facility_free");
                String standard = rs.getString("standard_room");

                facilitys.add(new Facility(id, name, area, cost, maxPeople, rentTypeId, facilityTypeId, standard, description, poolArea, floors, facilityFree));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return facilitys;
    }

    @Override
    public List<Facility> findAll() {
        return findFacilityByType(SELECT_ALL_FACILITY);
    }

    @Override
    public List<Facility> findVilla() {
        return findFacilityByType(SELECT_ALL_VILLA);
    }

    @Override
    public List<Facility> findHouse() {
        return findFacilityByType(SELECT_ALL_HOUSE);
    }

    @Override
    public List<Facility> findRoom() {
        return findFacilityByType(SELECT_ALL_ROOM);
    }

    @Override
    public void save(Facility facility){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FACILITY_SQL)) {

            preparedStatement.setInt(1, facility.getId());
            preparedStatement.setString(2, facility.getName());
            preparedStatement.setInt(3, facility.getArea());
            preparedStatement.setDouble(4, facility.getCost());
            preparedStatement.setInt(5, facility.getMaxPeople());
            preparedStatement.setString(6, facility.getStandard());
            preparedStatement.setString(7, facility.getDescription());
            preparedStatement.setDouble(8, facility.getPoolArea());
            preparedStatement.setInt(9, facility.getFloors());
            preparedStatement.setString(10, facility.getFacilityFree());
            preparedStatement.setInt(11, facility.getRentTypeId());
            preparedStatement.setInt(12, facility.getFacilityTypeId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }




    public Facility selectFacility(int id) {
        Facility facility = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                facility = new Facility(id, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return facility;
    }


    public boolean deleteFacility(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateFacility(Facility facility) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, facility.getName());
            statement.setString(2, facility.getEmail());
            statement.setString(3, facility.getCountry());
            statement.setInt(4, facility.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public List<Facility> selectFacilitysWhereCountry(String ctry) {
        List<Facility> facilitys = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_BY_COUNTRY);) {
            preparedStatement.setString(1, ctry);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                facilitys.add(new Facility(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return facilitys;

    }

    @Override
    public Facility getFacilityById(int id) {
        Facility facility = null;

        String query = "{CALL get_facility_by_id(?)}";

        // Step 1: Establishing a Connection

        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object

             CallableStatement callableStatement = connection.prepareCall(query);) {

            callableStatement.setInt(1, id);

            // Step 3: Execute the query or update query

            ResultSet rs = callableStatement.executeQuery();

            // Step 4: Process the ResultSet object.

            while (rs.next()) {

                String name = rs.getString("name");

                String email = rs.getString("email");

                String country = rs.getString("country");

                facility = new Facility(id, name, email, country);

            }

        } catch (SQLException e) {

            printSQLException(e);

        }

        return facility;
    }

    @Override
    public List<Facility> selectAllFacilitysStore() {
        List<Facility> facilitys = new ArrayList<>();
        String query = "{CALL get_all_facility()}";

        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {

            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                facilitys.add(new Facility(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return facilitys;
    }

    @Override
    public boolean updateFacilityStore(Facility facility) throws SQLException {
        boolean rowUpdated;
        String query = "{CALL update_facility(?,?,?,?)}";
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, facility.getId());
            callableStatement.setString(2, facility.getName());
            callableStatement.setString(3, facility.getEmail());
            callableStatement.setString(4, facility.getCountry());

            rowUpdated = callableStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteFacilityStore(int id) throws SQLException {
        boolean rowDeleted;
        String query = "{CALL delete_facility_by_id(?)}";
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, id);
            rowDeleted = callableStatement.executeUpdate() > 0;
            return rowDeleted;
        }
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
            connection = DriverManager.getConnection(jdbcURL, jdbcFacilityname, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

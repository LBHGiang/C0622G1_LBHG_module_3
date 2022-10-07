package furama_resort.repository.impl;

import furama_resort.model.DatabaseConnection;
import furama_resort.model.Facility;
import furama_resort.model.PrintSQLException;
import furama_resort.repository.IFacilityRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacilityRepository implements IFacilityRepository {

    private static final String INSERT_FACILITY = "INSERT INTO facility ( `name`, area, cost, max_people, standard_room, description_other_convenience,pool_area, number_of_floors, facility_free,rent_type_id,facility_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_FACILITY_BY_ID = "select * from facility where id = ?;";
    private static final String SELECT_ALL_FACILITY = "select * from facility where is_delete = 0;";
    private static final String SELECT_ALL_VILLA = "select * from facility where is_delete = 0 and facility_type_id = 1;";
    private static final String SELECT_ALL_HOUSE = "select * from facility where is_delete = 0 and facility_type_id = 2;";
    private static final String SELECT_ALL_ROOM = "select * from facility where is_delete = 0 and facility_type_id = 3;";
    private static final String SELECT_ALL_FACILITY_ORDER_BY_NAME = "select * from facility where is_delete = 0 order by name;";
    private static final String DELETE_FACILITY = "update facility set is_delete = 1 where id = ?;";
    private static final String UPDATE_FACILITY = "update facility set  `name`= ? , area= ? , cost= ? , max_people= ? , standard_room= ? , description_other_convenience= ?, pool_area= ? , number_of_floors= ? , facility_free= ? ,rent_type_id= ? ,facility_type_id =? where id = ?;";
    private static final String SELECT_RENT_TYPE = "SELECT * FROM data_furama.rent_type where is_delete = 0;";
    private static final String SELECT_SERVICE_TYPE = "SELECT * FROM data_furama.facility_type where is_delete=0;";
    private static final String SEARCH_NAME_COST_TYPE = "SELECT * FROM data_furama.facility where is_delete=0 and name like ? and cost < ? and facility_type_id = ? ;";
    private static final String SEARCH_NAME_COST_ALL_TYPE = "SELECT * FROM data_furama.facility where is_delete=0 and name like ? and cost < ?";


    public List<Facility> findFacilityByType(String query) {
        List<Facility> facilities = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
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

                facilities.add(new Facility(id, name, area, cost, maxPeople, rentTypeId, facilityTypeId, standard, description, poolArea, floors, facilityFree));
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return facilities;
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
    public void save(Facility facility) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FACILITY)) {

            preparedStatement.setString(1, facility.getName());
            preparedStatement.setInt(2, facility.getArea());
            preparedStatement.setDouble(3, facility.getCost());
            preparedStatement.setInt(4, facility.getMaxPeople());
            preparedStatement.setString(5, facility.getStandard());
            preparedStatement.setString(6, facility.getDescription());
            preparedStatement.setDouble(7, facility.getPoolArea());
            preparedStatement.setInt(8, facility.getFloors());
            preparedStatement.setString(9, facility.getFacilityFree());
            preparedStatement.setInt(10, facility.getRentTypeId());
            preparedStatement.setInt(11, facility.getFacilityTypeId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
    }

    @Override
    public Facility findById(int id) {
        Facility facility = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FACILITY_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                facility = getFacility(id, rs);
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return facility;
    }

    private Facility getFacility(int id, ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        int area = rs.getInt("area");
        double cost = rs.getDouble("cost");
        int maxPeople = rs.getInt("max_people");
        String standard = rs.getString("standard_room");
        String description = rs.getString("description_other_convenience");
        double poolArea = rs.getDouble("pool_area");
        int floors = rs.getInt("number_of_floors");
        String facilityFree = rs.getString("facility_free");
        int rentTypeId = rs.getInt("rent_type_id");
        int facilityTypeId = rs.getInt("facility_type_id");

        Facility facility = new Facility(id, name, area, cost, maxPeople, rentTypeId, facilityTypeId, standard, description, poolArea, floors, facilityFree);
        return facility;
    }

    @Override
    public void update(int id, Facility facility){
        try {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FACILITY);) {
                preparedStatement.setString(1, facility.getName());
                preparedStatement.setInt(2, facility.getArea());
                preparedStatement.setDouble(3, facility.getCost());
                preparedStatement.setInt(4, facility.getMaxPeople());
                preparedStatement.setString(5, facility.getStandard());
                preparedStatement.setString(6, facility.getDescription());
                preparedStatement.setDouble(7, facility.getPoolArea());
                preparedStatement.setInt(8, facility.getFloors());
                preparedStatement.setString(9, facility.getFacilityFree());
                preparedStatement.setInt(10, facility.getRentTypeId());
                preparedStatement.setInt(11, facility.getFacilityTypeId());
                preparedStatement.setInt(12, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void remove(int id){
        try {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(DELETE_FACILITY);) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Facility> searchFacility(String searchName, double searchCost, int searchServiceTypeId) {
        List<Facility> facilities = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NAME_COST_TYPE);) {
            preparedStatement.setString(1, searchName);
            preparedStatement.setDouble(2, searchCost);
            preparedStatement.setDouble(3, searchServiceTypeId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                facilities.add(getFacility(id,rs));
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return facilities;
    }

    public List<Facility> searchFacility(String searchName, double searchCost) {
        List<Facility> facilities = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NAME_COST_ALL_TYPE);) {
            preparedStatement.setString(1, searchName);
            preparedStatement.setDouble(2, searchCost);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                facilities.add(getFacility(id,rs));
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return facilities;
    }

    public Map<Integer,String> findRentType() {
        Map<Integer,String> rentType = new HashMap<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RENT_TYPE);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                rentType.put(id,name);
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return rentType;
    }

    public Map<Integer,String> findServiceType() {
        Map<Integer,String> rentType = new HashMap<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERVICE_TYPE);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                rentType.put(id,name);
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return rentType;
    }
}

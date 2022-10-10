package furama_resort.repository.impl;

import furama_resort.model.DatabaseConnection;
import furama_resort.model.PrintSQLException;
import furama_resort.model.Room;
import furama_resort.repository.IRoomRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRepository implements IRoomRepository {

    private static final String INSERT_ROOM = "INSERT INTO phong_tro (`ho_ten`, `so_dien_thoai`, `ngay_thue`, `ma_thanh_toan`, `ghi_chu`) values (?,?,?,?,?);";
    private static final String SELECT_ROOM_BY_ID = "select * from phong_tro where ma_phong_tro = ? and ton_tai = 0;";
    private static final String SELECT_ALL_ROOM = "select * from phong_tro where ton_tai = 0;";
    private static final String DELETE_ROOM = "update phong_tro set ton_tai = 1 where ma_phong_tro = ?;";
    private static final String UPDATE_ROOM = "update employee set name = ?, date_of_birth = ?, id_card = ?, salary = ?, phone_number = ?, email = ?, address = ?, position_id = ?, education_degree_id = ?, division_id = ? where id = ? and is_delete = 0;";
    private static final String SELECT_RENTMETHOD = "SELECT * FROM thanh_toan where ton_tai = 0;";
    private static final String SELECT_EDUCATION = "SELECT * FROM data_furama.education_degree where is_delete=0;";
    private static final String SELECT_DIVISION = "SELECT * FROM data_furama.division where is_delete=0;";
    private static final String SEARCH_NAME_DIVISION = "SELECT * FROM data_furama.employee where is_delete=0 and name like ? and division_id = ?; ";
    private static final String SEARCH_NAME = "SELECT * FROM data_furama.employee where is_delete=0 and name like ?;";

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROOM);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Room room = getRoom(rs);
                rooms.add(room);
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return rooms;
    }

    @Override
    public void save(Room room) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROOM)) {

            preparedStatement.setString(1, room.getName());
            preparedStatement.setString(2, room.getPhoneNumber());
            preparedStatement.setString(3, room.getRentDay());
            preparedStatement.setInt(4, room.getPayMethod());
            preparedStatement.setString(5, room.getDescription());
//            preparedStatement.setString(6, room.getEmail());
//            preparedStatement.setString(7, room.getDescription());
//            preparedStatement.setInt(8, room.getPayMethod());
//            preparedStatement.setInt(9, room.getEducationDegreeId());
//            preparedStatement.setInt(10, room.getDivisionId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
    }

    @Override
    public Room findById(int id) {
        Room room = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROOM_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                room = getRoom(rs);
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return room;
    }

    @Override
    public void update(int id, Room room) {
//        try {
//            try (Connection connection = DatabaseConnection.getConnection();
//                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE);) {
//                preparedStatement.setString(1, room.getName());
//                preparedStatement.setString(2, room.getRentDay());
//                preparedStatement.setString(3, room.getIdCard());
//                preparedStatement.setDouble(4, room.getSalary());
//                preparedStatement.setString(5, room.getPhoneNumber());
//                preparedStatement.setString(6, room.getEmail());
//                preparedStatement.setString(7, room.getDescription());
//                preparedStatement.setInt(8, room.getPayMethod());
//                preparedStatement.setInt(9, room.getEducationDegreeId());
//                preparedStatement.setInt(10, room.getDivisionId());
//                preparedStatement.setInt(11, room.getId());
//
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    public void remove(int id) {
        try {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(DELETE_ROOM);) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Room> searchEmployee(int searchId, String searchName, String searchPhoneNumber) {
        return null;
    }


    public List<Room> searchRoom(String searchName, int searchDivisionId) {
//        List<Room> rooms = new ArrayList<>();
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NAME_DIVISION);) {
//            preparedStatement.setString(1, searchName);
//            preparedStatement.setInt(2, searchDivisionId);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                Room room = getRoom(rs);
//                rooms.add(room);
//            }
//        } catch (SQLException e) {
//            PrintSQLException.printSQLException(e);
//        }
//        return rooms;
        return null;
    }

    private Room getRoom(ResultSet rs) throws SQLException {
        int id = rs.getInt("ma_phong_tro");
        String name = rs.getString("ho_ten");
        String phoneNumber = rs.getString("so_dien_thoai");
//        String idCard = rs.getString("id_card");
//        double salary = rs.getDouble("salary");
//        String phoneNumber = rs.getString("phone_number");
        String rentDay = rs.getString("ngay_thue");
        int payMethod = rs.getInt("ma_thanh_toan");
        String description = rs.getString("ghi_chu");
//        int educationDegreeId = rs.getInt("education_degree_id");
//        int divisionId = rs.getInt("division_id");
        return new Room(id, name, phoneNumber, rentDay, payMethod, description);
    }


    public List<Room> searchRoom(String searchName) {
//        List<Room> rooms = new ArrayList<>();
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NAME);) {
//            preparedStatement.setString(1, searchName);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                Room room = getRoom(rs);
//                rooms.add(room);
//            }
//        } catch (SQLException e) {
//            PrintSQLException.printSQLException(e);
//        }
//        return rooms;
        return null;
    }

    public Map<Integer, String> findRentType() {
        Map<Integer, String> rentType = new HashMap<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RENTMETHOD);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ma_thanh_toan");
                String name = rs.getString("hinh_thuc");
                rentType.put(id, name);
            }
        } catch (SQLException e) {
            PrintSQLException.printSQLException(e);
        }
        return rentType;
    }

}

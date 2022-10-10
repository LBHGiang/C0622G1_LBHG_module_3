package furama_resort.repository;

import furama_resort.model.Room;

import java.util.List;
import java.util.Map;

public interface IRoomRepository {
    List<Room> findAll();

    void save(Room room);

    Room findById(int id);

    void update(int id, Room room);

    void remove(int id);

    List<Room> searchEmployee( int searchId, String searchName, String searchPhoneNumber);

}

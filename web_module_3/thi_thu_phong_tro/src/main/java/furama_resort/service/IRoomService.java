package furama_resort.service;

import furama_resort.model.Room;

import java.util.List;
import java.util.Map;

public interface IRoomService {
    List<Room> findAll();

    Map<String, String> save(Room room);

    Room findById(int id);

    void update(int id, Room room);

    void remove(int id);

    List<Room> searchEmployee(int searchId, String searchName, String searchPhoneNumber);

}

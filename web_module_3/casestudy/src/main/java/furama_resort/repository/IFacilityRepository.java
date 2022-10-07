package furama_resort.repository;

import furama_resort.model.Facility;

import java.util.List;

public interface IFacilityRepository {
    List<Facility> findAll();

    List<Facility> findVilla();

    List<Facility> findRoom();

    List<Facility> findHouse();

    void save(Facility facility);

    Facility findById(int id);

    void update(int id, Facility facility);

    void remove(int id);

    List<Facility> searchFacility(String searchName, double cost, int serviceTypeId);

    List<Facility> searchFacility(String searchName, double cost);
}

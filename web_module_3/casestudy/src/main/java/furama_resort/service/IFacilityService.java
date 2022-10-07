package furama_resort.service;

import furama_resort.model.Facility;

import java.util.List;

public interface IFacilityService {

    List<Facility> findAll();

    List<Facility> findVilla();

    List<Facility> findRoom();

    List<Facility> findHouse();

    void save(Facility facility);

    Facility findById(int id);

    void update(int id, Facility facility);

    void remove(int id);

    List<Facility> findFacility(String name, double cost);

    List<Facility> findFacility(String name, double cost, int serviceTypeId);
}
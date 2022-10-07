package furama_resort.service.impl;

import furama_resort.model.Facility;
import furama_resort.repository.IFacilityRepository;
import furama_resort.repository.impl.FacilityRepository;
import furama_resort.service.IFacilityService;

import java.util.List;

public class FacilityService implements IFacilityService {
    IFacilityRepository iFacilityRepository = new FacilityRepository();

    @Override
    public List<Facility> findAll() {
        return iFacilityRepository.findAll();
    }

    @Override
    public List<Facility> findVilla() {
        return iFacilityRepository.findVilla();
    }

    @Override
    public List<Facility> findRoom() {
        return iFacilityRepository.findRoom();
    }

    @Override
    public List<Facility> findHouse() {
        return iFacilityRepository.findHouse();
    }

    @Override
    public void save(Facility facility) {
        iFacilityRepository.save(facility);
    }

    @Override
    public Facility findById(int id) {
        return iFacilityRepository.findById(id);
    }

    @Override
    public void update(int id, Facility facility) {
        iFacilityRepository.update(id, facility);
    }

    @Override
    public void remove(int id) {
        iFacilityRepository.remove(id);
    }

    @Override
    public List<Facility> findFacility(String name, double cost, int serviceTypeId) {
        int[] serviceTypeIds;
        if (serviceTypeId == 0) {
            serviceTypeIds = new int[] {1,2,3};
        } else {
            serviceTypeIds = new int[] {serviceTypeId,serviceTypeId,serviceTypeId};
        }
        return iFacilityRepository.searchFacility("%" + name + "%", cost, serviceTypeIds);
    }

}

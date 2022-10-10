package furama_resort.service.impl;

import furama_resort.model.Employee;
import furama_resort.repository.IEmployeeRepository;
import furama_resort.repository.impl.EmployeeRepository;
import furama_resort.service.IEmployeeService;

import java.util.List;

public class EmployeeService implements IEmployeeService {
    private IEmployeeRepository iEmployeeRepository = new EmployeeRepository();

    @Override
    public List<Employee> findAll() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public void save(Employee employee) {
        iEmployeeRepository.save(employee);
    }

    @Override
    public Employee findById(int id) {
        return iEmployeeRepository.findById(id);
    }

    @Override
    public void update(int id, Employee employee) {
        iEmployeeRepository.update(id, employee);
    }

    @Override
    public void remove(int id) {
        iEmployeeRepository.remove(id);
    }

    @Override
    public List<Employee> searchEmployee(String searchName, int divisionId) {
        return iEmployeeRepository.searchEmployee("%" + searchName + "%", divisionId);
    }

    @Override
    public List<Employee> searchEmployee(String searchName) {
        return iEmployeeRepository.searchEmployee("%" + searchName + "%");
    }
}

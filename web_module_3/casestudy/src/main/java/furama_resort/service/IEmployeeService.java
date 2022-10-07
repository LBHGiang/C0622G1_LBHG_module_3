package furama_resort.service;

import furama_resort.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();

    void save(Employee employee);

    Employee findById(int id);

    void update(int id, Employee employee);

    void remove(int id);

    List<Employee> searchEmployee(String searchName, double cost, int serviceTypeId);

    List<Employee> searchEmployee(String searchName, double cost);
}
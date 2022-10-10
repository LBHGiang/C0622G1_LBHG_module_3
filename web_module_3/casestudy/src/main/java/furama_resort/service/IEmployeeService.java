package furama_resort.service;

import furama_resort.model.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {
    List<Employee> findAll();

    Map<String,String> save(Employee employee);

    Employee findById(int id);

    void update(int id, Employee employee);

    void remove(int id);

    List<Employee> searchEmployee(String searchName, int divisionId);

    List<Employee> searchEmployee(String searchName);
}

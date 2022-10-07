package furama_resort.repository;

import furama_resort.model.Employee;

import java.util.List;

public interface IEmployeeRepository {
    List<Employee> findAll();

    void save(Employee employee);

    Employee findById(int id);

    void update(int id, Employee employee);

    void remove(int id);

    List<Employee> searchEmployee(String searchName, int divisionId);

    List<Employee> searchEmployee(String searchName);
}

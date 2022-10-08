package furama_resort.service;

import furama_resort.model.Customer;
import furama_resort.model.Employee;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(int id);

    void update(int id, Customer customer);

    void remove(int id);

    List<Customer> searchCustomer(String searchName, int searchCustomerTypeId);

    List<Customer> searchCustomer(String searchName);
}

package furama_resort.repository;

import furama_resort.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(int id);

    void update(int id, Customer customer);

    void remove(int id);

    List<Customer> searchCustomer(String searchName, int divisionId);

    List<Customer> searchCustomer(String searchName);
}

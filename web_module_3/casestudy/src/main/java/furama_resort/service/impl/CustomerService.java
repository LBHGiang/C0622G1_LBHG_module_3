package furama_resort.service.impl;

import furama_resort.model.Customer;
import furama_resort.repository.ICustomerRepository;
import furama_resort.repository.impl.CustomerRepository;
import furama_resort.service.ICustomerService;

import java.util.List;

public class CustomerService implements ICustomerService {
    private ICustomerRepository iCustomerRepository = new CustomerRepository();

    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public Customer findById(int id) {
        return iCustomerRepository.findById(id);
    }

    @Override
    public void update(int id, Customer customer) {
        iCustomerRepository.update(id, customer);
    }

    @Override
    public void remove(int id) {
        iCustomerRepository.remove(id);
    }

    @Override
    public List<Customer> searchCustomer(String searchName, int searchCustomerTypeId) {
        return iCustomerRepository.searchCustomer("%" + searchName + "%", searchCustomerTypeId);
    }

    @Override
    public List<Customer> searchCustomer(String searchName) {
        return iCustomerRepository.searchCustomer("%" + searchName + "%");
    }
}

package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;
import com.codegym.repository.ICustomerRepository;

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
        iCustomerRepository.update(id,customer);
    }

    @Override
    public void remove(int id) {
        iCustomerRepository.remove(id);
    }
}

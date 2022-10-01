package com.codegym.repository;

import com.codegym.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();

    int save(Customer customer) throws ClassNotFoundException;

    Customer findById(int id);

    void update(int id, Customer customer);

    void remove(int id);
}
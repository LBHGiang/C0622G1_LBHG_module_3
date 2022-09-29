package com.codegym.service;

import com.codegym.model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService implements ICustomerService {
    private static Map<Integer, Customer> customers;

    static {
        customers = new HashMap<>();
        customers.put(1, new Customer(1, "Hoàn", LocalDate.parse("1993-09-29"), "Hà Nội", "https://tmdl.edu.vn/wp-content/uploads/2022/07/hinh-nen-girl-xinh-full-hd-cho-laptop-va-may-1.jpg"));
        customers.put(2, new Customer(2, "Nam", LocalDate.parse("1994-04-22"), "Đà Nẵng", "https://anhdephd.vn/wp-content/uploads/2022/04/hinh-nen-gai-xinh.jpg"));
        customers.put(3, new Customer(3, "Tiến", LocalDate.parse("1997-01-03"), "Sài Gòn", "https://anhdephd.vn/wp-content/uploads/2022/04/anh-gai-xinh-hot-girl-viet-nam.jpg"));
        customers.put(4, new Customer(4, "Vinh", LocalDate.parse("1999-09-10"), "Hải Phòng", "https://tmdl.edu.vn/wp-content/uploads/2022/07/hinh-nen-girl-xinh-full-hd-cho-laptop-va-may-1.jpg"));
        customers.put(5, new Customer(5, "Giang", LocalDate.parse("2000-02-18"), "Quảng Bình", "https://how-yolo.net/wp-content/uploads/2021/12/15-13.png"));
        customers.put(6, new Customer(6, "Hải", LocalDate.parse("1995-02-22"), "Huế", "https://tmdl.edu.vn/wp-content/uploads/2022/07/hinh-nen-girl-xinh-full-hd-cho-laptop-va-may-1.jpg"));
    }

    @Override
    public List<Customer> findAll() {

        return new ArrayList<>(customers.values());
    }

    @Override
    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public Customer findById(int id) {
        return customers.get(id);
    }

    @Override
    public void update(int id, Customer customer) {
        customers.put(id, customer);
    }

    @Override
    public void remove(int id) {
        customers.remove(id);
    }
}

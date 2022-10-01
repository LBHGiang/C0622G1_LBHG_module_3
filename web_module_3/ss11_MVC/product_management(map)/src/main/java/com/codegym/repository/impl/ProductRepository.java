package com.codegym.repository.impl;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;

import java.util.List;

public class ProductRepository implements IProductRepository {

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Product product) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> findByName(String name) {
        return null;
    }

    @Override
    public List<Product> findByProducer(String producer) {
        return null;
    }

    @Override
    public List<Product> findByPrice(double min, double max) {
        return null;
    }
}

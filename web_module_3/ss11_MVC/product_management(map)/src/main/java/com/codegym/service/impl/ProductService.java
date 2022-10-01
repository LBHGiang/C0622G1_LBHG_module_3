package com.codegym.service.impl;

import com.codegym.model.Product;
import com.codegym.service.IProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
    private static Map<Integer, Product> products = new HashMap<>();

    static {
        products.put(1, new Product(1, "Bàn phím", 10000, "mô tả", "A1"));
        products.put(2, new Product(2, "Camera", 50000, "mô tả", "B2"));
        products.put(3, new Product(3, "Lót chuột", 25000, "mô tả", "D6"));
        products.put(4, new Product(4, "Chuột", 9000, "mô tả", "C2"));
        products.put(5, new Product(5, "Màn hình", 110000, "mô tả", "A5"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);

    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> productsFound = new ArrayList<>();

        for (Product product : products.values()
        ) {
            if (product.getName().contains(name)) {
                productsFound.add(product);
            }
        }
        return productsFound;
    }

    @Override
    public List<Product> findByProducer(String producer) {
        List<Product> productsFound = new ArrayList<>();

        for (Product product : products.values()
        ) {
            if (product.getProducer().contains(producer)) {
                productsFound.add(product);
            }
        }
        return productsFound;
    }

    @Override
    public List<Product> findByPrice(double min, double max) {
        List<Product> productsFound = new ArrayList<>();

        for (Product product : products.values()
        ) {
            if (product.getPrice() > min && product.getPrice() < max) {
                productsFound.add(product);
            }
        }
        return productsFound;
    }
}

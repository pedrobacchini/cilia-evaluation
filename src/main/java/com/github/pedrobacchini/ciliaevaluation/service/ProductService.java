package com.github.pedrobacchini.ciliaevaluation.service;

import com.github.pedrobacchini.ciliaevaluation.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(UUID uuid);

    Product createProduct(Product product);
}

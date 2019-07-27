package com.github.pedrobacchini.ciliaevaluation.service.impl;

import com.github.pedrobacchini.ciliaevaluation.config.LocaleMessageSource;
import com.github.pedrobacchini.ciliaevaluation.entity.Product;
import com.github.pedrobacchini.ciliaevaluation.exception.ObjectNotFoundException;
import com.github.pedrobacchini.ciliaevaluation.repository.ProductRepository;
import com.github.pedrobacchini.ciliaevaluation.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
final class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final LocaleMessageSource localeMessageSource;

    public ProductServiceImpl(ProductRepository productRepository,
                              LocaleMessageSource localeMessageSource) {
        this.productRepository = productRepository;
        this.localeMessageSource = localeMessageSource;
    }

    @Override
    public List<Product> getAllProducts() { return productRepository.findAll(); }

    @Override
    public Product getProductById(UUID uuid) {
        return productRepository.findById(uuid)
                .orElseThrow(() ->
                        new ObjectNotFoundException(localeMessageSource
                                .getMessage("object-not-found", uuid, Product.class.getName())));
    }

    @Override
    public Product createProduct(Product product) { return productRepository.save(product); }
}

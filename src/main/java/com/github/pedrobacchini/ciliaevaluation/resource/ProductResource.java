package com.github.pedrobacchini.ciliaevaluation.resource;

import com.github.pedrobacchini.ciliaevaluation.entity.Product;
import com.github.pedrobacchini.ciliaevaluation.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) { this.productService = productService; }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllProducts() { return productService.getAllProducts(); }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable("uuid") String uuid) {
        Product product = productService.getProductById(UUID.fromString(uuid));
        return ResponseEntity.ok(product);
    }
}

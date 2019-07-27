package com.github.pedrobacchini.ciliaevaluation.resource;

import com.github.pedrobacchini.ciliaevaluation.dto.ProductDTO;
import com.github.pedrobacchini.ciliaevaluation.entity.Product;
import com.github.pedrobacchini.ciliaevaluation.event.ResourceCreatedEvent;
import com.github.pedrobacchini.ciliaevaluation.service.ProductService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductResource {

    private final ProductService productService;
    private final ApplicationEventPublisher publisher;

    public ProductResource(ProductService productService,
                           ApplicationEventPublisher publisher) {
        this.productService = productService;
        this.publisher = publisher;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllProducts() { return productService.getAllProducts(); }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable("uuid") String uuid) {
        Product product = productService.getProductById(UUID.fromString(uuid));
        return ResponseEntity.ok(product);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDTO productDTO, HttpServletResponse response) {
        Product createdProduct = productService.createProduct(fromDTO(productDTO));
        publisher.publishEvent(new ResourceCreatedEvent(this, response, createdProduct.getUuid()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable("uuid") String uuid, @RequestBody @Valid ProductDTO productDTO) {
        Product product = productService.updateProduct(UUID.fromString(uuid), fromDTO(productDTO));
        return ResponseEntity.ok(product);
    }

    private Product fromDTO(ProductDTO productDTO) {
        Product product = new Product(productDTO.getName(), productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        return product;
    }
}

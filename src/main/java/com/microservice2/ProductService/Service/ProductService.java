package com.microservice2.ProductService.Service;

import com.microservice2.ProductService.Entity.Product;
import com.microservice2.ProductService.Entity.ProductInput;
import com.microservice2.ProductService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEventService productEventService;


    public Product createProduct(ProductInput input) {
        Product product = new Product();
        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setInventoryCount(input.getInventoryCount());

        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, ProductInput input) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setInventoryCount(input.getInventoryCount());

        return productRepository.save(product);
    }
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductInput input) {
        Product product = new Product();
        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setInventoryCount(input.getInventoryCount());

        Product savedProduct = productRepository.save(product);

        // Emit product created event
        productEventService.emitProductCreatedEvent(savedProduct.getProductId());

        return savedProduct;
    }

    public Product updateProduct(Long productId, ProductInput input) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setInventoryCount(input.getInventoryCount());

        Product updatedProduct = productRepository.save(product);

        // Emit inventory updated event if the count changes
        productEventService.emitInventoryUpdatedEvent(updatedProduct.getProductId());

        return updatedProduct;
    }

}

package com.microservice2.ProductService.Resolvers;
import com.microservice2.ProductService.Entity.Product;
import com.microservice2.ProductService.Entity.ProductInput;
import com.microservice2.ProductService.Service.ProductService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductResolvers implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private ProductService productService;

    // Query resolvers
    public Product getProduct(Long productId) {
        return productService.getProductById(productId);
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Mutation resolvers
    public Product createProduct(ProductInput input) {
        return productService.createProduct(input);
    }

    public Product updateProduct(Long productId, ProductInput input) {
        return productService.updateProduct(productId, input);
    }

    public String deleteProduct(Long productId) {
        productService.deleteProduct(productId);
        return "Product deleted successfully";
    }
}

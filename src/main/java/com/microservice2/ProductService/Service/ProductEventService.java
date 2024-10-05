package com.microservice2.ProductService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductEventService {
    private static final String PRODUCT_CREATED_TOPIC = "product_created";
    private static final String INVENTORY_UPDATED_TOPIC = "inventory_updated";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void emitProductCreatedEvent(Long productId) {
        kafkaTemplate.send(PRODUCT_CREATED_TOPIC, "Product Created: " + productId);
    }

    public void emitInventoryUpdatedEvent(Long productId) {
        kafkaTemplate.send(INVENTORY_UPDATED_TOPIC, "Inventory Updated for Product: " + productId);
    }
}

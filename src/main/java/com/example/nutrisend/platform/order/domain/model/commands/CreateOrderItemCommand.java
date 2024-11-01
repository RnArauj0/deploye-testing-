package com.example.nutrisend.platform.order.domain.model.commands;

public record CreateOrderItemCommand(
        String name,
        double price,
        String category,
        int quantity
) {
    public CreateOrderItemCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}

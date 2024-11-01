package com.example.nutrisend.platform.order.domain.model.queries;

public record GetOrdersByIdQuery(String id) {
    public GetOrdersByIdQuery {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }
}
package com.example.nutrisend.platform.order.rest.resources;

import java.util.List;

public record OrderResource(
        Long id,
        String userId,
        List<OrderItemResource> items,
        double total
) {
}
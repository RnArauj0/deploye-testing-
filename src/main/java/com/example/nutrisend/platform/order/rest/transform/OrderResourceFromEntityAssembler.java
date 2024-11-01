package com.example.nutrisend.platform.order.rest.transform;

import com.example.nutrisend.platform.order.domain.model.aggregates.Order;
import com.example.nutrisend.platform.order.rest.resources.OrderItemResource;
import com.example.nutrisend.platform.order.rest.resources.OrderResource;

public class OrderResourceFromEntityAssembler {
    public static OrderResource toResourceFromEntity(Order order) {
        return new OrderResource(
                order.getId(),
                order.getUserId(),
                order.getItems().stream()
                        .map(item -> new OrderItemResource(
                                item.getName(),
                                item.getPrice(),
                                item.getCategory(),
                                item.getQuantity()
                        ))
                        .toList(),
                order.getTotal()
        );
    }
}

package com.example.nutrisend.platform.order.internal;

import com.example.nutrisend.platform.order.domain.model.aggregates.Order;
import com.example.nutrisend.platform.order.domain.model.aggregates.OrderItem;
import com.example.nutrisend.platform.order.domain.model.commands.CreateOrderCommand;
import com.example.nutrisend.platform.order.domain.services.OrderCommandService;
import com.example.nutrisend.platform.order.jpa.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderCommandServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> handle(CreateOrderCommand command) {
        // Generar un ID único para el nuevo pedido
        String orderId = UUID.randomUUID().toString();

        // Crear los elementos del pedido
        List<OrderItem> orderItems = command.items().stream()
                .map(item -> new OrderItem(item.name(), item.price(), item.category(), item.quantity()))
                .toList();

        // Calcular el total del pedido
        double total = orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        // Crear una nueva instancia de Order
        Order order = new Order(command.userId(), orderItems, total);

        // Guardar el pedido en la base de datos
        orderRepository.save(order);

        return Optional.of(order); // Retornar el pedido creado
    }
}
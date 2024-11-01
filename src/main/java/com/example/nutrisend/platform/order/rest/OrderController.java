package com.example.nutrisend.platform.order.rest;

import com.example.nutrisend.platform.order.domain.model.aggregates.Order;
import com.example.nutrisend.platform.order.domain.model.commands.CreateOrderCommand;
import com.example.nutrisend.platform.order.domain.model.queries.GetOrdersByIdQuery;
import com.example.nutrisend.platform.order.domain.services.OrderCommandService;
import com.example.nutrisend.platform.order.domain.services.OrderQueryService;
import com.example.nutrisend.platform.order.rest.resources.CreateOrderResource;
import com.example.nutrisend.platform.order.rest.resources.OrderResource;
import com.example.nutrisend.platform.order.rest.transform.CreateOrderCommandFromResourceAssembler;
import com.example.nutrisend.platform.order.rest.transform.OrderResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = "application/json")
@Tag(name = "Orders", description = "Operations related to orders")
public class OrderController {

    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;

    public OrderController(OrderQueryService orderQueryService, OrderCommandService orderCommandService) {
        this.orderQueryService = orderQueryService;
        this.orderCommandService = orderCommandService;
    }

    @Operation(
            summary = "Create an order",
            description = "Create a new order with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<OrderResource> createOrder(@RequestBody CreateOrderResource resource) {
        CreateOrderCommand command = CreateOrderCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<Order> orderItem = orderCommandService.handle(command);
        return orderItem.map(order -> new ResponseEntity<>(OrderResourceFromEntityAssembler.toResourceFromEntity(order), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get order by ID",
            description = "Retrieve a specific order by its unique ID"
    )
    @GetMapping("{id}")
    public ResponseEntity<OrderResource> getOrderById(@PathVariable String id) {
        Optional<Order> orderItem = orderQueryService.handle(new GetOrdersByIdQuery(id));
        return orderItem.map(order -> ResponseEntity.ok(OrderResourceFromEntityAssembler.toResourceFromEntity(order)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get all orders",
            description = "Retrieve all orders"
    )
    @GetMapping
    public ResponseEntity<List<OrderResource>> getAllOrders() {
        List<Order> orders = orderQueryService.getAllOrders();
        List<OrderResource> orderResources = orders.stream()
                .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(orderResources);
    }
}

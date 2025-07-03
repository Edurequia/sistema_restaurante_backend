package com.example.dudu.services;

import com.example.dudu.dtos.OrderDTO;
import com.example.dudu.dtos.OrderItemDTO;
import com.example.dudu.dtos.OrderRequestDTO;
import com.example.dudu.entities.Order;
import com.example.dudu.entities.OrderItem;
import com.example.dudu.entities.Product;
import com.example.dudu.repositories.OrderRepository;
import com.example.dudu.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderDTO createOrder(OrderRequestDTO orderRequest) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDENTE");
        order.setTotal(0.0);

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> items = orderRequest.getItems().stream()
                .map(itemRequest -> {
                    Product product = productRepository.findById(itemRequest.getProductId())
                            .orElseThrow(() -> new RuntimeException("Product not found: " + itemRequest.getProductId()));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(savedOrder);
                    orderItem.setProduct(product);
                    orderItem.setQuantity(itemRequest.getQuantity());
                    orderItem.setUnitPrice(product.getPrice());

                    return orderItem;
                }).collect(Collectors.toList());

        savedOrder.setItems(items);

        // Calculate total
        double total = items.stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
        savedOrder.setTotal(total);

        Order finalOrder = orderRepository.save(savedOrder);
        return convertToDTO(finalOrder);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDTO(order);
    }

    private OrderDTO convertToDTO(Order order) {
        List<OrderItemDTO> itemDTOs = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setProductId(item.getProduct().getId());
            itemDTO.setProductName(item.getProduct().getName());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setUnitPrice(item.getUnitPrice());
            itemDTOs.add(itemDTO);
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotal(order.getTotal());
        orderDTO.setItems(itemDTOs);

        return orderDTO;
    }
}

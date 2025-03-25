package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.dto.OrderItemDTO;
import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.OrderStatus;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.exceptions.RequestNotFound;
import com.devsuperior.dscommerce.repositories.OrderItemRepository;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Optional<Order> result = orderRepository.findById(id);
        Order order = result.orElseThrow(() -> new RequestNotFound("Resource not found"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticated());

        for (OrderItemDTO itemDto : dto.getItems()) {
            Product prod = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(prod, order, itemDto.getQuantity(), prod.getPrice());
            order.getItems().add(item);
        }
        order = orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }
}

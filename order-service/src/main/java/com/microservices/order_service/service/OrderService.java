package com.microservices.order_service.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.microservices.order_service.dto.OrderLineItemsDto;
import com.microservices.order_service.dto.OrderRequest;
import com.microservices.order_service.model.Order;
import com.microservices.order_service.model.OrderLineItems;
import com.microservices.order_service.repository.OrderRepository;

@Service
public class OrderService {
     
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository)
    {
    this.orderRepository = orderRepository;
    }

    public void PlaceOrder(OrderRequest orderRequest)
    {
     Order order = new Order();   
     order.setOrderNumber(UUID.randomUUID().toString());
     List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream().map(this::convert).toList();
     order.setOrderLineItems(orderLineItemsList);
     orderRepository.save(order);
    }
    public OrderLineItems convert(OrderLineItemsDto orderLineItemsDto)
    {
         OrderLineItems orderLineItems = new OrderLineItems();
         orderLineItems.setId(orderLineItems.getId());
         orderLineItems.setPrice(orderLineItemsDto.getPrice());
         orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
         orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
         return orderLineItems;
    }
}

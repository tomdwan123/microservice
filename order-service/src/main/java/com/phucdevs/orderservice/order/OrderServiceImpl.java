package com.phucdevs.orderservice.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository repository;
    
    @Override
    public void placeOrder(OrderRequest orderRequest) {

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsRequests()
                .stream()
                .map(this::mapToDto)
                .toList();

        Order order = Order.builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .orderLineItems(orderLineItems)
                .build();
        
        repository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsRequest request) {

        OrderLineItems orderLineItems = OrderLineItems
                .builder()
                    .price(request.getPrice())
                    .quantity(request.getQuantity())
                    .skuCode(request.getSkuCode())
                .build();
        return orderLineItems;
    }
}

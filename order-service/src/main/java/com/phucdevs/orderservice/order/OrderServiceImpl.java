package com.phucdevs.orderservice.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final String INVENTORY_SERVICE_URL = "http://localhost:8082/api/v1/inventory";
    
    private final OrderRepository repository;
    private final WebClient webClient;
    
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

        List<String> skuCodes = order.getOrderLineItems()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        // call to inventory-service to place order if product is in stock
        InventoryResponse[] inventoryResponses = webClient.get()
                .uri(INVENTORY_SERVICE_URL,
                        uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);

        if (Boolean.FALSE.equals(allProductInStock)) {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
        
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

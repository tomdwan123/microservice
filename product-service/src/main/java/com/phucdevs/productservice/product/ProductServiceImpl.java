package com.phucdevs.productservice.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository repository;
    
    @Override
    @Transactional
    public void create(ProductRequest productRequest) {

        Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                .build();
        
        repository.save(product);
        log.info("Product {} is saved successfully!", product.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getAll() {

        List<Product> products = repository.findAll();
        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                .build();
    }
}

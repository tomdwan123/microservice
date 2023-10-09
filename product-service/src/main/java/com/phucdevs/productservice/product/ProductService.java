package com.phucdevs.productservice.product;

import java.util.List;

public interface ProductService {
    
    public void create(ProductRequest productRequest);
    public List<ProductResponse> getAll();
}

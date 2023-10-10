package com.phucdevs.inventoryservice.inventory;

import java.util.List;

public interface InventoryService {
    
    public List<InventoryResponse> isInStock(List<String> skuCodes);
}

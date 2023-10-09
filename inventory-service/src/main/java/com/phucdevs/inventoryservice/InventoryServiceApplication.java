package com.phucdevs.inventoryservice;

import com.phucdevs.inventoryservice.inventory.Inventory;
import com.phucdevs.inventoryservice.inventory.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	/*@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {

		return args -> {
			List<Inventory> inventories = List.of(
					Inventory.builder()
							.skuCode("iphone_13").quantity(1000)
							.build(),
					Inventory.builder()
							.skuCode("iphone_13_red").quantity(0)
							.build()
	
			);
			
			inventoryRepository.saveAll(inventories);
		};
	}*/
}

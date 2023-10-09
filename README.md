# Build Spring Boot Microservices

#### High architecture
![img.png](high_architecture.png)

### Service we are going to build
- **Product Service** - Create and View Products, acts as Product Catalog
- **Order Service** - Can Order Products
- **Inventory Service** - Can check if products in stock or not
- **Notification Service** - Can send notifications, after order is placed
- **Order Service**, **Inventory Service** and **Notification Service** are going to interact with each other
- Synchronous and Asynchronous communication
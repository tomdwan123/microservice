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

#### Create image docker keycloak
`docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:22.0.4 start-dev
`
#### Create image docker zipkin
`docker run -d -p 9411:9411 openzipkin/zipkin
`
#### Create docker image java application
- large size: `docker build -t [name_image] .`
- small size: `docker build -t [name_image] -f Dockerfile.layered .`
- without dockerfile: 
  - add plugin jib-maven-plugin
  - mvn clean compile jib:build
  - add server to settings.xml

#### Refer document
[spring-cloud-gateway-eureka-service-discovery](https://github.com/BarathArivazhagan/spring-cloud-gateway-eureka-service-discovery/blob/master/docker-compose.yml)

#### Security
- Keycloak

#### Distributed Tracing
- Spring Cloud Sleuth -> generate traceId and spanId
- OpenZipkin -> visualize

#### Monitoring
- Spring Boot Application <- Prometheus <- Grafana
- [Monitoring and Metrics for Spring](https://www.youtube.com/watch?v=_WdIlz33FKE)

#### Migrate version Spring Boot
- [Rewrite](https://docs.openrewrite.org/)

#### Spring Boot Microservices Document
[Spring Boot Microservices](https://www.youtube.com/watch?v=5_EXMJbhLY4&list=PLSVW22jAG8pBnhAdq9S8BpLnZ0_jVBj0c&index=9)
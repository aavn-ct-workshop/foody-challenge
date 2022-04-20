# order-service
Implementation of workshop order-service.
# Configure
Open <code>application.properties</code> file in <code>src/main/resources</code> folder and adapt it base on your local machine.
1. Configure database (Using <code>PostgreSQL</code>):
    ```
    spring.datasource.url= jdbc:postgresql://localhost:5432/postgres
    spring.datasource.username= postgres
    spring.datasource.password= 123456
    ```
2. Configure Kafka:
    ```
   // Kafka bootstrap server.
   order.service.kafka.bootstrap.servers=localhost:9092
   
   // Kafka topics require to write and read.
   order.service.kafka.topic=orders
   payment.service.kafka.topic=payments
   driver.service.kafka.topic=deliveries
    ```
Make sure to have all require kafka topics before start service.
# Specification
1. Create new <code>Order</code>

   POST ```/orders```
   
   Request body:
   ```
    "hostId": "ab893719-f74c-4ab0-b5b3-096ba5102aa5",
    "hostName": "Nguyen Van A",
    "deliveryAddress": "Ninh Kieu, Can Tho",
    "orderItems": [
        {
            "name": "Ca phe",
            "price": 15000,
            "quantity": 3
        },
        {
            "name": "Tra dao",
            "price": 15000,
            "quantity": 2
        }
    ]
   ```
   Response (ID of created order):
   ```
   14aa9e4f-aef5-4e3d-96ee-3ad3e892e48d
   ```
2. Message structure of <code>Order</code> on Kafka topic:
   ```
   {
    "id": "14aa9e4f-aef5-4e3d-96ee-3ad3e892e48d",
    "hostId": "ab893719-f74c-4ab0-b5b3-096ba5102aa5",
    "hostName": "Nguyen Van A",
    "driverId": null,
    "status": "VALIDATING",
    "createDate": 1648476348107,
    "deliveryAddress": "Ninh Kieu, Can Tho",
    "orderItems": [
        {
            "id": "00d39367-a4ac-4a3c-959b-fef49186e9e1",
            "name": "Ca phe",
            "price": 15000,
            "quantity": 3
        },
        {
            "id": "fb862562-94cb-4455-a9d5-3a62a0732673",
            "name": "Tra dao",
            "price": 15000,
            "quantity": 2
        }
    ]
   }
   ```
# Run service
Run the command bellow to start service:

    mvn spring-boot:run


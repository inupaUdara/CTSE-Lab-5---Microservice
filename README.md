# Microservices Lab - CTSE (SE4010)

**Student Name:** [Your Name]  
**Student ID:** [Your ID]  
**Module:** Current Trends in Software Engineering (SE4010)  
**Year:** 2026 | Semester 1

## 📋 Project Overview

This project implements a complete microservices architecture system with four independent services:

- **Item Service** (Port 8081) - Manages product inventory
- **Order Service** (Port 8082) - Handles customer orders
- **Payment Service** (Port 8083) - Processes payments
- **API Gateway** (Port 8080) - Routes all client requests

All services are built using **Spring Boot** and containerized with **Docker**.

## 🏗️ System Architecture

```
Client (Postman/Browser)
         ↓
    API Gateway :8080
    /items /orders /payments
         ↓
   ┌─────┼─────┐
   ↓     ↓     ↓
Item  Order  Payment
:8081 :8082  :8083
```

### Communication Flow
- All client requests go through the API Gateway on port 8080
- The gateway routes requests to appropriate microservices based on URL path
- Services do NOT communicate directly with each other
- All services run in isolated Docker containers on a shared network

## 🛠️ Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| Services | Spring Boot | 3.2.0 |
| API Gateway | Spring Cloud Gateway | 2023.0.0 |
| Build Tool | Maven | - |
| Java | OpenJDK | 17 |
| Containerization | Docker & Docker Compose | - |

## 📁 Project Structure

```
Lab 5/
├── item-service/
│   ├── src/main/java/com/example/itemservice/
│   │   ├── ItemServiceApplication.java
│   │   └── controller/ItemController.java
│   ├── src/main/resources/application.properties
│   ├── pom.xml
│   └── Dockerfile
│
├── order-service/
│   ├── src/main/java/com/example/orderservice/
│   │   ├── OrderServiceApplication.java
│   │   └── controller/OrderController.java
│   ├── src/main/resources/application.properties
│   ├── pom.xml
│   └── Dockerfile
│
├── payment-service/
│   ├── src/main/java/com/example/paymentservice/
│   │   ├── PaymentServiceApplication.java
│   │   └── controller/PaymentController.java
│   ├── src/main/resources/application.properties
│   ├── pom.xml
│   └── Dockerfile
│
├── api-gateway/
│   ├── src/main/java/com/example/apigateway/
│   │   └── ApiGatewayApplication.java
│   ├── src/main/resources/application.yml
│   ├── pom.xml
│   └── Dockerfile
│
├── docker-compose.yml
└── README.md
```

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:
- **Java 17** or higher
- **Maven 3.6+**
- **Docker Desktop** (for Mac/Windows) or Docker Engine (Linux)
- **Postman** (for API testing)

### Verify Prerequisites

```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Check Docker version
docker --version
docker-compose --version
```

## 📦 Building the Project

### Step 1: Build Maven Projects

Navigate to the project root directory and build each service:

```bash
# Build Item Service
cd item-service
mvn clean package -DskipTests
cd ..

# Build Order Service
cd order-service
mvn clean package -DskipTests
cd ..

# Build Payment Service
cd payment-service
mvn clean package -DskipTests
cd ..

# Build API Gateway
cd api-gateway
mvn clean package -DskipTests
cd ..
```

**Note:** The `-DskipTests` flag skips test execution to speed up the build process.

### Step 2: Build Docker Images

From the project root directory:

```bash
docker-compose build
```

This command builds Docker images for all four services.

## 🏃 Running the Application

### Start All Services

```bash
docker-compose up
```

To run in detached mode (background):

```bash
docker-compose up -d
```

### Check Running Containers

```bash
docker ps
```

You should see 4 containers running:
- `item-service` on port 8081
- `order-service` on port 8082
- `payment-service` on port 8083
- `api-gateway` on port 8080

### View Logs

```bash
# View all logs
docker-compose logs

# View specific service logs
docker-compose logs item-service
docker-compose logs order-service
docker-compose logs payment-service
docker-compose logs api-gateway

# Follow logs in real-time
docker-compose logs -f api-gateway
```

## 🔌 API Endpoints

All requests should be sent through the **API Gateway** on port **8080**.

### Item Service Endpoints

| Method | Endpoint | Description | Sample Body |
|--------|----------|-------------|-------------|
| GET | `http://localhost:8080/items` | Get all items | - |
| POST | `http://localhost:8080/items` | Add new item | `{"name": "Headphones"}` |
| GET | `http://localhost:8080/items/{id}` | Get item by ID | - |

### Order Service Endpoints

| Method | Endpoint | Description | Sample Body |
|--------|----------|-------------|-------------|
| GET | `http://localhost:8080/orders` | Get all orders | - |
| POST | `http://localhost:8080/orders` | Place new order | `{"item": "Laptop", "quantity": 2, "customerId": "C001"}` |
| GET | `http://localhost:8080/orders/{id}` | Get order by ID | - |

### Payment Service Endpoints

| Method | Endpoint | Description | Sample Body |
|--------|----------|-------------|-------------|
| GET | `http://localhost:8080/payments` | Get all payments | - |
| POST | `http://localhost:8080/payments/process` | Process payment | `{"orderId": 1, "amount": 1299.99, "method": "CARD"}` |
| GET | `http://localhost:8080/payments/{id}` | Get payment by ID | - |

## 🧪 Testing with Postman

### Import and Test

1. Open **Postman**
2. Create a new Collection called "**Microservices Lab**"
3. Add requests for each endpoint listed above

### Sample Test Sequence

#### 1. Get all items
```
GET http://localhost:8080/items
```

#### 2. Add a new item
```
POST http://localhost:8080/items
Content-Type: application/json

{
  "name": "Headphones"
}
```

#### 3. Get item by ID
```
GET http://localhost:8080/items/0
```

#### 4. Place an order
```
POST http://localhost:8080/orders
Content-Type: application/json

{
  "item": "Laptop",
  "quantity": 2,
  "customerId": "C001"
}
```

#### 5. Get all orders
```
GET http://localhost:8080/orders
```

#### 6. Process a payment
```
POST http://localhost:8080/payments/process
Content-Type: application/json

{
  "orderId": 1,
  "amount": 1299.99,
  "method": "CARD"
}
```

#### 7. Get payment by ID
```
GET http://localhost:8080/payments/1
```

### Expected Responses

**GET /items:**
```json
["Book", "Laptop", "Phone"]
```

**POST /orders (Response):**
```json
{
  "id": 1,
  "item": "Laptop",
  "quantity": 2,
  "customerId": "C001",
  "status": "PENDING"
}
```

**POST /payments/process (Response):**
```json
{
  "id": 1,
  "orderId": 1,
  "amount": 1299.99,
  "method": "CARD",
  "status": "SUCCESS"
}
```

## 🛑 Stopping the Application

```bash
# Stop all containers
docker-compose down

# Stop and remove volumes
docker-compose down -v

# Stop and remove images
docker-compose down --rmi all
```

## 🐛 Troubleshooting

### Port Already in Use

If you see "port already in use" errors:

```bash
# Find process using port 8080
lsof -i :8080

# Kill the process (replace PID with actual process ID)
kill -9 <PID>
```

### Container Won't Start

```bash
# Check container status
docker ps -a

# View container logs
docker logs <container-name>

# Restart specific service
docker-compose restart item-service
```

### Gateway Can't Reach Services

Make sure all services are on the same Docker network:

```bash
# Inspect network
docker network inspect lab-5_microservices-net

# Verify service names match in application.yml
```

### Build Failures

```bash
# Clean Maven cache and rebuild
cd item-service
mvn clean install -DskipTests -U

# Remove old Docker images
docker system prune -a
```

## 📸 Screenshots for Submission

Include the following screenshots in your documentation:

1. ✅ Running `docker ps` showing all 4 containers
2. ✅ Postman collection with all API requests
3. ✅ Successful GET response from `/items`
4. ✅ Successful POST response from `/orders`
5. ✅ Successful POST response from `/payments/process`
6. ✅ Docker Compose logs showing all services running
7. ✅ Project folder structure in VS Code/IDE

## 📚 Key Learning Outcomes

- ✅ Built independent microservices using Spring Boot
- ✅ Implemented API Gateway for request routing
- ✅ Containerized services using Docker
- ✅ Orchestrated multi-container application with Docker Compose
- ✅ Tested microservices architecture using Postman
- ✅ Understood service-to-service communication through gateway
- ✅ Applied RESTful API design principles

## 🎯 Bonus Features Implemented

- [x] All GET endpoints working
- [x] All POST endpoints working
- [x] Complete error handling
- [x] Health checks in Docker Compose
- [x] Comprehensive documentation
- [x] Clear API responses with proper HTTP status codes

## 📝 Notes

- All services use **in-memory storage** (no database required)
- Data will be **lost when containers restart**
- Services are **stateless** and can be scaled horizontally
- Gateway uses **path-based routing** for request distribution

## 👨‍💻 Author

**[Your Name]**  
Faculty of Computing  
SLIIT - Sri Lanka Institute of Information Technology

---

## 📄 License

This project is created for educational purposes as part of the SE4010 module.

---

**Last Updated:** March 2026

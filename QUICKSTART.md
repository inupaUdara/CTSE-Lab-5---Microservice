# Microservices Lab - Quick Start Guide

## Prerequisites Check

Before starting, ensure you have:
- [ ] Java 17 or higher installed
- [ ] Maven 3.6+ installed
- [ ] Docker Desktop running
- [ ] Postman installed

## Quick Start (5 Minutes)

### Step 1: Build Services (2-3 minutes)

```bash
# Option A: Use the build script (macOS/Linux)
chmod +x build-all.sh
./build-all.sh

# Option B: Build manually
cd item-service && mvn clean package -DskipTests && cd ..
cd order-service && mvn clean package -DskipTests && cd ..
cd payment-service && mvn clean package -DskipTests && cd ..
cd api-gateway && mvn clean package -DskipTests && cd ..
```

### Step 2: Start Docker Containers (1-2 minutes)

```bash
# Build Docker images
docker-compose build

# Start all services
docker-compose up -d

# Verify all containers are running
docker ps
```

You should see 4 containers running:
- item-service (8081)
- order-service (8082)
- payment-service (8083)
- api-gateway (8080)

### Step 3: Test with Postman (2 minutes)

1. Open Postman
2. Test these endpoints:

**Test 1: Get Items**
```
GET http://localhost:8080/items
```

**Test 2: Add Item**
```
POST http://localhost:8080/items
Content-Type: application/json

{"name": "Headphones"}
```

**Test 3: Create Order**
```
POST http://localhost:8080/orders
Content-Type: application/json

{
  "item": "Laptop",
  "quantity": 2,
  "customerId": "C001"
}
```

**Test 4: Process Payment**
```
POST http://localhost:8080/payments/process
Content-Type: application/json

{
  "orderId": 1,
  "amount": 1299.99,
  "method": "CARD"
}
```

## Troubleshooting

### Build fails?
```bash
# Clean everything and try again
mvn clean
rm -rf target/
mvn package -DskipTests
```

### Containers won't start?
```bash
# Check logs
docker-compose logs

# Restart
docker-compose down
docker-compose up -d
```

### Port already in use?
```bash
# Stop all containers
docker-compose down

# Find and kill process using port 8080
lsof -i :8080
kill -9 <PID>
```

## Stopping Everything

```bash
# Stop all containers
docker-compose down

# Stop and clean up everything
docker-compose down -v --rmi all
```

## Useful Commands

```bash
# View logs
docker-compose logs -f

# View specific service logs
docker-compose logs item-service

# Restart specific service
docker-compose restart item-service

# Check service health
curl http://localhost:8080/items
```

## Need Help?

Check the main [README.md](README.md) for detailed documentation.

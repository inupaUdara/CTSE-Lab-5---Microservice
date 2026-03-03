#!/bin/bash

echo "======================================"
echo "Microservices Lab - Build Script"
echo "======================================"
echo ""

# Build each service
echo "Building Item Service..."
cd item-service
mvn clean package -DskipTests
if [ $? -ne 0 ]; then
    echo "❌ Item Service build failed!"
    exit 1
fi
echo "✅ Item Service built successfully"
cd ..

echo ""
echo "Building Order Service..."
cd order-service
mvn clean package -DskipTests
if [ $? -ne 0 ]; then
    echo "❌ Order Service build failed!"
    exit 1
fi
echo "✅ Order Service built successfully"
cd ..

echo ""
echo "Building Payment Service..."
cd payment-service
mvn clean package -DskipTests
if [ $? -ne 0 ]; then
    echo "❌ Payment Service build failed!"
    exit 1
fi
echo "✅ Payment Service built successfully"
cd ..

echo ""
echo "Building API Gateway..."
cd api-gateway
mvn clean package -DskipTests
if [ $? -ne 0 ]; then
    echo "❌ API Gateway build failed!"
    exit 1
fi
echo "✅ API Gateway built successfully"
cd ..

echo ""
echo "======================================"
echo "All services built successfully! ✅"
echo "======================================"
echo ""
echo "Next steps:"
echo "1. Run: docker-compose build"
echo "2. Run: docker-compose up"
echo ""

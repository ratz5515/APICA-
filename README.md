Apica Microservices Project

Overview
This repository contains two Spring Boot microservices:
User Service: Manages user registration, authentication, CRUD, and publishes events to Kafka.
Journal Service: Consumes user events from Kafka and exposes endpoints to retrieve them.

Tools&Software
- Docker & Docker Compose
- Java 17+
- Maven

Running the System
1. Build and start all services:
bash
docker-compose up --build

2. User Service runs on http://localhost:8080
3. Journal Service runs on http://localhost:8081

APIs

User Service
- POST /api/users/register
- POST /api/users/login
- GET /api/users
- GET /api/users/{id}
- PUT /api/users/{id}
- DELETE /api/users/{id}

Journal Service
- GET /api/journal/events

Notes
- Default JWT secret and expiration configured in application.yml.
- Kafka topic:user-events

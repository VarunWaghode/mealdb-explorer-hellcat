Hell Cat'sTheMealDB Explorer (Mono-Repo)

A production-grade Full-Stack application acting as a resilient proxy to TheMealDB API.

This repository contains the complete source code for TheMealDB Explorer, structured as a mono-repo containing both the Spring Boot Backend and the React Frontend.

mealExplorer/ : The Backend API (Java 8, Spring Boot 2.7, Redis, Resilience4j).

meal-explorer-frontend/ : The Frontend UI (React 18, Tailwind CSS).

 Quick Start Guide

To run the entire application locally, follow these three steps in order.

1. Start Infrastructure (Redis)

You need a running Redis instance for the backend to work. Run this Docker command:
--------------------------------------------------------------------------------------------
docker run -d --name redis_local -p 6379:6379 redis:latest redis-server --maxmemory 200mb --maxmemory-policy allkeys-lru
--------------------------------------------------------------------------------------------

2. Start Backend (Java)

Open a terminal in the mealExplorer folder:
--------------------------------------------------------------------------------------------
cd mealExplorer
./mvnw spring-boot:run
--------------------------------------------------------------------------------------------
Server Port: 8001
--------------------------------------------------------------------------------------------
Swagger UI: http://localhost:8001/swagger-ui/index.html
--------------------------------------------------------------------------------------------
3. Start Frontend (React)

Open a new terminal in the meal-explorer-frontend folder:
--------------------------------------------------------------------------------------------
cd meal-explorer-frontend
npm install
npm start
--------------------------------------------------------------------------------------------

App URL: http://localhost:3000
--------------------------------------------------------------------------------------------
System Architecture

Backend Highlights : 
Circuit Breaker Pattern: Prevents cascading failures if TheMealDB API goes down.
Smart Caching: A background scheduler warms up the cache with popular categories on startup.
Observability: Performance logging via AOP and health checks via Spring Actuator.

Frontend Highlights : 
Glassmorphism UI: Modern, aesthetic design using Tailwind CSS.
Responsive Layout: Adaptive grid and modal system for mobile and desktop.
Dietary Modes: Toggles for Vegetarian and Non-Vegetarian discovery.

Author: Varun Waghode
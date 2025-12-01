Hell cat's Meal Explorer - Backend Service

A production-grade REST API acting as a resilient proxy to TheMealDB. Built with strict adherence to Java 8 enterprise standards and designed for high availability using Circuit Breakers and Redis Caching.

 Technical Stack
-----------------------------------------------------------------------
Java JDK : 1.8.0_251 
Framework : Spring Boot 2.7.18
IDE : Spring Tool Suite (STS) 4
Build Tool : Maven 3.8+
Caching : Redis (Docker)
Resilience : Resilience4j
HTTP Client : OpenFeign
Documentation : SpringDoc OpenAPI
Logging : Slf4j + Logback
-----------------------------------------------------------------------

#  Prerequisites

Ensure your development environment meets these specific requirements:

Java JDK: Version 1.8.0_251 installed and configured in JAVA_HOME.

Docker Desktop: Installed and running (Required for Redis).

Spring Tool Suite (STS): Recommended IDE for running the project.

# Execution Guide

1. Infrastructure Setup (Redis on Docker)

The application requires a Redis instance for caching. We run only Redis in Docker, while the Java application runs locally in your IDE/Terminal.

Run this command to start the Redis container:
-----------------------------------------------------------------------
docker run -d --name redis_local -p 6379:6379 
-----------------------------------------------------------------------
docker exec -it redis_local redis-cli config set maxmemory 200mb
-----------------------------------------------------------------------
-----------------------------------------------------------------------
-d: Detached mode (runs in background).

-p 6379:6379: Exposes the Redis port to your local machine.

--maxmemory: Prevents the cache from consuming excessive RAM.

--maxmemory-policy: Automatically deletes old keys when full.
-----------------------------------------------------------------------

# How to View/Inspect Cache

To see the data inside Redis without installing extra tools, use the Docker command line:

Enter the Redis Container:
-----------------------------------------------------------------------
docker exec -it redis_local redis-cli
-----------------------------------------------------------------------

Useful Commands:
-----------------------------------------------------------------------
KEYS * : List all cached keys (e.g., meals::search-chicken).
-----------------------------------------------------------------------
GET "key_name" : View the JSON content of a specific key.
-----------------------------------------------------------------------
TTL "key_name" : Check how many seconds until the key expires.
-----------------------------------------------------------------------
FLUSHALL : Clear the entire cache instantly.
-----------------------------------------------------------------------
exit : Leave the Redis console.
-----------------------------------------------------------------------
2. Running the Java App (Local)

Open Spring Tool Suite (STS).

File > Import > Maven > Existing Maven Projects.

Select the mealExplorer directory.

Right-click MealExplorerApplication.java -> Run As -> Spring Boot App.

3. Running via Terminal (Maven Wrapper)

If you prefer the command line:
-----------------------------------------------------------------------
./mvnw spring-boot:run
-----------------------------------------------------------------------

Docker Deployment (Java App - Optional)

Note: This step is only if you want to containerize the Java Backend itself. For development, skip this and run locally.

Build the JAR artifact:
-----------------------------------------------------------------------
./mvnw clean package -DskipTests
-----------------------------------------------------------------------

Create Dockerfile:
------------------------------------------------------
FROM openjdk:8-jdk-alpine
COPY target/mealExplorer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
-----------------------------------------------------

Build & Run:
----------------------------------------------------------------------
docker build -t meal-explorer-api .
docker run -p 8001:8001 --link redis_local:redis meal-explorer-api
-----------------------------------------------------------------------

🔗 Key Endpoints

The server runs on Port 8001 by default.

Swagger UI : Interactive API Documentation & Testing
------------------------------------------------
http://localhost:8001/swagger-ui/index.html
------------------------------------------------

Cache Clear : Administrative endpoint to flush Redis
-----------------------------------------------
http://localhost:8001/api/v1/meals/cache/clear
-----------------------------------------------

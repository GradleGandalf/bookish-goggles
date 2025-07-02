# RabbitMQ Spring Boot POC

This proof of concept demonstrates a basic message pipeline using Spring Boot 3.5, RabbitMQ and PostgreSQL. It exposes a secure REST endpoint that publishes messages to RabbitMQ. A listener consumes messages and persists them to PostgreSQL.

## Prerequisites
- Java 21
- Maven 3.9+
- Docker (for RabbitMQ and PostgreSQL)

## Running the Demo

1. Start the infrastructure:
   ```bash
   docker-compose up -d
   ```
2. Build and run the application:
   ```bash
   cd rabbitmq-poc
   ./mvnw spring-boot:run
   ```
3. Send a test request:
   ```bash
   curl -u user:password -H 'Content-Type: application/json' \
     -d '{"content":"hello"}' http://localhost:8080/api/messages
   ```

### Load Test
To approximate 200 transactions per second:
```bash
./loadtest.sh
```
The script uses [hey](https://github.com/rakyll/hey) to generate load.

## Security
Basic authentication is enabled with a default user (`user`/`password`). Update `SecurityConfig` for production usage.

## Project Structure
- `rabbitmq-poc/` – Spring Boot application
- `docker-compose.yml` – RabbitMQ and PostgreSQL services
- `loadtest.sh` – helper script for a simple benchmark

This setup is intended for experimentation and should be tuned and hardened before any production use.

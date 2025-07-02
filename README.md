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
   *(On Windows use `mvnw.cmd` instead of `./mvnw`.)*
3. Send a test request:
   ```bash
   curl -u user:password -H 'Content-Type: application/json' \
     -d '{"content":"hello"}' http://localhost:8080/api/messages
   ```
   *PowerShell users:* call `curl.exe` or use `Invoke-RestMethod` to avoid quoting
   issues.

4. Try the simple web UI at `http://localhost:8080/index.html`. Press **Start**
   to send one request per second for 12 seconds and watch the counter and
   elapsed time update live.

5. Alternatively, run the `SingleRun.java` script which performs the same test
   from the command line:
   ```bash
   cd rabbitmq-poc
   java SingleRun.java
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

### Windows notes
- Use `mvnw.cmd` for Maven commands.
- `docker-compose` can be run from PowerShell or WSL.
- The [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) in VS Code makes editing and debugging easier but is optional.

# GameSetMatch
[![Gradle Test](https://github.com/JeremySomsouk/GameSetMatch/actions/workflows/gradle-test.yml/badge.svg)](https://github.com/JeremySomsouk/GameSetMatch/actions/workflows/gradle-test.yml)

GameSetMatch is my clean implementation of tennis scoring rules built with Spring Boot and hexagonal architecture. The project demonstrates domain-driven design principles by modeling the core tennis scoring logic (15, 30, 40, Deuce, Advantage, Game) in a pure domain layer, completely isolated from infrastructure concerns. The API exposes REST endpoints to create games, score points, and retrieve current scores, with (a sample of) error handling. The application follows best practices like dependency injection and clear separation of concerns, making it easy to test and maintain. While currently using in-memory storage for simplicity, the design allows for easy replacement with a persistent database or other storage solutions. The project includes comprehensive tests (unit only for now) and serves as a practical demonstration of how to structure a Java application with clean architecture principles.

**Disclaimer**: This implementation goes beyond the basic kata requirement of "a simple tennis score computer." The purpose of this implementation was to demonstrate some of the few patterns I find important that make the code: **maintainable**, **testable**, and **extensible**.

For the exact minimal solution matching the kata requirements ("take a String of 'A's and 'B's and print scores"), see the [simple version here](src/main/java/com/jsomsouk/tennis/kata/GameSetMatch/SimpleGame.java).

## Features
- Create tennis games with unique IDs
- Track scores (0, 15, 30, 40, Deuce, Advantage, Game)
- REST API with proper error handling
- In-memory storage (easy to replace with a database)

## Tech Stack
- Java 21
- Spring Boot 3
- Hexagonal Architecture
- Domain-Driven Design
- JUnit 5 + Mockito
- Swagger/OpenAPI documentation

## Quick Start
### Prerequisites
- Java 21
- Gradle 8+

### Run Locally

```bash
# Clone the repository
git clone https://github.com/JeremySomsouk/GameSetMatch.git

cd GameSetMatch

# Build and run
./gradlew bootRun
```

Run the SimpleGame.java file to see the output of the kata.
```bash
# Default run defined in the gradle task "AABBAA"
./gradlew runKata

# Player A wins
./gradlew runKata -PkataArgs="AAAA"

# Player B wins
./gradlew runKata -PkataArgs="BBBBB"

# Deuce
./gradlew runKata -PkataArgs="ABABAB"

# Advantage B
./gradlew runKata -PkataArgs="ABABABB"

# Advantage A
./gradlew runKata -PkataArgs="ABABABA"

# Invalid input
./gradlew runKata -PkataArgs="ZZZ"

```

### Access Swagger UI
http://localhost:8080/swagger-ui.html

### Query the API
[Create a game]
```bash
curl -XPOST localhost:8080/api/v1/games/tennis
```

[Play a ball] 
```bash
curl -X GET \
  http://localhost:8080/api/v1/games/tennis/96d7e4ea-e3c6-472e-abdd-6d0fff3b4ad1/play \
  -H "Content-Type: application/json" \
  -d '{"winner": "A"}'
```

[Retrieve game info]
```bash
curl -XGET localhost:8080/api/v1/games/tennis/96d7e4ea-e3c6-472e-abdd-6d0fff3b4ad1
```

## API Endpoints
   Endpoint | Method | Description | Example Request |
 |----------|--------|-------------|-----------------|
| `/api/v1/games/tennis` | POST | Create new game | ```curl -X POST http://localhost:8080/api/v1/games/tennis``` |
| `/api/v1/games/tennis/{gameId}/play` | POST | Score a point | ```curl -X POST http://localhost:8080/api/v1/games/tennis/{gameId}/play -H "Content-Type: application/json" -d '{"winner": "A"}'``` |
| `/api/v1/games/tennis/{gameId}` | GET | Get current score | ```curl http://localhost:8080/api/v1/games/tennis/{gameId}``` |


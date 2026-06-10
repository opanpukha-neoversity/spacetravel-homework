# SpaceTravel Homework №4

Spring Boot project for interplanetary ticket booking.

## Stack

- Java 17
- Gradle
- Spring Boot
- Hibernate / Spring Data JPA
- H2
- Flyway

## How to run

```bash
gradle clean test
gradle bootRun
```

H2 console:

```text
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:file:./data/spacetravel
User: sa
Password: empty
```

## What is implemented

- Flyway migration `V1__create_db.sql` creates tables `client`, `planet`, `ticket`.
- Flyway migration `V2__populate_db.sql` inserts 10 clients, 5 planets and 10 tickets.
- Entities: `Client`, `Planet`, `Ticket`.
- CRUD services: `ClientCrudService`, `PlanetCrudService`, `TicketCrudService`.
- Ticket validation: client/from planet/to planet must not be null and must exist in DB.

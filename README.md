# Mechanic Booking Service — Java Assignment

This repository contains a small Swing-based Java application used for a programming assignment in a Java course. The application models a simple mechanic booking system where users can create, edit, list and manage vehicle service bookings.

## Quick facts

- Language: Java 21
- Build tool: Maven
- UI: Swing
- Logging: Logback (configured in `src/main/resources/logback.xml`)

## Prerequisites

- JDK 21 installed (the project is compiled with `maven.compiler.source`/`target` = 21).
- Maven 3.6+ installed.

Check Java version:

```bash
java -version
```

## Build

Build the project (compile only):

```bash
mvn -DskipTests package
```

## Run

You can run the GUI application from Maven:

```bash
mvn -DskipTests exec:java -Dexec.mainClass="com.example.Main"
```

Or run the packaged artifact from target (if packaged as jar):

```bash
# Example if you produce an executable jar
java -jar target/mechanic_booking_service-1.0-SNAPSHOT.jar
```

Note: the project uses Swing for UI; start it from an environment that supports a desktop display.

## Tests

Run unit tests with Maven:

```bash
mvn test
```

The test sources are under `src/test/java` and use JUnit 5 and Mockito.

## Logging

Logging is configured via `src/main/resources/logback.xml`:

- Active log file: `logs/latest.log`
- Archived logs: `logs/archive/` (rotated on application start or by the configured policy)

The configuration attempts to keep a limited set of archived logs. If you need to change rotation or retention, edit `logback.xml`.

## Project layout

```
pom.xml
src/main/java/com/example/  # application code
  Main.java
  BookingService.java
  BookingRepository.java
  Models/                   # Booking, Vehicle, Email, RegNr
  views/                    # Swing UI (Menu, BookingListView, Add/Edit dialogs)
src/main/resources/
  logback.xml               # logging config
src/test/java/              # unit tests
```

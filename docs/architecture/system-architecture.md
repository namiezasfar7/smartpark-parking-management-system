# SmartPark System Architecture

## 1. Overview

SmartPark uses a layered architecture to separate responsibilities within the application.

The main structure is:

```text
+---------------------------+
|       User Interface      |
|        Java Swing         |
+-------------+-------------+
              |
              v
+---------------------------+
|        Controllers        |
+-------------+-------------+
              |
              v
+---------------------------+
|          Services         |
|       Business Logic      |
+-------------+-------------+
              |
              v
+---------------------------+
|        Repositories       |
|      Data Management      |
+-------------+-------------+
              |
              v
+---------------------------+
|           Models          |
|       Domain Objects      |
+---------------------------+
```

---

## 2. Model Layer

The model layer represents the main entities in the parking system.

### Vehicle

Represents a registered vehicle.

Main information includes:

* Registration number
* Owner name
* Vehicle type

### ParkingSpace

Represents an individual parking space.

Main information includes:

* Space ID
* Vehicle type
* Parking-space status

### ParkingSession

Represents a vehicle's parking session.

Main information includes:

* Session ID
* Vehicle
* Parking space
* Entry time
* Exit time
* Session status

---

## 3. Repository Layer

Repositories provide storage and retrieval operations for domain objects.

Examples include:

* `VehicleRepository`
* `ParkingSpaceRepository`
* `ParkingSessionRepository`

The service layer communicates with repositories instead of directly managing the underlying storage implementation.

---

## 4. Service Layer

The service layer contains application business logic.

### VehicleService

Responsible for:

* Registering vehicles
* Preventing duplicate registrations
* Finding vehicles
* Retrieving registered vehicles

### ParkingService

Responsible for:

* Adding parking spaces
* Finding parking spaces
* Retrieving parking spaces
* Updating parking-space status

### ParkingSessionService

Responsible for:

* Starting parking sessions
* Checking vehicle availability
* Checking parking-space availability
* Preventing duplicate active sessions
* Completing sessions
* Releasing parking spaces

### AnalyticsService

Responsible for:

* Parking-space statistics
* Session statistics
* Average parking duration
* Date-based session statistics
* Seven-day activity statistics

---

## 5. Controller Layer

Controllers connect the Swing user interface to the service layer.

The UI communicates with controllers rather than directly accessing repositories.

This keeps UI code separate from business logic and ensures that business rules remain within the service layer.

---

## 6. Utility Layer

`ValidationUtil` provides reusable validation methods.

Validation is centralized so that common validation rules do not have to be duplicated throughout the application.

---

## 7. Exception Layer

Custom exceptions are used for specific invalid business operations.

Examples include:

* `ActiveSessionException`
* `ParkingSpaceUnavailableException`
* `VehicleNotFoundException`

These allow the application to distinguish between different failure conditions and provide appropriate feedback to the user.

---

## 8. User Interface

The application uses Java Swing to provide the graphical interface.

The main application areas include:

* Dashboard
* Parking
* Vehicles
* Sessions
* Analytics

The UI provides forms, tables, buttons, status information, and user feedback.

---

## 9. Design Principles

The project demonstrates the following object-oriented and software design principles:

* Encapsulation
* Separation of responsibilities
* Object-oriented modelling
* Layered architecture
* Reusable validation
* Exception handling
* Dependency injection through constructors
* Repository abstraction

These principles help keep the application modular, maintainable, and easier to extend.

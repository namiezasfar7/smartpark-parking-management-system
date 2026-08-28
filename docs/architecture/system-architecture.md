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
+-------------+-------------+
              |
              v
+---------------------------+
|           MySQL           |
|     Persistent Storage    |
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
* Zone ID
* Vehicle type
* Parking-space status

### ParkingZone

Represents a parking zone within the parking facility.

Main information includes:

* Zone ID
* Zone name

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

Repository interfaces provide an abstraction between the service layer and the underlying storage implementation.

Examples include:

* `VehicleRepository`
* `ParkingSpaceRepository`
* `ParkingZoneRepository`
* `ParkingSessionRepository`

SmartPark supports repository implementations for application data management.

The MySQL implementation is located under:

```text
com.smartpark.repository.mysql
```

The main MySQL repositories are:

* `MySQLVehicleRepository`
* `MySQLParkingSpaceRepository`
* `MySQLParkingZoneRepository`
* `MySQLParkingSessionRepository`

---

## 4. Database Layer

SmartPark v1.1.0 introduces persistent MySQL database storage.

The application connects to MySQL through JDBC.

Database connectivity is centralized through:

```text
com.smartpark.util.DatabaseConnection
```

The database connection uses:

```text
Host: localhost
Port: 3306
Database: smartpark
Username: root
```

The database password is obtained from:

```text
SMARTPARK_DB_PASSWORD
```

This prevents the database password from being hard-coded in the source code.

The main database tables are:

* `vehicles`
* `parking_spaces`
* `parking_zones`
* `parking_sessions`

---

## 5. Service Layer

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

## 6. Controller Layer

Controllers connect the Swing user interface to the service layer.

The UI communicates with controllers rather than directly accessing repositories.

This keeps UI code separate from business logic and ensures that business rules remain within the service layer.

---

## 7. Utility Layer

The utility layer provides reusable application functionality.

### ValidationUtil

Provides centralized validation methods for common application inputs.

### DatabaseConnection

Provides JDBC connections to the SmartPark MySQL database.

---

## 8. Exception Layer

Custom exceptions are used for specific invalid business operations.

Examples include:

* `ActiveSessionException`
* `ParkingSpaceUnavailableException`
* `VehicleNotFoundException`

These allow the application to distinguish between different failure conditions and provide appropriate feedback to the user.

---

## 9. User Interface

The application uses Java Swing to provide the graphical interface.

The main application areas include:

* Dashboard
* Parking
* Vehicles
* Sessions
* Analytics

The UI provides forms, tables, buttons, status information, filtering controls and user feedback.

The parking interface also provides visual differentiation for occupied parking spaces.

---

## 10. Data Flow

A typical operation follows this structure:

```text
User
 |
 v
Swing UI
 |
 v
Controller
 |
 v
Service
 |
 v
Repository
 |
 v
MySQL
```

For example, when a vehicle is registered:

```text
VehiclePanel
     |
     v
VehicleController
     |
     v
VehicleService
     |
     v
VehicleRepository
     |
     v
MySQLVehicleRepository
     |
     v
MySQL
```

This structure keeps database-specific code outside the UI and service layers.

---

## 11. Design Principles

The project demonstrates the following object-oriented and software design principles:

* Encapsulation
* Separation of responsibilities
* Object-oriented modelling
* Layered architecture
* Reusable validation
* Exception handling
* Dependency injection through constructors
* Repository abstraction
* Persistent data management

These principles help keep the application modular, maintainable, and easier to extend.
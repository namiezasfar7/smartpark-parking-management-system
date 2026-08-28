# SmartPark Development Log

## 1. Project Start

The SmartPark project was developed as an Object-Oriented Programming coursework project.

The initial objective was to create a desktop Parking Management System using Java and Java Swing.

The project was organized into separate packages for models, repositories, services, controllers, UI components, utilities and exceptions.

---

## 2. Domain Models

The first major development stage involved creating the core domain models.

The main models developed were:

* `Vehicle`
* `ParkingSpace`
* `ParkingZone`
* `ParkingSession`

Supporting enumerations were also introduced for vehicle and parking/session statuses.

The models were designed using encapsulated private attributes with appropriate constructors, getters and setters.

---

## 3. Vehicle Management

Vehicle management was implemented through:

* `Vehicle`
* `VehicleRepository`
* `VehicleService`
* `VehicleController`
* `VehiclePanel`

Vehicle registration was implemented with duplicate-registration protection.

The UI was designed to allow users to enter a registration number and select a vehicle type.

Validation was reviewed during development so that the validation rules matched the information collected by the current vehicle-registration workflow.

---

## 4. Parking Space Management

Parking-space management was implemented through the parking-space model, repository, service, controller and UI.

Parking spaces support multiple statuses:

* `AVAILABLE`
* `OCCUPIED`
* `OUT_OF_SERVICE`

New parking spaces initially begin in the available state.

The parking service provides functionality for adding spaces, finding spaces, retrieving all spaces and updating their statuses.

Zone-based filtering was also implemented in the parking interface.

---

## 5. Parking Sessions

Parking-session functionality was implemented.

A parking session contains:

* Session ID
* Vehicle
* Parking space
* Entry time
* Exit time
* Session status

New sessions begin with an `ACTIVE` status.

The session service performs several business checks before starting a session.

These include:

1. Checking that the session exists.
2. Checking that a vehicle is associated with the session.
3. Checking that the vehicle does not already have an active session.
4. Checking that a parking space is associated with the session.
5. Checking that the parking space exists.
6. Checking that the parking space is available.

Once the checks succeed, the parking space is marked as occupied and the session is stored.

---

## 6. Session Completion

Session completion was implemented so that an active session can be completed.

When completed:

* The exit time is recorded.
* The session status changes to `COMPLETED`.
* The associated parking space becomes `AVAILABLE`.

This keeps parking-space state synchronized with parking-session state.

---

## 7. Validation and Exceptions

A reusable `ValidationUtil` was introduced to centralize common validation rules.

The utility validates:

* Vehicles
* Registration numbers
* Parking spaces
* Parking-space IDs
* Parking-space statuses

Custom exceptions were also introduced for business-specific errors.

Examples include:

* `ActiveSessionException`
* `ParkingSpaceUnavailableException`
* `VehicleNotFoundException`

---

## 8. Analytics

An analytics service was implemented to provide information about the current parking system.

The analytics functionality includes:

* Total spaces
* Available spaces
* Occupied spaces
* Out-of-service spaces
* Total sessions
* Active sessions
* Completed sessions
* Average session duration
* Sessions for a specific date
* Last seven days of session activity

Parking-session duration is calculated from the recorded entry and exit times.

---

## 9. Graphical Interface

The Java Swing interface was developed to provide a user-friendly desktop experience.

The main sections include:

* Dashboard
* Parking
* Vehicles
* Sessions
* Analytics

The interface was styled consistently using a shared UI theme.

Tables are used to display registered vehicles, parking spaces and sessions.

UI inconsistencies identified during development were corrected.

The parking interface was also improved so that occupied spaces have clearer visual differentiation from available spaces.

---

## 10. MySQL Database Integration

For version 1.1.0, persistent MySQL database integration was introduced.

The application now includes MySQL repository implementations for:

* Vehicles
* Parking spaces
* Parking zones
* Parking sessions

The database connection is handled through:

```text
DatabaseConnection
```

The application connects to the `smartpark` MySQL database using JDBC.

The database password is obtained from:

```text
SMARTPARK_DB_PASSWORD
```

This prevents the database password from being stored directly in the source code.

---

## 11. Database Testing

Database integration was tested by running the application against a local MySQL database.

Stored records were inspected through MySQL Workbench.

The main database tables tested were:

* `vehicles`
* `parking_zones`
* `parking_spaces`
* `parking_sessions`

The database was also reset during development to verify that application data could be recreated correctly.

---

## 12. Bug Fixes

Several bugs and inconsistencies identified during development were corrected.

The v1.1.0 update includes:

* Database-related fixes
* UI consistency fixes
* Parking-space display improvements
* General application bug fixes
* Database repository corrections

---

## 13. Integration Testing

The completed components were integrated and tested through the running application.

The parking-session workflow was verified by:

1. Selecting a registered vehicle.
2. Selecting an available parking space.
3. Starting a parking session.
4. Confirming the active session appears in the interface.
5. Confirming the parking space becomes occupied.
6. Completing the session.
7. Confirming the session becomes completed.
8. Confirming the parking space becomes available again.
9. Confirming the corresponding database records are updated.

---

## 14. Version 1.0.0

The first stable release was designated:

```text
v1.0.0
```

Version 1.0.0 represented the completed initial implementation of SmartPark.

---

## 15. Version 1.1.0

Version 1.1.0 introduces improvements to the initial implementation.

The main changes are:

* MySQL database integration
* Persistent storage
* MySQL repository implementations
* Database connection configuration
* UI consistency fixes
* General bug fixes
* Improved occupied parking-space presentation
* Updated project documentation

Version 1.1.0 is intended as a minor feature release following the initial `v1.0.0` release.

---

## 16. Release Preparation

Before the v1.1.0 release:

1. The application was compiled.
2. Database connectivity was tested.
3. Main application workflows were tested.
4. Parking-space status changes were verified.
5. Session completion was tested.
6. Database records were inspected.
7. UI inconsistencies were corrected.
8. Documentation was updated.
9. The project version was updated to `1.1.0`.

The release is prepared for merging from `develop` into `main`.
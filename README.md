# SmartPark

## Parking Management System

SmartPark is a Java-based Parking Management System developed as part of an Object-Oriented Programming coursework project.

The system provides a graphical desktop application for managing vehicles, parking spaces, parking sessions, and parking-related analytics.

The project was developed using object-oriented design principles and a feature-branch Git workflow, with `develop` used for active development and `main` reserved for stable releases.

---

## Features

### Vehicle Management

* Register vehicles
* Validate vehicle registration numbers
* Prevent duplicate vehicle registrations
* Select vehicle types
* View all registered vehicles

### Parking Space Management

* Add parking spaces
* Assign vehicle types to parking spaces
* Track parking space availability
* Track occupied spaces
* Track out-of-service spaces
* Update parking space status

### Parking Session Management

* Start parking sessions
* Assign vehicles to parking spaces
* Automatically record entry time
* Prevent vehicles from having multiple active sessions
* Prevent unavailable parking spaces from being used
* Complete parking sessions
* Automatically record exit time
* Automatically release parking spaces when sessions are completed

### Analytics

* View total parking spaces
* View available parking spaces
* View occupied parking spaces
* View out-of-service parking spaces
* View total parking sessions
* View active sessions
* View completed sessions
* Calculate average parking duration
* View parking session counts for specific dates
* View session activity for the previous seven days

### Validation and Exception Handling

The application includes centralized input validation and custom exception handling for invalid parking operations.

Examples include:

* Invalid vehicle registration numbers
* Invalid parking space IDs
* Invalid parking space status values
* Null vehicle or parking-space objects
* Duplicate vehicle registrations
* Vehicles with existing active sessions
* Unavailable parking spaces

---

## Technologies

| Technology   | Purpose                                      |
| ------------ | -------------------------------------------- |
| Java 26      | Application development                      |
| Java Swing   | Graphical user interface                     |
| Apache Maven | Build and dependency management              |
| Git          | Version control                              |
| GitHub       | Source-code hosting and collaboration        |
| MySQL        | Planned/optional future database integration |

---

## Architecture

SmartPark follows a layered object-oriented architecture:

```text
                    User Interface
                          │
                          ▼
                     Controllers
                          │
                          ▼
                       Services
                          │
                          ▼
                     Repositories
                          │
                          ▼
                        Models
```

### Main Layers

#### Model

Contains the application's core domain objects, including:

* `Vehicle`
* `ParkingSpace`
* `ParkingSession`
* Vehicle status enumerations
* Parking status enumerations
* Vehicle type enumerations

#### Repository

Responsible for storing and retrieving application objects.

#### Service

Contains the main business logic for:

* Vehicle management
* Parking-space management
* Parking-session management
* Analytics

#### Controller

Acts as the connection between the graphical user interface and the service layer.

#### UI

Contains the Java Swing-based graphical interface used by the user.

#### Utility

Contains reusable utility functionality such as `ValidationUtil`.

#### Exception

Contains custom exceptions used to represent invalid parking operations.

More details can be found in:

```text
docs/architecture/system-architecture.md
```

---

## Requirements

### Software Requirements

* Java Development Kit 26
* Apache Maven
* Git
* GitHub account for repository access

---

## Running the Application

### 1. Clone the Repository

```bash
git clone https://github.com/namiezasfar7/smartpark-parking-management-system.git
cd smartpark-parking-management-system
```

### 2. Build the Project

```bash
mvn clean package
```

### 3. Run the Application

The application can be run using the generated classes or directly through an IDE such as IntelliJ IDEA.

The project can also be opened directly in IntelliJ IDEA or another Java-compatible IDE.

---

## Project Structure

```text
smartpark-parking-management-system/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── smartpark/
│                   ├── controller/
│                   ├── exception/
│                   ├── model/
│                   ├── repository/
│                   ├── service/
│                   ├── ui/
│                   └── util/
│
├── docs/
│   ├── architecture/
│   ├── development/
│   ├── requirements/
│   ├── screenshots/
│   └── testing/
│
├── pom.xml
├── LICENSE
├── README.md
└── LICENSE
```

---

## Git Workflow

The project uses a feature-branch workflow.

```text
main
 │
 └── develop
      │
      ├── feature/...
      ├── fix/...
      └── exception/...
```

### Branch Responsibilities

| Branch        | Responsibility                         |
| ------------- | -------------------------------------- |
| `main`        | Stable released versions               |
| `develop`     | Active development                     |
| `feature/*`   | Individual features                    |
| `fix/*`       | Bug fixes                              |
| `exception/*` | Exception-handling related development |

Changes are developed and tested on their respective branches before being merged into `develop`.

Once the release is considered stable, `develop` is merged into `main` and tagged with a release version.

For more information, see:

```text
docs/development/git-workflow.md
```

---

## Documentation

Complete project documentation is available in the `docs/` directory.

| Document                 | Description                                             |
| ------------------------ | ------------------------------------------------------- |
| `system-architecture.md` | Application architecture and component responsibilities |
| `development-log.md`     | Development history and major implementation stages     |
| `git-workflow.md`        | Git branching, commits, merging and release workflow    |
| `requirements.md`        | Functional and non-functional requirements              |
| `test-plan.md`           | Testing strategy and test cases                         |
| `screenshots/`           | Application screenshots                                 |

---

## Team

| Member    | Responsibility                                   |
| --------- | ------------------------------------------------ |
| Namiez    | Team Lead, UI Design, Development, Documentation |
| Amasha    | Development                                      |
| Lakmina   | Development, Business Analysis                   |
| Sheshanth | Development, Documentation                       |

---

## Project Status

**Version:** `1.0.0`

**Status:** Stable Release

SmartPark `v1.0.0` represents the completed initial version of the Parking Management System.

### Future Improvements

Future versions may include:

* Persistent database storage
* Additional reporting functionality
* Improved validation
* Further UI enhancements
* Expanded parking analytics

---

## License

This project is licensed under the **MIT License**.

See the `LICENSE` file for the complete license text.

---

## Educational Purpose

SmartPark was developed for educational purposes as part of an Object-Oriented Programming coursework project.

The project demonstrates:

* Object-oriented programming
* Layered application design
* GUI development
* Input validation
* Exception handling
* Version control
* Git branching and collaboration
* Software documentation
* Team-based software development

---

## Acknowledgements

This project was developed collaboratively as part of an Object-Oriented Programming coursework project.

© 2026 SmartPark Contributors
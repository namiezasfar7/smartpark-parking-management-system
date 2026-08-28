# SmartPark

## Parking Management System

SmartPark is a Java-based Parking Management System developed as part of an Object-Oriented Programming coursework project.

The system provides a graphical desktop application for managing vehicles, parking spaces, parking sessions, and parking-related analytics.

SmartPark uses a layered object-oriented architecture with Java Swing for the graphical interface and MySQL for persistent data storage.

The project uses a feature-branch Git workflow, with `develop` used for active development and `main` reserved for stable releases.

---

## Features

### Vehicle Management

* Register vehicles
* Validate vehicle registration numbers
* Prevent duplicate vehicle registrations
* Select vehicle types
* View all registered vehicles
* Store vehicle information in MySQL

### Parking Space Management

* Add parking spaces
* Assign vehicle types to parking spaces
* Track parking space availability
* Track occupied spaces
* Track out-of-service spaces
* Update parking space status
* Store parking-space information in MySQL
* Display parking-space status clearly in the user interface
* Filter parking spaces by zone

### Parking Session Management

* Start parking sessions
* Assign vehicles to parking spaces
* Automatically record entry time
* Prevent vehicles from having multiple active sessions
* Prevent unavailable parking spaces from being used
* Complete parking sessions
* Automatically record exit time
* Automatically release parking spaces when sessions are completed
* Store parking-session information in MySQL

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

### Database Integration

SmartPark v1.1.0 introduces MySQL database integration for persistent storage.

The application uses:

* MySQL
* MySQL Connector/J
* JDBC
* A database connection utility
* MySQL repository implementations

The main database repositories are:

* `MySQLVehicleRepository`
* `MySQLParkingSpaceRepository`
* `MySQLParkingZoneRepository`
* `MySQLParkingSessionRepository`

Database connection information is managed by `DatabaseConnection`.

The database password is read from the `SMARTPARK_DB_PASSWORD` environment variable rather than being stored directly in the source code.

See:

```text
docs/database/mysql-setup.md
```

for database setup instructions.

---

## Validation and Exception Handling

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

| Technology | Purpose |
| ---------- | ------- |
| Java 26 | Application development |
| Java Swing | Graphical user interface |
| Apache Maven | Build and dependency management |
| MySQL | Persistent database storage |
| MySQL Connector/J | Java-to-MySQL database connectivity |
| JDBC | Database access |
| Git | Version control |
| GitHub | Source-code hosting and collaboration |

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
                          │
                          ▼
                         MySQL
```

### Main Layers

#### Model

Contains the application's core domain objects, including:

* `Vehicle`
* `ParkingSpace`
* `ParkingZone`
* `ParkingSession`
* `VehicleType`
* `ParkingSpaceStatus`
* `ParkingSessionStatus`

#### Repository

Responsible for storing and retrieving application objects.

Repository interfaces provide an abstraction between the service layer and the data-storage implementation.

MySQL implementations provide persistent database access.

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

The main interface areas include:

* Dashboard
* Parking
* Vehicles
* Sessions
* Analytics

#### Utility

Contains reusable utility functionality, including:

* `ValidationUtil`
* `DatabaseConnection`

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
* MySQL Server
* MySQL Workbench or another MySQL client
* Git
* GitHub account for repository access

---

## Database Setup

SmartPark v1.1.0 requires a MySQL database.

The application expects the following database connection:

```text
Host: localhost
Port: 3306
Database: smartpark
Username: root
Password: SMARTPARK_DB_PASSWORD environment variable
```

The database and required tables must be created before running the application.

Database setup instructions are available in:

```text
docs/database/mysql-setup.md
```

The database password should not be committed to Git.

Set the environment variable before running the application.

### Windows

Command Prompt:

```cmd
set SMARTPARK_DB_PASSWORD=your_password
```

PowerShell:

```powershell
$env:SMARTPARK_DB_PASSWORD="your_password"
```

For IntelliJ IDEA, the environment variable can be added to the application's Run/Debug Configuration.

---

## Running the Application

### 1. Clone the Repository

```bash
git clone https://github.com/namiezasfar7/smartpark-parking-management-system.git
cd smartpark-parking-management-system
```

### 2. Configure MySQL

Create the `smartpark` database and required tables.

Follow:

```text
docs/database/mysql-setup.md
```

### 3. Configure the Database Password

Set:

```text
SMARTPARK_DB_PASSWORD
```

to the password of the MySQL user configured for SmartPark.

### 4. Build the Project

```bash
mvn clean package
```

### 5. Run the Application

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
│                   │   ├── memory/
│                   │   └── mysql/
│                   ├── service/
│                   ├── ui/
│                   └── util/
│
├── docs/
│   ├── architecture/
│   ├── database/
│   ├── development/
│   ├── requirements/
│   ├── screenshots/
│   └── testing/
│
├── pom.xml
├── LICENSE
└── README.md
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

| Branch | Responsibility |
| ------ | -------------- |
| `main` | Stable released versions |
| `develop` | Active development |
| `feature/*` | Individual features |
| `fix/*` | Bug fixes |
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

| Document | Description |
| -------- | ----------- |
| `system-architecture.md` | Application architecture and component responsibilities |
| `development-log.md` | Development history and major implementation stages |
| `git-workflow.md` | Git branching, commits, merging and release workflow |
| `mysql-setup.md` | MySQL database setup and configuration |
| `requirements.md` | Functional and non-functional requirements |
| `test-plan.md` | Testing strategy and test cases |
| `screenshots/` | Application screenshots |

---

## Team

| Member | Responsibility |
| ------ | -------------- |
| Namiez | Team Lead, UI Design, Development, Documentation |
| Amasha | Development |
| Lakmina | Development, Business Analysis |
| Sheshanth | Development, Documentation |

---

## Project Status

**Version:** `1.1.0`

**Status:** Stable Release

SmartPark `v1.1.0` builds upon the initial `v1.0.0` implementation by introducing persistent MySQL database integration, UI improvements and bug fixes.

### Future Improvements

Future versions may include:

* User authentication
* Parking fees and billing
* Additional reporting functionality
* Advanced parking analytics
* Search and filtering improvements
* Exporting analytics
* Additional vehicle categories

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
* Database integration
* JDBC
* MySQL persistence
* Version control
* Git branching and collaboration
* Software documentation
* Team-based software development

---

## Acknowledgements

This project was developed collaboratively as part of an Object-Oriented Programming coursework project.

© 2026 SmartPark Contributors
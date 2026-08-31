# SmartPark Requirements

## 1. Introduction

SmartPark is a desktop-based Parking Management System designed to provide a simple interface for managing vehicles, parking spaces and parking sessions.

The system was developed as an Object-Oriented Programming coursework project.

Version 1.1.0 introduces persistent MySQL database storage.

---

## 2. Functional Requirements

### FR-01 Vehicle Registration

The system shall allow users to register a vehicle using its registration number and vehicle type.

### FR-02 Vehicle Validation

The system shall validate vehicle registration information before registration.

### FR-03 Duplicate Vehicle Prevention

The system shall prevent a vehicle with an existing registration number from being registered again.

### FR-04 Parking Space Management

The system shall allow parking spaces to be added and managed.

### FR-05 Parking Space Status

The system shall maintain parking-space statuses including:

* `AVAILABLE`
* `OCCUPIED`
* `OUT_OF_SERVICE`

### FR-06 Parking Session Creation

The system shall allow a parking session to be started for a registered vehicle.

### FR-07 Parking Space Assignment

The system shall assign a parking space to an active parking session.

### FR-08 Parking Space Availability

The system shall prevent a session from using an unavailable parking space.

### FR-09 Active Session Prevention

The system shall prevent a vehicle from having multiple active parking sessions.

### FR-10 Entry Time

The system shall record the entry time of a parking session.

### FR-11 Session Completion

The system shall allow an active parking session to be completed.

### FR-12 Exit Time

The system shall record the exit time when a parking session is completed.

### FR-13 Space Release

The system shall make the associated parking space available after a session is completed.

### FR-14 Analytics

The system shall provide parking and session statistics.

### FR-15 Average Duration

The system shall calculate the average duration of completed parking sessions.

### FR-16 Date-Based Statistics

The system shall provide parking-session counts for selected dates and recent activity.

### FR-17 Persistent Storage

The system shall store application data using a MySQL database.

### FR-18 Database Retrieval

The system shall retrieve stored vehicles, parking spaces, parking zones and parking sessions from MySQL.

### FR-19 Database Session Updates

The system shall persist parking-session state changes, including session completion.

### FR-20 Database Parking-Space Updates

The system shall persist parking-space status changes.

### FR-21 User Login

The system shall provide a login screen before allowing access to the main application.

### FR-22 Login Validation

The system shall validate the entered username and password.

### FR-23 Login Credentials

The system shall allow access using the configured administrator credentials.

### FR-24 Invalid Login Handling

The system shall reject invalid login credentials and provide appropriate feedback to the user.

### FR-25 Application Access Control

The system shall display the main SmartPark application only after successful authentication.

---

## 3. Non-Functional Requirements

### Usability

The application should provide a clear graphical interface that allows users to perform common parking-management operations without interacting directly with the underlying code.

### Reliability

Invalid operations should be rejected through validation and exception handling.

### Maintainability

The application should separate user-interface, controller, business-logic, repository and model responsibilities.

### Persistence

Application data should remain available between application runs through MySQL database storage.

### Security

Database credentials should not be stored directly in the source code.

### Portability

The application should run on systems capable of running the required Java version and configured MySQL environment.

### Version Control

The source code should be maintained using Git with a structured branching workflow.

### Security

The login screen shall prevent unauthorized access to the main application by requiring valid credentials before displaying the system interface.

---

## 4. Future Requirements

Possible future enhancements include:

* User authentication
* Parking fees and billing
* Advanced reports
* Search and filtering improvements
* Exporting analytics
* Additional vehicle categories
* Additional parking analytics
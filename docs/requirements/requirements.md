# SmartPark Requirements

## 1. Introduction

SmartPark is a desktop-based Parking Management System designed to provide a simple interface for managing vehicles, parking spaces and parking sessions.

The system was developed as an Object-Oriented Programming coursework project.

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

- AVAILABLE
- OCCUPIED
- OUT_OF_SERVICE

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

---

## 3. Non-Functional Requirements

### Usability

The application should provide a clear graphical interface that allows users to perform common parking-management operations without interacting directly with the underlying code.

### Reliability

Invalid operations should be rejected through validation and exception handling.

### Maintainability

The application should separate user-interface, controller, business-logic, repository and model responsibilities.

### Portability

The application should run on systems capable of running the required Java version.

### Version Control

The source code should be maintained using Git with a structured branching workflow.

---

## 4. Future Requirements

Possible future enhancements include:

- Persistent database storage
- MySQL integration
- User authentication
- Parking fees and billing
- Advanced reports
- Search and filtering
- Exporting analytics
- Additional vehicle categories
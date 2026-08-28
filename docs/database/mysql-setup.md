# SmartPark MySQL Database Setup

## 1. Overview

SmartPark v1.1.0 uses MySQL for persistent storage.

The application connects to a local MySQL server using JDBC.

The default connection configuration is:

```text
Host: localhost
Port: 3306
Database: smartpark
Username: root
Password: SMARTPARK_DB_PASSWORD
```

The database password is not stored directly in the Java source code.

---

## 2. Requirements

Install:

* MySQL Server
* MySQL Workbench

The MySQL server should be running before starting SmartPark.

---

## 3. Create the Database

Open MySQL Workbench and connect to the local MySQL server.

Create the database:

```sql
CREATE DATABASE smartpark;
```

Select it:

```sql
USE smartpark;
```

---

## 4. Create the Tables

Create the required SmartPark tables.

The database contains the following main tables:

```text
vehicles
parking_zones
parking_spaces
parking_sessions
```

The table structure must match the fields expected by the MySQL repository classes.

The application expects the following fields.

### vehicles

```text
registration_number
owner_name
vehicle_type
```

### parking_zones

```text
zone_id
zone_name
```

### parking_spaces

```text
space_id
zone_id
vehicle_type
status
```

### parking_sessions

```text
session_id
registration_number
space_id
zone_id
entry_time
exit_time
status
```

Foreign-key relationships should be configured consistently with the database design.

---

## 5. Configure the Database Password

SmartPark reads the MySQL password from the environment variable:

```text
SMARTPARK_DB_PASSWORD
```

Do not place the password directly into the source code.

### Windows Command Prompt

```cmd
set SMARTPARK_DB_PASSWORD=your_password
```

### Windows PowerShell

```powershell
$env:SMARTPARK_DB_PASSWORD="your_password"
```

---

## 6. IntelliJ IDEA Configuration

If running SmartPark through IntelliJ IDEA:

1. Open the project.
2. Open the Run/Debug Configuration for `com.smartpark.Main`.
3. Find the environment-variable configuration.
4. Add:

```text
SMARTPARK_DB_PASSWORD=your_password
```

5. Apply the configuration.
6. Run the application.

---

## 7. Verify the Database

After starting the application, MySQL Workbench can be used to inspect the stored data.

Select the `smartpark` database and query the tables.

For example:

```sql
USE smartpark;

SELECT * FROM vehicles;

SELECT * FROM parking_zones;

SELECT * FROM parking_spaces;

SELECT * FROM parking_sessions;
```

These queries allow the stored application data to be inspected directly.

---

## 8. Clearing the Database

If the database needs to be reset during development, the tables must be cleared while respecting their foreign-key relationships.

One approach is:

```sql
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE parking_sessions;
TRUNCATE TABLE parking_spaces;
TRUNCATE TABLE parking_zones;
TRUNCATE TABLE vehicles;

SET FOREIGN_KEY_CHECKS = 1;
```

Use this only when the stored SmartPark data can be deleted.

---

## 9. Parking Space Data

The application expects the parking-space records in the database to match the parking layout used by the application.

For the current parking layout, there are:

```text
Ground Floor: 8 spaces
Level 1:      8 spaces
Level 2:      8 spaces
Level 3:      8 spaces
--------------------------------
Total:       32 spaces
```

The zone IDs used by the application are:

```text
GF
L1
L2
L3
```

---

## 10. Troubleshooting

### Application cannot connect to MySQL

Check:

* MySQL Server is running.
* MySQL is using port `3306`.
* The `smartpark` database exists.
* The configured username is correct.
* `SMARTPARK_DB_PASSWORD` is set correctly.

### Password is null

Make sure the environment variable is configured in the environment used to start the Java application.

For IntelliJ IDEA, check the Run/Debug Configuration.

### Tables are empty

Check that the correct database is selected:

```sql
USE smartpark;
```

Then inspect the tables:

```sql
SELECT * FROM vehicles;
SELECT * FROM parking_zones;
SELECT * FROM parking_spaces;
SELECT * FROM parking_sessions;
```

### Parking spaces are missing

Check the contents of:

```sql
SELECT * FROM parking_spaces;
```

The application reads parking spaces from the database, so the required parking-space records must exist.

---

## 11. Security

Database passwords should never be committed to Git.

Do not replace:

```java
System.getenv("SMARTPARK_DB_PASSWORD")
```

with a hard-coded password.

Each developer or installation should configure the database password locally.
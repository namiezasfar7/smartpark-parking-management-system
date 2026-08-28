# SmartPark Test Plan

## 1. Objective

The purpose of testing is to verify that the SmartPark application behaves correctly under normal and invalid usage conditions.

Testing focuses on vehicle management, parking-space management, parking sessions, validation, exception handling, analytics, database integration and the graphical interface.

---

## 2. Build Test

| Test | Expected Result |
| ---- | --------------- |
| Run `mvn clean package` | Project compiles successfully and package is generated |
| Launch application | Application starts without compilation/runtime errors |

---

## 3. Database Tests

| Test | Expected Result |
| ---- | --------------- |
| Connect to MySQL | Connection is established |
| Connect using configured database | `smartpark` database is accessed |
| Register vehicle | Vehicle is stored in `vehicles` |
| Add parking space | Space is stored in `parking_spaces` |
| Add parking zone | Zone is stored in `parking_zones` |
| Start parking session | Session is stored in `parking_sessions` |
| Complete parking session | Session status and exit time are updated |
| Complete parking session | Parking-space status becomes `AVAILABLE` |
| Retrieve stored vehicles | Correct vehicles are returned |
| Retrieve stored parking spaces | Correct spaces are returned |
| Retrieve stored sessions | Correct sessions are returned |
| Reset database | Database tables can be cleared successfully |

---

## 4. Vehicle Tests

| Test | Expected Result |
| ---- | --------------- |
| Register valid vehicle | Vehicle is registered |
| Register duplicate vehicle | Registration is rejected |
| Empty registration number | Validation warning/error |
| Null vehicle | Validation exception |
| Select vehicle type | Vehicle type is stored correctly |
| View registered vehicles | Vehicle appears in vehicle list |
| Restart application | Stored vehicle remains available |

---

## 5. Parking Space Tests

| Test | Expected Result |
| ---- | --------------- |
| Add valid parking space | Space is added |
| Add invalid space | Validation rejects invalid input |
| Find existing space | Correct space is returned |
| Find missing space | No space is returned |
| Update space status | Status changes correctly |
| Create new parking space | Initial status is `AVAILABLE` |
| View all zones | Parking spaces from all zones are displayed |
| Select a zone | Only spaces from the selected zone are displayed |
| Occupy a space | Space is clearly displayed as occupied |

---

## 6. Parking Session Tests

| Test | Expected Result |
| ---- | --------------- |
| Start valid session | Session becomes `ACTIVE` |
| Start session with missing vehicle | Operation is rejected |
| Start session with missing space | Operation is rejected |
| Start session using unavailable space | Operation is rejected |
| Start second active session for same vehicle | Operation is rejected |
| Start session | Parking space becomes `OCCUPIED` |
| Complete session | Session becomes `COMPLETED` |
| Complete session | Exit time is recorded |
| Complete session | Parking space becomes `AVAILABLE` |
| Complete already completed session | No duplicate completion occurs |

---

## 7. Analytics Tests

| Test | Expected Result |
| ---- | --------------- |
| Count total spaces | Correct total is returned |
| Count available spaces | Correct count is returned |
| Count occupied spaces | Correct count is returned |
| Count out-of-service spaces | Correct count is returned |
| Count total sessions | Correct total is returned |
| Count active sessions | Correct count is returned |
| Count completed sessions | Correct count is returned |
| Calculate average duration | Correct average is returned |
| Query sessions by date | Correct sessions are counted |
| Retrieve seven-day statistics | Seven values are returned |

---

## 8. User Interface Tests

The main UI workflows should be manually verified.

### Vehicle Management

* Open Vehicles
* Register a vehicle
* Verify it appears in the table
* Attempt duplicate registration
* Verify the duplicate is rejected

### Parking Management

* Open Parking
* View parking spaces
* Change the selected zone
* Verify the correct spaces are displayed
* Verify occupied spaces are visually distinguishable

### Session Management

* Select vehicle
* Select parking space
* Start session
* Verify active session appears
* Verify parking space becomes occupied
* Complete selected session
* Verify the session is completed
* Verify parking space becomes available

### Analytics

* Open Analytics
* Verify displayed statistics correspond to the current application state

---

## 9. Final Verification

Before release:

* [ ] Project compiles
* [ ] Application launches
* [ ] MySQL connection works
* [ ] Database tables exist
* [ ] Vehicle registration works
* [ ] Duplicate vehicles are rejected
* [ ] Vehicles are persisted in MySQL
* [ ] Parking spaces work
* [ ] Parking zones work
* [ ] Parking sessions work
* [ ] Active-session protection works
* [ ] Unavailable-space protection works
* [ ] Session completion works
* [ ] Parking spaces are released after completion
* [ ] Database records are updated
* [ ] Analytics display correctly
* [ ] Occupied spaces are clearly displayed
* [ ] Documentation is complete
* [ ] README is updated
* [ ] Version is set to `1.1.0`
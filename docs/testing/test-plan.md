# SmartPark Test Plan

## 1. Objective

The purpose of testing is to verify that the SmartPark application behaves correctly under normal and invalid usage conditions.

Testing focuses on vehicle management, parking-space management, parking sessions, validation, exception handling and analytics.

---

## 2. Build Test

| Test | Expected Result |
|---|---|
| Run `mvn clean package` | Project compiles successfully and package is generated |

---

## 3. Vehicle Tests

| Test | Expected Result |
|---|---|
| Register valid vehicle | Vehicle is registered |
| Register duplicate vehicle | Registration is rejected |
| Empty registration number | Validation warning/error |
| Null vehicle | Validation exception |
| Select vehicle type | Vehicle type is stored correctly |
| View registered vehicles | Vehicle appears in vehicle list |

---

## 4. Parking Space Tests

| Test | Expected Result |
|---|---|
| Add valid parking space | Space is added |
| Add invalid space | Validation rejects invalid input |
| Find existing space | Correct space is returned |
| Find missing space | No space is returned |
| Update space status | Status changes correctly |
| Create new parking space | Initial status is AVAILABLE |

---

## 5. Parking Session Tests

| Test | Expected Result |
|---|---|
| Start valid session | Session becomes ACTIVE |
| Start session with missing vehicle | Operation is rejected |
| Start session with missing space | Operation is rejected |
| Start session using unavailable space | Operation is rejected |
| Start second active session for same vehicle | Operation is rejected |
| Start session | Parking space becomes OCCUPIED |
| Complete session | Session becomes COMPLETED |
| Complete session | Exit time is recorded |
| Complete session | Parking space becomes AVAILABLE |
| Complete already completed session | No duplicate completion occurs |

---

## 6. Analytics Tests

| Test | Expected Result |
|---|---|
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

## 7. User Interface Tests

The main UI workflows should be manually verified.

### Vehicle Management

- Open Vehicles
- Register a vehicle
- Verify it appears in the table
- Attempt duplicate registration

### Parking Management

- Open Parking
- Add/manage parking spaces
- Verify status changes

### Session Management

- Select vehicle
- Select parking space
- Start session
- Verify active session appears
- Complete selected session
- Verify the session is completed

### Analytics

- Open Analytics
- Verify displayed statistics correspond to the current application state

---

## 8. Final Verification

Before release:

- [ ] Project compiles
- [ ] Application launches
- [ ] Vehicle registration works
- [ ] Duplicate vehicles are rejected
- [ ] Parking spaces work
- [ ] Parking sessions work
- [ ] Active-session protection works
- [ ] Unavailable-space protection works
- [ ] Session completion works
- [ ] Parking spaces are released after completion
- [ ] Analytics display correctly
- [ ] Documentation is complete
- [ ] README is updated
- [ ] Version is set to `1.0.0`
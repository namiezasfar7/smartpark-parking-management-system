CREATE DATABASE smartpark;

USE smartpark;

CREATE TABLE vehicles (
    registration_number VARCHAR(20) PRIMARY KEY,
    owner_name VARCHAR(100) NOT NULL,
    vehicle_type VARCHAR(30) NOT NULL
);

CREATE TABLE parking_zones (
    zone_id VARCHAR(20) PRIMARY KEY,
    zone_name VARCHAR(50) NOT NULL
);

INSERT INTO parking_zones (zone_id, zone_name) VALUES
	('GF', 'Ground Floor'),
	('L1', 'Level 1'),
	('L2', 'Level 2'),
	('L3', 'Level 3');

SELECT * FROM parking_zones;

CREATE TABLE parking_spaces (
    space_id VARCHAR(20) PRIMARY KEY,
    zone_id VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,

    FOREIGN KEY (zone_id)
        REFERENCES parking_zones(zone_id)
);
    
SELECT * FROM parking_spaces;

CREATE TABLE parking_sessions (
    session_id VARCHAR(20) PRIMARY KEY,
    registration_number VARCHAR(20) NOT NULL,
    space_id VARCHAR(20) NOT NULL,
    zone_id VARCHAR(20) NOT NULL,
    entry_time DATETIME NOT NULL,
    exit_time DATETIME,
    status VARCHAR(20) NOT NULL,

    FOREIGN KEY (registration_number)
        REFERENCES vehicles(registration_number),

    FOREIGN KEY (space_id)
        REFERENCES parking_spaces(space_id),

    FOREIGN KEY (zone_id)
        REFERENCES parking_zones(zone_id)
);

CREATE TABLE reservations (
    reservation_id VARCHAR(20) PRIMARY KEY,
    registration_number VARCHAR(20) NOT NULL,
    space_id VARCHAR(20) NOT NULL,
    zone_id VARCHAR(20) NOT NULL,
    reservation_time DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,

    FOREIGN KEY (registration_number)
        REFERENCES vehicles(registration_number),

    FOREIGN KEY (space_id)
        REFERENCES parking_spaces(space_id),

    FOREIGN KEY (zone_id)
        REFERENCES parking_zones(zone_id)
);

ALTER TABLE parking_spaces
ADD COLUMN vehicle_type VARCHAR(30) NOT NULL;

SHOW TABLES;

INSERT INTO parking_zones (zone_id, zone_name)
VALUES
('GF', 'Ground Floor'),
('L1', 'Level 1'),
('L2', 'Level 2'),
('L3', 'Level 3');

INSERT INTO parking_spaces (space_id, zone_id, vehicle_type, status)
VALUES

-- GROUND FLOOR
('GF01', 'GF', 'CAR', 'AVAILABLE'),
('GF02', 'GF', 'CAR', 'AVAILABLE'),
('GF03', 'GF', 'CAR', 'AVAILABLE'),
('GF04', 'GF', 'CAR', 'AVAILABLE'),
('GF05', 'GF', 'CAR', 'AVAILABLE'),
('GF06', 'GF', 'CAR', 'AVAILABLE'),
('GF07', 'GF', 'CAR', 'AVAILABLE'),
('GF08', 'GF', 'CAR', 'AVAILABLE'),

-- LEVEL 1
('L101', 'L1', 'CAR', 'AVAILABLE'),
('L102', 'L1', 'CAR', 'AVAILABLE'),
('L103', 'L1', 'CAR', 'AVAILABLE'),
('L104', 'L1', 'CAR', 'AVAILABLE'),
('L105', 'L1', 'CAR', 'AVAILABLE'),
('L106', 'L1', 'CAR', 'AVAILABLE'),
('L107', 'L1', 'CAR', 'AVAILABLE'),
('L108', 'L1', 'CAR', 'AVAILABLE'),

-- LEVEL 2
('L201', 'L2', 'CAR', 'AVAILABLE'),
('L202', 'L2', 'CAR', 'AVAILABLE'),
('L203', 'L2', 'CAR', 'AVAILABLE'),
('L204', 'L2', 'CAR', 'AVAILABLE'),
('L205', 'L2', 'CAR', 'AVAILABLE'),
('L206', 'L2', 'CAR', 'AVAILABLE'),
('L207', 'L2', 'CAR', 'AVAILABLE'),
('L208', 'L2', 'CAR', 'AVAILABLE'),

-- LEVEL 3
('L301', 'L3', 'CAR', 'AVAILABLE'),
('L302', 'L3', 'CAR', 'AVAILABLE'),
('L303', 'L3', 'CAR', 'AVAILABLE'),
('L304', 'L3', 'CAR', 'AVAILABLE'),
('L305', 'L3', 'CAR', 'AVAILABLE'),
('L306', 'L3', 'CAR', 'AVAILABLE'),
('L307', 'L3', 'CAR', 'AVAILABLE'),
('L308', 'L3', 'CAR', 'AVAILABLE');

SELECT * FROM parking_spaces;
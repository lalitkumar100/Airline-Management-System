CREATE DATABASE IF NOT EXISTS airline_db;
USE airline_db;

-- 1. AIRPORTS TABLE
CREATE TABLE airports (
    airport_code VARCHAR(10) PRIMARY KEY,
    airport_name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

-- 2. AIRCRAFTS TABLE
CREATE TABLE aircrafts (
    aircraft_id VARCHAR(20) PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    capacity INT NOT NULL CHECK (capacity > 0)
) ENGINE=InnoDB;

-- 3. ROUTES TABLE
CREATE TABLE routes (
    route_id INT AUTO_INCREMENT PRIMARY KEY,
    source_airport_code VARCHAR(10) NOT NULL,
    destination_airport_code VARCHAR(10) NOT NULL,
    CONSTRAINT fk_route_source FOREIGN KEY (source_airport_code) REFERENCES airports(airport_code) ON DELETE CASCADE,
    CONSTRAINT fk_route_dest FOREIGN KEY (destination_airport_code) REFERENCES airports(airport_code) ON DELETE CASCADE,
    CONSTRAINT uq_route UNIQUE (source_airport_code, destination_airport_code)
) ENGINE=InnoDB;

-- 4. LOYALTY ACCOUNTS TABLE
CREATE TABLE loyalty_accounts (
    loyalty_id INT AUTO_INCREMENT PRIMARY KEY,
    points INT DEFAULT 0,
    tier ENUM('Silver', 'Gold', 'Diamond') DEFAULT 'Silver'
) ENGINE=InnoDB;

-- 5. PASSENGERS TABLE
CREATE TABLE passengers (
    passenger_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    date_of_birth DATE NULL,
    bank_name VARCHAR(100) DEFAULT 'State Bank of India',
    account_number VARCHAR(50) DEFAULT '12334678AC',
    password VARCHAR(255) NOT NULL,
    loyalty_id INT UNIQUE NULL,
    CONSTRAINT fk_passenger_loyalty FOREIGN KEY (loyalty_id) REFERENCES loyalty_accounts(loyalty_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- 6. FLIGHTS TABLE
CREATE TABLE flights (
    flight_id VARCHAR(20) PRIMARY KEY,
    source_airport_code VARCHAR(10) NOT NULL,
    destination_airport_code VARCHAR(10) NOT NULL,
    departure_date_time DATETIME NOT NULL,
    arrival_date_time DATETIME NOT NULL,
    aircraft_id VARCHAR(20) NOT NULL,
    base_fare DECIMAL(10, 2) NOT NULL CHECK (base_fare >= 0),
    status ENUM('Scheduled', 'Delayed', 'Cancelled', 'Completed', 'Flew', 'Check-in In Mode') DEFAULT 'Scheduled',
    booked_a_seats INT DEFAULT 0,
    booked_b_seats INT DEFAULT 0,
    booked_c_seats INT DEFAULT 0,
    CONSTRAINT fk_flight_source FOREIGN KEY (source_airport_code) REFERENCES airports(airport_code),
    CONSTRAINT fk_flight_dest FOREIGN KEY (destination_airport_code) REFERENCES airports(airport_code),
    CONSTRAINT fk_flight_aircraft FOREIGN KEY (aircraft_id) REFERENCES aircrafts(aircraft_id)
) ENGINE=InnoDB;

-- 7. SEATS TABLE
CREATE TABLE seats (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_id VARCHAR(20) NOT NULL,
    seat_no INT NOT NULL,
    seat_type ENUM('A', 'B', 'C') NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    is_upgraded BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_seat_flight FOREIGN KEY (flight_id) REFERENCES flights(flight_id) ON DELETE CASCADE,
    CONSTRAINT uq_flight_seat UNIQUE (flight_id, seat_no, seat_type)
) ENGINE=InnoDB;

-- 8. PAYMENTS TABLE
CREATE TABLE payments (
    payment_id VARCHAR(20) PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL CHECK (amount >= 0),
    is_paid BOOLEAN DEFAULT FALSE,
    payment_method ENUM('UPI', 'PayPal', 'Credit card') DEFAULT 'UPI',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- 9. BOOKINGS TABLE
CREATE TABLE bookings (
    booking_id VARCHAR(20) PRIMARY KEY,
    passenger_id VARCHAR(20) NOT NULL,
    flight_id VARCHAR(20) NOT NULL,
    seat_type ENUM('A', 'B', 'C') NOT NULL,
    seat_no VARCHAR(20) NULL,
    amount DECIMAL(10, 2) NOT NULL,
    booking_status ENUM('Confirmed', 'WaitList', 'Confirmed BN', 'Cancelled') NOT NULL,
    booking_date_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    passenger_check_in BOOLEAN DEFAULT FALSE,
    payment_id VARCHAR(20) NULL UNIQUE,
    CONSTRAINT fk_booking_passenger FOREIGN KEY (passenger_id) REFERENCES passengers(passenger_id),
    CONSTRAINT fk_booking_flight FOREIGN KEY (flight_id) REFERENCES flights(flight_id),
    CONSTRAINT fk_booking_payment FOREIGN KEY (payment_id) REFERENCES payments(payment_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- 10. TICKETS TABLE
CREATE TABLE tickets (
    ticket_id VARCHAR(20) PRIMARY KEY,
    booking_id VARCHAR(20) NOT NULL UNIQUE,
    flight_id VARCHAR(20) NOT NULL,
    seat_id INT NULL,
    fare DECIMAL(10, 2) NOT NULL,
    issued_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ticket_booking FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE,
    CONSTRAINT fk_ticket_flight FOREIGN KEY (flight_id) REFERENCES flights(flight_id),
    CONSTRAINT fk_ticket_seat FOREIGN KEY (seat_id) REFERENCES seats(seat_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- 11. REFUNDS TABLE
CREATE TABLE refunds (
    refund_id VARCHAR(20) PRIMARY KEY,
    booking_id VARCHAR(20) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    bank_name VARCHAR(100) NOT NULL,
    refund_date_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_refund_booking FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 12. CREW MEMBERS TABLE
CREATE TABLE crew_members (
    crew_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    flight_id VARCHAR(20) NULL,
    CONSTRAINT fk_crew_flight FOREIGN KEY (flight_id) REFERENCES flights(flight_id) ON DELETE SET NULL
) ENGINE=InnoDB;

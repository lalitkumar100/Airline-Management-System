# Airline Management System

A comprehensive Java-based airline management system designed to handle flight operations, passenger management, crew scheduling, and booking services.

## 📋 Project Overview

The Airline Management System is an enterprise-level application that manages all aspects of airline operations including:
- Flight scheduling and management
- Aircraft and airport management
- Passenger booking and reservations
- Crew management and assignments
- Payment processing
- Loyalty program management
- Revenue reporting

## 🏗️ Project Architecture

### Directory Structure

```
Airline-Management-System/
├── src/
│   ├── Main.java                                    # Application entry point
│   └── com/crimsonlogic/arilinemanangmentsystem/
│       ├── entity/                                  # Domain models
│       │   ├── Human.java                          # Base class for human entities
│       │   ├── Passenger.java                      # Passenger model
│       │   ├── Crew.java                           # Crew member model
│       │   ├── Flight.java                         # Flight information
│       │   ├── Aircraft.java                       # Aircraft details
│       │   ├── Airport.java                        # Airport information
│       │   ├── Booking.java                        # Booking records
│       │   ├── Ticket.java                         # Ticket information
│       │   ├── Seat.java                           # Seat management
│       │   ├── Payment.java                        # Payment records
│       │   ├── LoyaltyAccount.java                # Loyalty program
│       │   ├── FlightCrew.java                    # Flight crew assignments
│       │   ├── Report.java                         # Report base class
│       │   ├── RevenueReport.java                 # Revenue reporting
│       │   └── DisplayInfo.java                   # Display information interface
│       ├── service/                                # Business logic layer
│       │   └── PassengerServices.java             # Passenger operations
│       ├── utility/                                # Utility functions
│       │   ├── InputUtil.java                     # Console input handling
│       │   └── ValidatorUtil.java                 # Input validation
│       └── exception/                              # Custom exceptions
│           └── InvalidInputException.java         # Input validation exception
├── .gitignore
├── Airline Manangment System.iml                   # IntelliJ IDEA configuration
└── context.md                                      # Detailed documentation

```

## 📦 Core Packages

### 1. Entity Package (`entity/`)
Domain models representing business entities:

| Class | Purpose |
|-------|---------|
| **Human** | Base class with common properties (name, age, email, phone) |
| **Passenger** | Extends Human, represents airline passengers |
| **Crew** | Extends Human, represents crew members with role information |
| **Flight** | Flight information (number, route, schedule, capacity) |
| **Aircraft** | Aircraft details (model, capacity, registration) |
| **Airport** | Airport information (code, name, location) |
| **Booking** | Booking records linking passengers to flights |
| **Ticket** | Ticket information for confirmed bookings |
| **Seat** | Individual seat management and status |
| **Payment** | Payment transaction records |
| **LoyaltyAccount** | Customer loyalty program accounts |
| **FlightCrew** | Assignment of crew members to flights |
| **Report** | Base class for various reports |
| **RevenueReport** | Financial and revenue reporting |

### 2. Service Package (`service/`)
Business logic and service layer:

| Class | Purpose |
|-------|---------|
| **PassengerServices** | Core passenger operations (booking, ticket management, profile) |

### 3. Utility Package (`utility/`)
Helper functions and utilities:

#### InputUtil.java
Centralized console input handling with support for:
- **Data Types Supported:**
  - `int` - Integer values
  - `double` - Decimal values
  - `String` - Text input
  - `LocalDate` - Dates in yyyy-MM-dd format
  - `LocalDateTime` - Date-time in yyyy-MM-ddTHH:mm:ss format
  - `boolean` - True/False values

#### ValidatorUtil.java
Input validation utilities for data integrity checks

### 4. Exception Package (`exception/`)
Custom exception handling:

| Class | Purpose |
|-------|---------|
| **InvalidInputException** | Custom exception for invalid user input |

## 🔑 Key Classes & Functionality

### Main Entry Point
- **Main.java**: Application initialization and main execution flow

### Core Entity Classes

#### Human.java (Base Class)
```
Properties:
- id: String
- name: String
- age: int
- email: String
- phone: String
- address: String
- dateOfBirth: LocalDate
```

#### Passenger.java
```
Extends: Human
Additional Properties:
- loyaltyPoints: int
- bookingHistory: List<Booking>
- preferences: String
```

#### Crew.java
```
Extends: Human
Additional Properties:
- employeeID: String
- role: String (Pilot, Steward, Hostess, etc.)
- certifications: List<String>
- assignedFlights: List<Flight>
```

#### Flight.java
```
Properties:
- flightNumber: String
- departureAirport: Airport
- arrivalAirport: Airport
- departureTime: LocalDateTime
- arrivalTime: LocalDateTime
- aircraft: Aircraft
- crew: List<Crew>
- seats: List<Seat>
- status: String (Scheduled, On-time, Delayed, Cancelled)
```

#### Booking.java
```
Properties:
- bookingID: String
- passenger: Passenger
- flight: Flight
- bookingDate: LocalDate
- status: String (Confirmed, Pending, Cancelled)
- totalAmount: double
- seats: List<Seat>
```

## 🎯 Workflow & Functionality

### Passenger Booking Process
1. **Search & Browse** - Find available flights
2. **Select Flight** - Choose preferred flight and seats
3. **Make Payment** - Process payment transaction
4. **Confirm Booking** - Generate booking confirmation
5. **Issue Ticket** - Create and display ticket information
6. **Earn Loyalty Points** - Accumulate rewards

### Flight Management
1. **Schedule Flight** - Set flight details and crew
2. **Assign Aircraft** - Allocate appropriate aircraft
3. **Manage Seats** - Configure seat inventory
4. **Update Status** - Track real-time flight status
5. **Generate Reports** - Create operational reports

### Crew Management
1. **Assign Crew** - Allocate crew to flights
2. **Manage Certifications** - Track crew qualifications
3. **Schedule Management** - Prevent crew conflicts

## 🔧 Utility Features

### InputUtil Usage Examples

```java
// Get integer input
int age = InputUtil.getInt("Enter your age: ");

// Get decimal input
double salary = InputUtil.getDouble("Enter salary: ");

// Get string input
String name = InputUtil.getString("Enter full name: ");

// Get date input (yyyy-MM-dd)
LocalDate dob = InputUtil.getDate("Enter date of birth: ");

// Get datetime input (yyyy-MM-ddTHH:mm:ss)
LocalDateTime flightTime = InputUtil.getDateTime("Enter flight departure time: ");

// Get boolean input
boolean isActive = InputUtil.getBoolean("Is account active? ");
```

## 💾 Future Enhancements

- [ ] Database integration (SQL/NoSQL)
- [ ] Enhanced input validation with regex patterns
- [ ] Comprehensive error handling and logging
- [ ] Web-based user interface (Spring Boot REST API)
- [ ] Real-time flight tracking
- [ ] Mobile application
- [ ] Advanced reporting and analytics
- [ ] Integration with payment gateways
- [ ] Multi-language support
- [ ] Email and SMS notifications

## 🛠️ Technology Stack

- **Language**: Java
- **IDE**: IntelliJ IDEA
- **Build Tool**: Maven (optional)
- **Java Version**: Java 8+
- **Date/Time**: java.time.* API

## 📝 Code Quality Standards

- Object-oriented design principles
- Separation of concerns (Entity, Service, Utility layers)
- Reusable utility classes
- Custom exception handling
- Centralized input handling

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA or any Java IDE

### Running the Application
1. Clone the repository
2. Open the project in your IDE
3. Build the project
4. Run `Main.java` as the entry point

## 📄 Documentation

For detailed information about specific components, see [context.md](context.md)

## 👤 Author

**Lalit Kumar** - [GitHub Profile](https://github.com/lalitkumar100)

## 📄 License

This project is open source and available under the MIT License.

---

**Last Updated**: July 24, 2026

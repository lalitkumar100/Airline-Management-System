package com.crimsonlogic.arilinemanangmentsystem.Functionality;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.crimsonlogic.arilinemanangmentsystem.model.*;
import com.crimsonlogic.arilinemanangmentsystem.exception.InvalidCrewException;
import com.crimsonlogic.arilinemanangmentsystem.exception.InvalidHumanException;
import com.crimsonlogic.arilinemanangmentsystem.service.AirportandAirCraftService;
import com.crimsonlogic.arilinemanangmentsystem.service.FlightService;
import com.crimsonlogic.arilinemanangmentsystem.utility.InputUtil;
import com.crimsonlogic.arilinemanangmentsystem.utility.ValidatorUtil;

public class RegisterFunctionality {


    InputUtil input = new InputUtil();
    public  Human registerHuman() {

        System.out.println("\n========== Person Info ==========");

        String firstName = input.getString("Enter First Name: ");
        String lastName = input.getString("Enter Last Name: ");
        String passportNumber = input.getString("Enter Passport Number: ");
        String nationality = input.getString("Enter Nationality: ");

        LocalDate dob;
        while (true) {
            try {
                dob = input.getDate("Enter Date of Birth");
                ValidatorUtil.validateAge(dob);
                break;
            } catch (InvalidHumanException e) {
                System.out.println(e.getMessage());
            }
        }

        String phoneNumber;
        while (true) {
            try {
                phoneNumber = input.getString("Enter Phone Number: ");
                ValidatorUtil.validatePhone(phoneNumber);
                break;
            } catch (InvalidHumanException e) {
                System.out.println(e.getMessage());
            }
        }

        String email;
        while (true) {
            try {
                email = input.getString("Enter Email: ");
                ValidatorUtil.validateEmail(email);
                break;
            } catch (InvalidHumanException e) {
                System.out.println(e.getMessage());
            }
        }

        Human human = new Human(
                firstName,
                lastName,
                dob,
                passportNumber,
                nationality,
                phoneNumber,
                email
        );

        System.out.println("\nHuman Registered Successfully!\n");

        return human;
    }

    public  Crew registerCrew() {

        System.out.println("\n========== Crew Registration ==========");

        Human human = registerHuman();

        System.out.println("\n========== Professional Info ==========");

        LocalDate dateOfJoining;
        while (true) {
            try {
                dateOfJoining = input.getDate("Enter Date of Joining");
                ValidatorUtil.validateDateOfJoining(human.getDateOfBirth(), dateOfJoining);
                break;
            } catch (InvalidCrewException e) {
                System.out.println(e.getMessage());
            }
        }

        int yearOfExperience;
        while (true) {
            try {
                yearOfExperience = input.getInt("Enter Years of Experience: ");
                ValidatorUtil.validateExperience(human.getDateOfBirth(),
                        dateOfJoining,
                        yearOfExperience);
                break;
            } catch (InvalidCrewException e) {
                System.out.println(e.getMessage());
            }
        }

        return new Crew(human, dateOfJoining, yearOfExperience);
    }

    public  Passenger registerPassenger() {

        System.out.println("\n========== Passenger Registration ==========");
        // Register common Human details
        Human human = registerHuman();

        String password = input.getString("Enter password : ");

        // Create Passenger


        Passenger passenger = new Passenger(human,password);


        System.out.println("\nPassenger Registered Successfully!\n");

        return passenger;
    }

    /**
     * Registers a new flight by taking input from the console.
     *
     * <p>The method displays available aircraft and airports, validates
     * the user's input, ensures the source and destination airports are
     * different, validates the departure and arrival times, creates a
     * Flight object and stores it in the FlightService.</p>
     *
     * @param flightService Service used to store the flight.
     * @param airportService Service used to retrieve aircraft and airport information.
     * @throws Exception if an unexpected error occurs while retrieving data.
     */
    public void registerFlight(FlightService flightService,
                               AirportandAirCraftService airportService) throws Exception {

        System.out.println("\n========== Register Flight ==========");

        // ---------------- Aircraft ----------------

        airportService.displayAllAircraft();

        Aircraft aircraft;

        while (true) {

            int aircraftId = input.getInt("\nEnter Aircraft ID : ");

            if (!airportService.containsAircraft(aircraftId)) {
                System.out.println("Aircraft not found. Please try again.");
                continue;
            }

            aircraft = airportService.getAircraft(aircraftId);
            break;
        }

        // ---------------- Airports ----------------

        airportService.displayAllAirports();

        Airport sourceAirport;
        Airport destinationAirport;

        while (true) {

            String sourceId = input.getString("\nEnter Source Airport ID : ").toUpperCase();

            if (!airportService.containsAirportById(sourceId)) {
                System.out.println("Source Airport does not exist.");
                continue;
            }

            sourceAirport = airportService.getAirportById(sourceId);
            break;
        }

        while (true) {

            String destinationId =
                    input.getString("Enter Destination Airport ID : ").toUpperCase();

            if (!airportService.containsAirportById(destinationId)) {

                System.out.println("Destination Airport does not exist.");
                continue;
            }

            if (destinationId.equalsIgnoreCase(sourceAirport.getAirportId())) {

                System.out.println("Source and Destination Airport cannot be the same.");
                continue;
            }

            destinationAirport = airportService.getAirportById(destinationId);
            break;
        }

        // ---------------- Date & Time ----------------

        System.out.println("\nCurrent Date & Time : " + LocalDateTime.now());
        System.out.println("Date format : yyyy-MM-ddTHH:mm:ss");

        LocalDateTime departureTime;

        while (true) {

            departureTime =
                    input.getDateTime("Enter Departure Date & Time");

            if (departureTime.isBefore(LocalDateTime.now())) {

                System.out.println("Departure cannot be before the current date and time.");
                continue;
            }

            break;
        }

        LocalDateTime arrivalTime;

        while (true) {

            arrivalTime =
                    input.getDateTime("Enter Arrival Date & Time");

            if (!arrivalTime.isAfter(departureTime)) {

                System.out.println("Arrival time must be after departure time.");
                continue;
            }

            break;
        }

        if (!flightService.isAircraftAvailable(
                aircraft.getAircraftId(),
                departureTime,
                arrivalTime)) {

            System.out.println();
            System.out.println("This aircraft is already assigned to another flight during the selected time.");
            return;
        }

        // ---------------- Create Flight ----------------

        Flight flight = new Flight(
                aircraft,
                sourceAirport,
                destinationAirport,
                departureTime,
                arrivalTime
        );

        if (flightService.addFlight(flight)) {

            System.out.println("\nFlight Registered Successfully.");
            System.out.println("Generated Flight ID : " + flight.getFlightId());

        } else {

            System.out.println("Unable to register flight.");
        }
    }


}
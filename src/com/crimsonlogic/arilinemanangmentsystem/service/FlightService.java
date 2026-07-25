package com.crimsonlogic.arilinemanangmentsystem.service;

import com.crimsonlogic.arilinemanangmentsystem.model.Airport;
import com.crimsonlogic.arilinemanangmentsystem.model.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Service class responsible for managing all flight-related operations.
 *
 * <p>This class provides functionalities to:</p>
 * <ul>
 *     <li>Add new flights.</li>
 *     <li>Search flights.</li>
 *     <li>Update flight timings.</li>
 *     <li>Update source and destination airports.</li>
 *     <li>Remove flights.</li>
 *     <li>Display all available flights.</li>
 * </ul>
 *
 * <p>The service internally maintains multiple HashMaps to allow
 * faster searching using Flight ID, source airport,
 * destination airport and departure date.</p>
 */
public class FlightService {

    /**
     * Stores every flight using Flight ID as the key.
     */
    private final HashMap<String, Flight> flightMap = new HashMap<>();

    /**
     * Stores flights grouped by source airport ID.
     */
    private final HashMap<String, ArrayList<Flight>> sourceFlightMap = new HashMap<>();

    /**
     * Stores flights grouped by destination airport ID.
     */
    private final HashMap<String, ArrayList<Flight>> destinationFlightMap = new HashMap<>();

    /**
     * Stores flights grouped by departure date.
     */
    private final HashMap<LocalDate, ArrayList<Flight>> dateFlightMap = new HashMap<>();



    /**
     * Adds a new flight into the system.
     *
     * @param flight Flight object to be registered.
     * @return true if flight is added successfully, otherwise false.
     */
    public boolean addFlight(Flight flight) throws Exception {

        if (flight == null || flightMap.containsKey(flight.getFlightId())) {
            return false;
        }

        flightMap.put(flight.getFlightId(), flight);

        sourceFlightMap
                .computeIfAbsent(
                        flight.getSourceAirport().getAirportId(),
                        k -> new ArrayList<>())
                .add(flight);

        destinationFlightMap
                .computeIfAbsent(
                        flight.getDestinationAirport().getAirportId(),
                        k -> new ArrayList<>())
                .add(flight);

        dateFlightMap
                .computeIfAbsent(
                        flight.getDepartureDateTime().toLocalDate(),
                        k -> new ArrayList<>())
                .add(flight);

        return true;
    }

    /**
     * Checks whether a flight exists.
     *
     * @param flightId Flight ID.
     * @return true if found.
     */
    public boolean containsFlight(int flightId) {
        return flightMap.containsKey(flightId);
    }

    /**
     * Returns a flight using its unique ID.
     *
     * @param flightId Flight ID.
     * @return Flight object.
     * @throws Exception if flight does not exist.
     */
    public Flight getFlight(int flightId) throws Exception {

        if (!containsFlight(flightId)) {
            throw new Exception("Flight not found.");
        }

        return flightMap.get(flightId);
    }

    /**
     * Updates the departure time of a flight.
     *
     * @param flightId Flight ID.
     * @param departureTime New departure time.
     * @throws Exception if flight does not exist.
     */
    public void updateDepartureTime(int flightId,
                                    LocalDateTime departureTime) throws Exception {

        Flight flight = getFlight(flightId);

        dateFlightMap
                .get(flight.getDepartureDateTime().toLocalDate())
                .remove(flight);

        flight.setDepartureDateTime(departureTime);

        dateFlightMap
                .computeIfAbsent(
                        departureTime.toLocalDate(),
                        k -> new ArrayList<>())
                .add(flight);
    }

    /**
     * Updates the arrival time.
     *
     * @param flightId Flight ID.
     * @param arrivalTime New arrival time.
     * @throws Exception if flight does not exist.
     */
    public void updateArrivalTime(int flightId,
                                  LocalDateTime arrivalTime)
            throws Exception {

        getFlight(flightId).setArrivalDateTime(arrivalTime);
    }

    /**
     * Updates the source airport.
     *
     * @param flightId Flight ID.
     * @param airport New source airport.
     * @throws Exception if flight does not exist.
     */
    public void updateSourceAirport(int flightId, Airport airport) throws Exception {

        Flight flight = getFlight(flightId);

        sourceFlightMap
                .get(flight.getSourceAirport().getAirportId())
                .remove(flight);

        flight.setSourceAirport(airport);

        sourceFlightMap
                .computeIfAbsent(
                        airport.getAirportId(),
                        k -> new ArrayList<>())
                .add(flight);
    }

    /**
     * Updates the destination airport.
     *
     * @param flightId Flight ID.
     * @param airport New destination airport.
     * @throws Exception if flight does not exist.
     */
    public void updateDestinationAirport(int flightId, Airport airport)throws Exception {

        Flight flight = getFlight(flightId);

        destinationFlightMap
                .get(flight.getDestinationAirport().getAirportId())
                .remove(flight);

        flight.setDestinationAirport(airport);

        destinationFlightMap
                .computeIfAbsent(
                        airport.getAirportId(),
                        k -> new ArrayList<>())
                .add(flight);
    }

    /**
     * Removes a flight permanently.
     *
     * @param flightId Flight ID.
     * @throws Exception if flight does not exist.
     */
    public void removeFlight(int flightId) throws Exception {

        Flight flight = getFlight(flightId);

        sourceFlightMap
                .get(flight.getSourceAirport().getAirportId())
                .remove(flight);

        destinationFlightMap
                .get(flight.getDestinationAirport().getAirportId())
                .remove(flight);

        dateFlightMap
                .get(flight.getDepartureDateTime().toLocalDate())
                .remove(flight);

        flightMap.remove(flightId);
    }

    /**
     * Searches flights using source airport,
     * destination airport and departure date.
     *
     * @param sourceAirportId Source Airport ID.
     * @param destinationAirportId Destination Airport ID.
     * @param date Departure date.
     * @return List of matching flights.
     */
    public List<Flight> searchFlights(String sourceAirportId,
                                      String destinationAirportId,
                                      LocalDate date) {

        List<Flight> result = new ArrayList<>();

        ArrayList<Flight> sourceFlights =
                sourceFlightMap.get(sourceAirportId);

        if (sourceFlights == null) {
            return result;
        }

        for (Flight flight : sourceFlights) {

            if (flight.getDestinationAirport()
                    .getAirportId()
                    .equals(destinationAirportId)
                    &&
                    flight.getDepartureDateTime()
                            .toLocalDate()
                            .equals(date)) {

                result.add(flight);
            }
        }

        return result;
    }


    /**
     * Displays all registered flights in tabular format.
     */
    public void displayAllFlights() {

        if (flightMap.isEmpty()) {
            System.out.println("\nNo flights available.");
            return;
        }

        System.out.println("======================================================================================================================");
        System.out.printf("%-8s %-8s %-15s %-8s %-8s %-18s %-18s%n",
                "ID",
                "AIR ID",
                "AIRCRAFT",
                "FROM",
                "TO",
                "DEPARTURE",
                "ARRIVAL");
        System.out.println("======================================================================================================================");

        for (Flight flight : flightMap.values()) {
            System.out.println(flight);
        }

        System.out.println("======================================================================================================================");
    }


    /**
     * Displays the given list of flights in tabular format.
     *
     * @param flights List of flights to display.
     */
    public void displayFlights(List<Flight> flights) {

        if (flights == null || flights.isEmpty()) {
            System.out.println("\nNo flights found.");
            return;
        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        System.out.println("==============================================================================================================================");
        System.out.printf("%-8s %-8s %-18s %-8s %-8s %-18s %-18s%n",
                "ID",
                "AIR ID",
                "AIRCRAFT",
                "FROM",
                "TO",
                "DEPARTURE",
                "ARRIVAL");
        System.out.println("==============================================================================================================================");

        for (Flight flight : flights) {

            System.out.printf("%-8s %-8d %-18s %-8s %-8s %-18s %-18s%n",
                    flight.getFlightId(),
                    flight.getAircraftId(),
                    flight.getAircraftName(),
                    flight.getSourceAirport().getAirportCode(),
                    flight.getDestinationAirport().getAirportCode(),
                    flight.getDepartureDateTime().format(formatter),
                    flight.getArrivalDateTime().format(formatter));
        }

        System.out.println("==============================================================================================================================");
    }


    /**
     * Checks whether an aircraft is available for the specified time period.
     *
     * @param aircraftId Aircraft ID.
     * @param departure Proposed departure time.
     * @param arrival Proposed arrival time.
     * @return true if the aircraft is available; false if there is a scheduling conflict.
     *
     *
     * @Existing:
     *      |-------------|
     *
     * @New:
     *          |------|
     *
     * @OR
     *
     * @New:
     * |----------------------|
     **/
    public boolean isAircraftAvailable(int aircraftId,
                                       LocalDateTime departure,
                                       LocalDateTime arrival) {

        for (Flight flight : flightMap.values()) {

            // Skip flights of other aircraft
            if (flight.getAircraftId() != aircraftId) {
                continue;
            }

            LocalDateTime existingDeparture = flight.getDepartureDateTime();
            LocalDateTime existingArrival = flight.getArrivalDateTime();



            boolean conflict =
                    departure.isBefore(existingArrival)
                            && arrival.isAfter(existingDeparture);

            if (conflict) {
                return false;
            }
        }

        return true;
    }
    /**
     * Loads sample flight records.
     *
     * <p>Implementation can be extended later to create
     * predefined flights during application startup.</p>
     */
    public void initializeData(AirportandAirCraftService aas) throws Exception {

        addFlight(new Flight(aas.getAircraft(101), aas.getAirportById("A101"), aas.getAirportById("A102"),
                LocalDateTime.of(2026, 7, 29, 6, 0),
                LocalDateTime.of(2026, 7, 29, 8, 30)));

        addFlight(new Flight(aas.getAircraft(102), aas.getAirportById("A102"), aas.getAirportById("A103"),
                LocalDateTime.of(2026, 7, 29, 7, 15),
                LocalDateTime.of(2026, 7, 29, 9, 30)));

        addFlight(new Flight(aas.getAircraft(103), aas.getAirportById("A103"), aas.getAirportById("A101"),
                LocalDateTime.of(2026, 7, 29, 9, 0),
                LocalDateTime.of(2026, 7, 29, 11, 0)));

        addFlight(new Flight(aas.getAircraft(104), aas.getAirportById("A101"), aas.getAirportById("A104"),
                LocalDateTime.of(2026, 7, 29, 10, 0),
                LocalDateTime.of(2026, 7, 29, 12, 20)));

        addFlight(new Flight(aas.getAircraft(105), aas.getAirportById("A104"), aas.getAirportById("A105"),
                LocalDateTime.of(2026, 7, 29, 11, 30),
                LocalDateTime.of(2026, 7, 29, 13, 10)));

        addFlight(new Flight(aas.getAircraft(106), aas.getAirportById("A105"), aas.getAirportById("A106"),
                LocalDateTime.of(2026, 7, 29, 12, 0),
                LocalDateTime.of(2026, 7, 29, 14, 45)));

        addFlight(new Flight(aas.getAircraft(107), aas.getAirportById("A106"), aas.getAirportById("A107"),
                LocalDateTime.of(2026, 7, 29, 13, 0),
                LocalDateTime.of(2026, 7, 29, 15, 15)));

        addFlight(new Flight(aas.getAircraft(108), aas.getAirportById("A107"), aas.getAirportById("A101"),
                LocalDateTime.of(2026, 7, 29, 14, 30),
                LocalDateTime.of(2026, 7, 29, 16, 45)));

        addFlight(new Flight(aas.getAircraft(109), aas.getAirportById("A102"), aas.getAirportById("A105"),
                LocalDateTime.of(2026, 7, 29, 15, 0),
                LocalDateTime.of(2026, 7, 29, 17, 20)));

        addFlight(new Flight(aas.getAircraft(110), aas.getAirportById("A103"), aas.getAirportById("A106"),
                LocalDateTime.of(2026, 7, 29, 16, 0),
                LocalDateTime.of(2026, 7, 29, 18, 40)));

        addFlight(new Flight(aas.getAircraft(111), aas.getAirportById("A104"), aas.getAirportById("A107"),
                LocalDateTime.of(2026, 7, 29, 17, 0),
                LocalDateTime.of(2026, 7, 29, 19, 30)));

        addFlight(new Flight(aas.getAircraft(112), aas.getAirportById("A105"), aas.getAirportById("A101"),
                LocalDateTime.of(2026, 7, 29, 18, 0),
                LocalDateTime.of(2026, 7, 29, 20, 30)));

        addFlight(new Flight(aas.getAircraft(113), aas.getAirportById("A106"), aas.getAirportById("A102"),
                LocalDateTime.of(2026, 7, 29, 19, 0),
                LocalDateTime.of(2026, 7, 29, 21, 15)));

        addFlight(new Flight(aas.getAircraft(114), aas.getAirportById("A107"), aas.getAirportById("A103"),
                LocalDateTime.of(2026, 7, 29, 20, 0),
                LocalDateTime.of(2026, 7, 29, 22, 20)));

        addFlight(new Flight(aas.getAircraft(115), aas.getAirportById("A101"), aas.getAirportById("A106"),
                LocalDateTime.of(2026, 7, 30, 6, 0),
                LocalDateTime.of(2026, 7, 30, 8, 40)));

        addFlight(new Flight(aas.getAircraft(116), aas.getAirportById("A102"), aas.getAirportById("A104"),
                LocalDateTime.of(2026, 7, 30, 7, 30),
                LocalDateTime.of(2026, 7, 30, 9, 45)));

        addFlight(new Flight(aas.getAircraft(117), aas.getAirportById("A103"), aas.getAirportById("A105"),
                LocalDateTime.of(2026, 7, 30, 8, 30),
                LocalDateTime.of(2026, 7, 30, 10, 45)));

        addFlight(new Flight(aas.getAircraft(118), aas.getAirportById("A104"), aas.getAirportById("A101"),
                LocalDateTime.of(2026, 7, 30, 10, 0),
                LocalDateTime.of(2026, 7, 30, 12, 15)));

        addFlight(new Flight(aas.getAircraft(119), aas.getAirportById("A105"), aas.getAirportById("A102"),
                LocalDateTime.of(2026, 7, 30, 11, 30),
                LocalDateTime.of(2026, 7, 30, 13, 50)));

        addFlight(new Flight(aas.getAircraft(120), aas.getAirportById("A106"), aas.getAirportById("A107"),
                LocalDateTime.of(2026, 7, 30, 13, 0),
                LocalDateTime.of(2026, 7, 30, 15, 30)));
    }

}
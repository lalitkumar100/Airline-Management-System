package com.crimsonlogic.arilinemanangmentsystem.model;

import com.crimsonlogic.arilinemanangmentsystem.utility.IdGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight extends Aircraft {


    private String flightId;

    private Airport sourceAirport;
    private Airport destinationAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private  FlightCrew  crewMember ;


    public Flight(
                  Aircraft plane,
                  Airport sourceAirport,
                  Airport destinationAirport,
                  LocalDateTime departureDateTime,
                  LocalDateTime arrivalDateTime) {


        super(plane);
        this.flightId = IdGenerator.generateFlightId();
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getFlightId() {
        return flightId;
    }



    public void setSourceAirport(Airport sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public FlightCrew getCrewMember() {
        return crewMember;
    }

    public void setCrewMember(FlightCrew crewMember) {
        this.crewMember = crewMember;
    }



    @Override
    public void displayInfo() {

        System.out.println("\n===== Flight Information =====");
        System.out.println("Flight ID          : " + flightId);
        System.out.println("Source Airport     : " + sourceAirport);
        System.out.println("Destination Airport: " + destinationAirport);
        System.out.println("Departure Time     : " + departureDateTime);
        System.out.println("Arrival Time       : " + arrivalDateTime);
        System.out.println("==============================");
    }


    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public String toString() {

        return String.format(
                "%-8s %-8d %-15s %-8s %-8s %-18s %-18s",
                flightId,
                getAircraftId(),
                getAircraftName(),
                sourceAirport.getAirportCode(),
                destinationAirport.getAirportCode(),
                departureDateTime.format(FORMATTER),
                arrivalDateTime.format(FORMATTER)
        );
    }

    public void displayInfo(boolean showAircraft) {

        displayInfo(); // Flight Info

        if (showAircraft) {
            super.displayInfo(); // Aircraft Info
        }
    }
}

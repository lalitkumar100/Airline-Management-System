package com.crimsonlogic.arilinemanangmentsystem.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class Flight extends  Aircraft{


    private int flightId;
    private String flightNumber;
    private Airport sourceAirport;
    private Airport destinationAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private  FlightCrew  crewMember ;


    public Flight(int aircraftId,
                  String aircraftName,
                  String aircraftModel,
                  int capacity,
                  int flightId,
                  String flightNumber,
                  Airport sourceAirport,
                  Airport destinationAirport,
                  LocalDateTime departureDateTime,
                  LocalDateTime arrivalDateTime) {

        super(aircraftId, aircraftName, aircraftModel, capacity);

        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
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
        System.out.println("Flight Number      : " + flightNumber);
        System.out.println("Source Airport     : " + sourceAirport);
        System.out.println("Destination Airport: " + destinationAirport);
        System.out.println("Departure Time     : " + departureDateTime);
        System.out.println("Arrival Time       : " + arrivalDateTime);
        System.out.println("==============================");
    }
    public void displayInfo(boolean showAircraft) {

        displayInfo(); // Flight Info

        if (showAircraft) {
            super.displayInfo(); // Aircraft Info
        }
    }
}

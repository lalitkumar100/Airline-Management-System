package com.crimsonlogic.arilinemanangmentsystem.service;

import java.util.HashMap;

import com.crimsonlogic.arilinemanangmentsystem.model.Aircraft;
import com.crimsonlogic.arilinemanangmentsystem.model.Airport;

public class AirportandAirCraftService  {

    // airportId -> Airport
    private static HashMap<String, Airport> airportMap = new HashMap<>();

    // city -> Airport
    private  static HashMap<String, Airport> cityAirportMap = new HashMap<>();

    // Aircraft collection
    private static HashMap<Integer, Aircraft> aircraftMap = new HashMap<>();


    public boolean addAircraft(Aircraft aircraft) {

        if (aircraft == null) {
            return false;
        }

        aircraftMap.put(aircraft.getAircraftId(), aircraft);
        return true;
    }
    // Add Airport
    public boolean addAirport(Airport airport) {

        if (airport == null) {
            return false;
        }

        airportMap.put(airport.getAirportId(), airport);
        cityAirportMap.put(
                airport.getCity().toLowerCase(),
                airport
        );

        return true;
    }

    public Airport getAirportByCity(String city) throws Exception {

        city = city.toLowerCase();

        if (!cityAirportMap.containsKey(city)) {
            throw new Exception("Airport for this city is not found");
        }

        return cityAirportMap.get(city);
    }

    public  boolean ContainsAirportByCity(String city){
        return cityAirportMap.containsKey(city);
    }

    // Search Airport by Airport
    public boolean containsAirportById(String airportId) {

        return airportMap.containsKey(airportId);
    }

    public Airport getAirportById(String airportId) throws Exception {
        if(!airportMap.containsKey(airportId)){
            throw  new Exception("Airport of this Id is Not Found");
        }
        return airportMap.get(airportId);
    }

    // Search Airport by City


    public boolean containsAircraft(int aircraftId) {
        return aircraftMap.containsKey(aircraftId);
    }

    public Aircraft getAircraft(int aircraftId) throws Exception {
        if(!containsAircraft(aircraftId)){

            throw  new Exception("AirCraft will this ID not found");
        }
        return aircraftMap.get(aircraftId);
    }

    public void removeAircraft(int aircraftId) throws Exception {
        if(!containsAircraft(aircraftId)){

            throw  new Exception("AirCraft will this ID not found");
        }
        aircraftMap.remove(aircraftId);
        System.out.println("Delete the AirCraft with ID "+aircraftId);
    }

    // Display all Airports
    public static void displayAllAirports() {

        System.out.println("================================================================================");
        System.out.printf("%-10s %-15s %-15s %-15s%n",
                "ID", "CODE", "CITY", "COUNTRY");
        System.out.println("================================================================================");

        for (Airport airport : airportMap.values()) {
            System.out.println(airport);
        }

        System.out.println("================================================================================");
    }

    // Display all Aircraft
    public static void displayAllAircraft() {

        System.out.println("========================================================================================");
        System.out.printf("%-10s %-20s %-20s %-10s%n",
                "ID", "NAME", "MODEL", "CAPACITY");
        System.out.println("========================================================================================");

        for (Aircraft aircraft : aircraftMap.values()) {
            System.out.println(aircraft);
        }

        System.out.println("========================================================================================");
    }
    // Load Sample Data
    public void initializeData() {

        // Airports
        addAirport(new Airport("A101", "BLR", "Bangalore", "India"));
        addAirport(new Airport("A102", "DEL", "Delhi", "India"));
        addAirport(new Airport("A103", "BOM", "Mumbai", "India"));
        addAirport(new Airport("A104", "MAA", "Chennai", "India"));
        addAirport(new Airport("A105", "HYD", "Hyderabad", "India"));
        addAirport(new Airport("A106", "CCU", "Kolkata", "India"));
        addAirport(new Airport("A107", "COK", "Kochi", "India"));

        // Aircraft

        addAircraft(new Aircraft(101, "Air India", "Boeing 777", 300));
        addAircraft(new Aircraft(102, "IndiGo", "Airbus A320", 180));
        addAircraft(new Aircraft(103, "SpiceJet", "Boeing 737", 189));
        addAircraft(new Aircraft(104, "Vistara", "Airbus A321", 220));
        addAircraft(new Aircraft(105, "Akasa Air", "Boeing 737 MAX", 197));

            addAircraft(new Aircraft(106, "Emirates", "Airbus A380", 550));
            addAircraft(new Aircraft(107, "Qatar Airways", "Boeing 787", 280));
            addAircraft(new Aircraft(108, "Lufthansa", "Airbus A350", 315));
            addAircraft(new Aircraft(109, "British Airways", "Boeing 777", 320));
            addAircraft(new Aircraft(110, "Singapore Airlines", "Airbus A350", 300));

            addAircraft(new Aircraft(111, "United Airlines", "Boeing 787", 275));
            addAircraft(new Aircraft(112, "Delta Airlines", "Airbus A330", 290));
            addAircraft(new Aircraft(113, "American Airlines", "Boeing 737", 190));
            addAircraft(new Aircraft(114, "Etihad Airways", "Boeing 777", 310));
            addAircraft(new Aircraft(115, "Turkish Airlines", "Airbus A330", 295));

            addAircraft(new Aircraft(116, "Japan Airlines", "Boeing 767", 250));
            addAircraft(new Aircraft(117, "ANA", "Boeing 787", 270));
            addAircraft(new Aircraft(118, "Malaysia Airlines", "Airbus A330", 287));
            addAircraft(new Aircraft(119, "Thai Airways", "Boeing 777", 305));
            addAircraft(new Aircraft(120, "Air France", "Airbus A350", 325));
         }
}
package com.crimsonlogic.arilinemanangmentsystem.service;

import com.crimsonlogic.arilinemanangmentsystem.exception.RecordNotFoundException;
import com.crimsonlogic.arilinemanangmentsystem.model.Aircraft;
import com.crimsonlogic.arilinemanangmentsystem.model.Airport;

import java.util.ArrayList;
import java.util.List;

public class AirportAndAircraftService {

    /**
     * Loads 5 demo airports and 5 demo aircraft.
     * Call this method once when the application starts.
     */
    public void initializeDemoData() {

    }

    /**
     * Returns an Airport using airport code.
     *
     * @param airportCode Airport code (DEL, BOM...)
     * @return Airport object
     * @throws RecordNotFoundException if airport is not found
     */
    public Airport getAirportByCode(String airportCode) throws RecordNotFoundException {
        try (org.apache.ibatis.session.SqlSession session = com.crimsonlogic.arilinemanangmentsystem.config.MyBatisUtil.getSqlSession()) {
            com.crimsonlogic.arilinemanangmentsystem.mapper.AirportMapper mapper = session.getMapper(com.crimsonlogic.arilinemanangmentsystem.mapper.AirportMapper.class);
            Airport airport = mapper.selectAirportByCode(airportCode);
            if (airport != null) {
                return airport;
            }
        }
        throw new RecordNotFoundException("Airport with this code not found.");
    }

    /**
     * Returns an Aircraft using aircraft ID.
     *
     * @param aircraftId Aircraft ID
     * @return Aircraft object
     * @throws RecordNotFoundException if aircraft is not found
     */
    public Aircraft getAircraftById(String aircraftId) throws RecordNotFoundException {
        try (org.apache.ibatis.session.SqlSession session = com.crimsonlogic.arilinemanangmentsystem.config.MyBatisUtil.getSqlSession()) {
            com.crimsonlogic.arilinemanangmentsystem.mapper.AircraftMapper mapper = session.getMapper(com.crimsonlogic.arilinemanangmentsystem.mapper.AircraftMapper.class);
            Aircraft aircraft = mapper.selectAircraftById(aircraftId);
            if (aircraft != null) {
                return aircraft;
            }
        }
        throw new RecordNotFoundException("Aircraft not found.");
    }

    /**
     * Returns all airports.
     *
     * @return airport list
     */
    public List<Airport> getAirportList() {
        try (org.apache.ibatis.session.SqlSession session = com.crimsonlogic.arilinemanangmentsystem.config.MyBatisUtil.getSqlSession()) {
            com.crimsonlogic.arilinemanangmentsystem.mapper.AirportMapper mapper = session.getMapper(com.crimsonlogic.arilinemanangmentsystem.mapper.AirportMapper.class);
            return mapper.selectAllAirports();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Returns all aircraft.
     *
     * @return aircraft list
     */
    public List<Aircraft> getAircraftList() {
        try (org.apache.ibatis.session.SqlSession session = com.crimsonlogic.arilinemanangmentsystem.config.MyBatisUtil.getSqlSession()) {
            com.crimsonlogic.arilinemanangmentsystem.mapper.AircraftMapper mapper = session.getMapper(com.crimsonlogic.arilinemanangmentsystem.mapper.AircraftMapper.class);
            return mapper.selectAllAircrafts();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Displays all airports in table format.
     */
    public void displayAllAirports() {
        List<Airport> airports = getAirportList();
        if (airports.isEmpty()) {
            System.out.println("No airports available.");
            return;
        }

        System.out.println("\n===================== AIRPORT LIST =====================");
        System.out.printf("%-10s %-40s %-20s%n",
                "Code",
                "Airport Name",
                "City");

        System.out.println("--------------------------------------------------------------");

        for (Airport airport : airports) {
            System.out.println(airport);
        }
    }

    /**
     * Displays all aircraft in table format.
     */
    public void displayAllAircraft() {
        List<Aircraft> aircrafts = getAircraftList();
        if (aircrafts.isEmpty()) {
            System.out.println("No aircraft available.");
            return;
        }

        System.out.println("\n=============== AIRCRAFT LIST ===============");

        System.out.printf("%-10s %-25s %-10s%n",
                "ID",
                "Model",
                "Capacity");

        System.out.println("------------------------------------------------");

        for (Aircraft aircraft : aircrafts) {
            System.out.println(aircraft);
        }
    }
}
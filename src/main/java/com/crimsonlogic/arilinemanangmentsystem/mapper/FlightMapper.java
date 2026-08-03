package com.crimsonlogic.arilinemanangmentsystem.mapper;

import com.crimsonlogic.arilinemanangmentsystem.model.Flight;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface FlightMapper {
    void insertFlight(Flight flight);
    Flight selectFlightById(@Param("flightId") String flightId);
    List<Flight> selectAllFlights();
    void updateFlight(Flight flight);
    void deleteFlight(@Param("flightId") String flightId);
    String selectMaxFlightId();
}

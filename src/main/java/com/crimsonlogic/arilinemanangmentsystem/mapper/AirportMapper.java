package com.crimsonlogic.arilinemanangmentsystem.mapper;

import com.crimsonlogic.arilinemanangmentsystem.model.Airport;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AirportMapper {
    void insertAirport(Airport airport);
    Airport selectAirportByCode(@Param("airportCode") String airportCode);
    List<Airport> selectAllAirports();
    void updateAirport(Airport airport);
    void deleteAirport(@Param("airportCode") String airportCode);
}

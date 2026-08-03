package com.crimsonlogic.arilinemanangmentsystem.mapper;

import com.crimsonlogic.arilinemanangmentsystem.model.Aircraft;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AircraftMapper {
    void insertAircraft(Aircraft aircraft);
    Aircraft selectAircraftById(@Param("aircraftId") String aircraftId);
    List<Aircraft> selectAllAircrafts();
    void updateAircraft(Aircraft aircraft);
    void deleteAircraft(@Param("aircraftId") String aircraftId);
}

package com.crimsonlogic.arilinemanangmentsystem.mapper;

import com.crimsonlogic.arilinemanangmentsystem.model.Seat;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SeatMapper {
    void insertSeat(@Param("flightId") String flightId, @Param("seat") Seat seat);
    Seat selectSeatById(int seatId);
    List<Seat> selectSeatsByFlightId(String flightId);
}

package com.crimsonlogic.arilinemanangmentsystem.mapper;

import com.crimsonlogic.arilinemanangmentsystem.model.Ticket;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TicketMapper {
    void insertTicket(@Param("bookingId") String bookingId, @Param("flightId") String flightId, @Param("seatId") Integer seatId, @Param("ticket") Ticket ticket);
    Ticket selectTicketById(String ticketId);
    List<Ticket> selectTicketsByFlightId(String flightId);
    Ticket selectTicketByBookingId(String bookingId);
}

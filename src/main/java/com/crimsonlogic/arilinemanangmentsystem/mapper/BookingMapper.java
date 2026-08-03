package com.crimsonlogic.arilinemanangmentsystem.mapper;

import com.crimsonlogic.arilinemanangmentsystem.model.Booking;
import com.crimsonlogic.arilinemanangmentsystem.model.Ticket;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface BookingMapper {
    // Booking CRUD
    void insertBooking(Booking booking);
    Booking selectBookingById(@Param("bookingId") String bookingId);
    List<Booking> selectAllBookings();
    List<Booking> selectBookingsByFlightId(@Param("flightId") String flightId);
    List<Booking> selectBookingsByPassengerId(@Param("passengerId") String passengerId);
    void updateBooking(Booking booking);
    void deleteBooking(@Param("bookingId") String bookingId);

    // Ticket CRUD
    void insertTicket(Ticket ticket);
    Ticket selectTicketById(@Param("ticketId") String ticketId);
    void deleteTicket(@Param("ticketId") String ticketId);

    String selectMaxBookingId();
    String selectMaxTicketId();
}

package com.crimsonlogic.arilinemanangmentsystem.service;

import com.crimsonlogic.arilinemanangmentsystem.exception.ValidationException;
import com.crimsonlogic.arilinemanangmentsystem.model.Booking;
import com.crimsonlogic.arilinemanangmentsystem.model.Flight;
import com.crimsonlogic.arilinemanangmentsystem.model.Ticket;
import com.crimsonlogic.arilinemanangmentsystem.utility.InputUtil;
import com.crimsonlogic.arilinemanangmentsystem.model.Seat;
import com.crimsonlogic.arilinemanangmentsystem.config.MyBatisUtil;
import com.crimsonlogic.arilinemanangmentsystem.mapper.BookingMapper;
import com.crimsonlogic.arilinemanangmentsystem.mapper.FlightMapper;
import com.crimsonlogic.arilinemanangmentsystem.mapper.SeatMapper;
import com.crimsonlogic.arilinemanangmentsystem.mapper.TicketMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class TicketSercive {

    private final FlightService flightService;
    InputUtil input = new InputUtil();

    public TicketSercive(FlightService flightService) {
        this.flightService = flightService;
    }

    public void generateTicketswithFlight(Flight flight) throws ValidationException {

        if (flight == null) {
            throw new ValidationException("Flight cannot be null.");
        }

        if (flight.getStatus().equalsIgnoreCase(Flight.STATUS_COMPLETED)) {
            System.out.println("Tickets are already generated.");
            return;
        }

        PriorityQueue<Booking> queue = new PriorityQueue<>(flight.getWaitLsit());

        ArrayList<Booking> aBookings = new ArrayList<>();
        ArrayList<Booking> bBookings = new ArrayList<>();
        ArrayList<Booking> cBookings = new ArrayList<>();

        while (!queue.isEmpty()) {

            Booking booking = queue.poll();

            switch (booking.getSeatType().toUpperCase()) {

                case "A":
                    aBookings.add(booking);
                    break;

                case "B":
                    bBookings.add(booking);
                    break;

                default:
                    cBookings.add(booking);
            }
        }

        int capacity = flight.getAircraft().getCapacity();

        int aCapacity = capacity * 20 / 100;
        int bCapacity = capacity * 30 / 100;
        int cCapacity = capacity - aCapacity - bCapacity;

        while (aBookings.size() < aCapacity && !bBookings.isEmpty()) {

            Booking booking = bBookings.remove(0);
            booking.setSeatType("A");
            aBookings.add(booking);
        }

        while (bBookings.size() < bCapacity && !cBookings.isEmpty()) {

            Booking booking = cBookings.remove(0);
            booking.setSeatType("B");
            bBookings.add(booking);
        }

        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            SeatMapper seatMapper = session.getMapper(SeatMapper.class);
            TicketMapper ticketMapper = session.getMapper(TicketMapper.class);
            BookingMapper bookingMapper = session.getMapper(BookingMapper.class);
            FlightMapper flightMapper = session.getMapper(FlightMapper.class);

            assignSeats(aBookings, 'A', flight, aCapacity, seatMapper, ticketMapper, bookingMapper);
            assignSeats(bBookings, 'B', flight, bCapacity, seatMapper, ticketMapper, bookingMapper);
            assignSeats(cBookings, 'C', flight, cCapacity, seatMapper, ticketMapper, bookingMapper);

            flight.setStatus(Flight.STATUS_COMPLETED);
            flightMapper.updateFlight(flight);

            session.commit();
        } catch (Exception e) {
            System.out.println("Database Error during ticket generation: " + e.getMessage());
            throw new ValidationException("Error generating tickets: " + e.getMessage());
        }

        System.out.println("Tickets Generated Successfully.");
    }

    /**
     * Assigns seats and creates tickets.
     */
    private void assignSeats(ArrayList<Booking> bookings,
                             char seatType,
                             Flight flight,
                             int maxSeats,
                             SeatMapper seatMapper,
                             TicketMapper ticketMapper,
                             BookingMapper bookingMapper) {

        int seatNumber = 1;
        int assignedSeats = 0;

        for (Booking booking : bookings) {

            // Stop when all seats are occupied
            if (assignedSeats >= maxSeats) {

                booking.setBookingstatus(Booking.STATUS_WAITLIST);
                bookingMapper.updateBooking(booking);

                System.out.println("------------------------------------------");
                System.out.println("Booking ID : " + booking.getBookingId());
                System.out.println("Passenger  : " + booking.getPassenger().getName());
                System.out.println("Status     : WAITLIST (No Seat Available)");

                continue;
            }

            Seat seat = new Seat(
                    seatNumber++,
                    seatType,
                    false,
                    !booking.getSeatType().equalsIgnoreCase(String.valueOf(seatType))
            );

            // Persist the seat and generate seatId
            seatMapper.insertSeat(flight.getFlightId(), seat);

            Ticket ticket = new Ticket(booking.getAmount(), seat);

            // Persist the ticket
            ticketMapper.insertTicket(booking.getBookingId(), flight.getFlightId(), seat.getSeatId(), ticket);

            booking.setTicket(ticket);
            booking.setBookingstatus(Booking.STATUS_CONFIRMED);
            bookingMapper.updateBooking(booking);

            flight.addTickets(ticket);

            assignedSeats++;

            System.out.println("------------------------------------------");
            booking.displayInfo();
            System.out.println("Ticket ID : " + ticket.getTicketId());
            System.out.println("Seat      : " + seatType + seat.getSeatNo());
        }
    }
}

package com.crimsonlogic.arilinemanangmentsystem.model;

import com.crimsonlogic.arilinemanangmentsystem.utility.IdGenerator;

/**
 * Represents an issued travel ticket for a confirmed booking.
 * <p>
 * Contains a unique ticket ID, the fare paid, and the assigned {@link Seat}.
 * Created once a booking is confirmed and a seat is allocated on the flight.
 */
public class Ticket {

    String ticketId;
    double fare;
    Seat seat;

    public Ticket() {
    }

    public Ticket(double fare, Seat seat) {
        this.fare = fare;
        this.seat = seat;
        this.ticketId= IdGenerator.generateTicketId();
    }

    public String getTicketId() {
        return ticketId;
    }


    public double getFare() {
        return fare;
    }


    public Seat getSeat() {
        return seat;
    }


}
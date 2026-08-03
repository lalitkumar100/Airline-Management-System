package com.crimsonlogic.arilinemanangmentsystem.model;

/**
 * Represents an individual seat on an aircraft.
 * <p>
 * Each seat has a number, a class type (A, B, or C), and flags for
 * availability and whether it has been upgraded to a higher class.
 */
public class Seat {

    final char   SEAT_A ='A';
    final char   SEAT_B ='B';
    final char   SEAT_C ='C';

    private int seatId;
    private int seatNo;
    private char SeatType ;
    private boolean available;
    private boolean upgraded;

    public Seat() {
    }

    public Seat(int seatNo, char seatType, boolean available, boolean upgraded) {
        this.seatNo = seatNo;
        this.SeatType = seatType;
        this.available = available;
        this.upgraded = upgraded;
    }
    
    public int getSeatId() {
        return seatId;
    }
    
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
    public int getSeatNo() {
        return seatNo;
    }

}
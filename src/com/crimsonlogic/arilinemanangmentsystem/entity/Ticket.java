package com.crimsonlogic.arilinemanangmentsystem.entity;


public class Ticket extends Booking implements DisplayInfo {

    private String seatNo;
    private String seatType; // Override booking seat type
    public Ticket(Booking booking, String seatNo, String seatType) {
        super(booking);
        this.seatNo = seatNo;
        this.seatType = seatType;
    }
    // Getters and Setters
    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    @Override
    public String getSeatType() {
        return seatType;
    }

    @Override
    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    // Future implementation
    public void cancelTicket() {
        System.out.println("Ticket cancellation feature will be implemented later.");
    }

    @Override
    public void displayInfo() {
        System.out.println("\n===== Ticket Details =====");
         super.displayInfo();
        System.out.println("Seat Type     : " + seatType);
        System.out.println("Seat No       : " + seatNo);

    }
}
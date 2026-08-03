package com.crimsonlogic.arilinemanangmentsystem.model;

import com.crimsonlogic.arilinemanangmentsystem.utility.IdGenerator;

/**
 * Represents a payment transaction linked to a flight booking.
 * <p>
 * Holds a unique payment ID, the amount charged, payment status (paid or pending),
 * and a reference to the associated {@link Booking}.
 */
public class Payment {


    public static  final  String UPI="UPI";
    public static  final  String PAYPAL="PayPal";
    public static  final  String CREDIT_CARD="Credit card";

    private String paymentId;

    private double amount;

   private boolean paid;

   private Booking booking;

   private String paymentMethod;

    public Payment() {
    }

    public Payment(double amount, boolean paid) {
        this.paymentId = IdGenerator.generatePaymentId();
        this.amount = amount;
        this.paid = paid;
        this.paymentMethod=UPI;
    }


    public Payment(double amount, boolean paid, String  paymentMethod) {
        this.paymentId = IdGenerator.generatePaymentId();
        this.amount = amount;
        this.paid = paid;
        this.paymentMethod=paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentId() {
        return paymentId;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {

        return String.format(
                "%-10s %-10s %-10s %-10.2f %-10s",
                paymentId,
                booking.getBookingId(),
                booking.getFlightBooked().getFlightId(),
                amount,
                paid ? "Paid" : "Pending");
    }

    /**
     * Displays payment information.
     */
    public void displayInfo() {

        System.out.println("\n========== PAYMENT DETAILS ==========");
        System.out.println("Payment ID    : " + paymentId);
        System.out.println("Booking ID    : " + booking.getBookingId());
        System.out.println("Passenger ID  : " + booking.getPassenger().getPassengerId());
        System.out.println("Flight ID     : " + booking.getFlightBooked().getFlightId());
        System.out.println("Amount        : " + amount);
        System.out.println("Payment Status: " + (paid ? "Paid" : "Pending"));
    }
}
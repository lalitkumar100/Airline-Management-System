package com.crimsonlogic.arilinemanangmentsystem.model;

import com.crimsonlogic.arilinemanangmentsystem.utility.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a refund issued for a cancelled booking.
 * <p>
 * Stores the refund ID, amount, timestamp, and linked booking.
 * All refunds are collected in a shared list for reporting and audit purposes.
 */
public class Refund {

    private String refundId;
    private LocalDateTime time;
    private double amount;
    private Booking booking;
    private String accountNo;
    private String bankName;

    public Refund() {
    }

    public Refund(double amount, Booking booking) {
        this.refundId = IdGenerator.generatePaymentId();
        this.amount = amount;
        this.booking = booking;
        this.time = LocalDateTime.now();
        this.accountNo=booking.getPassenger().getAccountNumber();
        this.bankName=booking.getPassenger().getBankName();
    }

    public String getRefundId() {
        return refundId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
                "%-12s %-12s %-12s %-15s %-18s %-10s% %-10.2f",
                refundId,
                booking.getBookingId(),
                booking.getPassenger().getPassengerId(),
                booking.getFlightBooked().getFlightId(),
                accountNo,
                bankName,
                amount
        );
    }

    public static void displayAllRefunds() {

        try (org.apache.ibatis.session.SqlSession session = com.crimsonlogic.arilinemanangmentsystem.config.MyBatisUtil.getSqlSession()) {
            com.crimsonlogic.arilinemanangmentsystem.mapper.RefundMapper mapper = session.getMapper(com.crimsonlogic.arilinemanangmentsystem.mapper.RefundMapper.class);
            java.util.List<Refund> refunds = mapper.selectAllRefunds();

            if (refunds.isEmpty()) {
                System.out.println("No Refunds Found.");
                return;
            }

            System.out.println("\n============================ REFUND LIST ============================");

            System.out.printf("%-12s %-12s %-12s %-12s %-18s %-10s% %-10s%n",
                    "Refund ID",
                    "Booking ID",
                    "Passenger",
                    "Flight ID",
                    "Account No",
                    "Bank Name",
                    "Amount");

            System.out.println("-------------------------------------------------------------------------------");

            for (Refund refund : refunds) {
                System.out.println(refund);
            }

            System.out.println("-------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
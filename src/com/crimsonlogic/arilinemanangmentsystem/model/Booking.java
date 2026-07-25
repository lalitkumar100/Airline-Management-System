package com.crimsonlogic.arilinemanangmentsystem.model;

import java.time.LocalDateTime;


    public class Booking implements DisplayInfo {

        private String bookingId;
        private String flightId;
        private LocalDateTime bookingDateTime;
        private double bookingPrice;
        private String seatType;
        private String status;

        public Booking(String bookingId, String flightId,
                       LocalDateTime bookingDateTime,
                       double bookingPrice,
                       String seatType,
                       String status) {

            this.bookingId = bookingId;
            this.flightId = flightId;
            this.bookingDateTime = bookingDateTime;
            this.bookingPrice = bookingPrice;
            this.seatType = seatType;
            this.status = status;
        }

        public Booking(Booking booking) {
            this.bookingId = booking.bookingId;
            this.flightId = booking.flightId;
            this.bookingDateTime = booking.bookingDateTime;
            this.bookingPrice = booking.bookingPrice;
            this.seatType = booking.seatType;
            this.status = booking.status;
        }

        public String getBookingId() {
            return bookingId;
        }

        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }

        public String getFlightId() {
            return flightId;
        }

        public void setFlightId(String flightId) {
            this.flightId = flightId;
        }

        public LocalDateTime getBookingDateTime() {
            return bookingDateTime;
        }

        public void setBookingDateTime(LocalDateTime bookingDateTime) {
            this.bookingDateTime = bookingDateTime;
        }

        public double getBookingPrice() {
            return bookingPrice;
        }

        public void setBookingPrice(double bookingPrice) {
            this.bookingPrice = bookingPrice;
        }

        public String getSeatType() {
            return seatType;
        }

        public void setSeatType(String seatType) {
            this.seatType = seatType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public void displayInfo() {
            System.out.println("===== Booking Details =====");
            System.out.println("Booking ID      : " + bookingId);
            System.out.println("Flight ID       : " + flightId);
            System.out.println("Booking Date    : " + bookingDateTime);
            System.out.println("Booking Price   : " + bookingPrice);
            System.out.println("Seat Type       : " + seatType);
            System.out.println("Status          : " + status);
        }
    }


package com.crimsonlogic.arilinemanangmentsystem.entity;

import java.time.LocalDate;

public class Passenger extends Human {

    private String loyaltyId;

    public Passenger(String id,
                     String firstName,
                     String lastName,
                     LocalDate dateOfBirth,
                     String passportNumber,
                     String nationality,
                     String phoneNumber,
                     String email,
                     String loyaltyId) {

        super(id, firstName, lastName, dateOfBirth,
                passportNumber, nationality,
                phoneNumber, email);

        this.loyaltyId = loyaltyId;
    }

    public String getLoyaltyId() {
        return loyaltyId;
    }

    public void setLoyaltyId(String loyaltyId) {
        this.loyaltyId = loyaltyId;
    }
    @Override
    public void displayInfo() {

        System.out.println("===== Passenger Details =====");

        super.displayInfo();

        System.out.println("Loyalty ID: " + loyaltyId);
    }
}

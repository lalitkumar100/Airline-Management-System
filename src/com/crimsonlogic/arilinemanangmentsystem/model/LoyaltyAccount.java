package com.crimsonlogic.arilinemanangmentsystem.model;

import com.crimsonlogic.arilinemanangmentsystem.utility.IdGenerator;

public class LoyaltyAccount implements  DisplayInfo
{

    private String loyaltyId;
    private String passengerId;
    private int loyaltyPoints;
    private int totalFlights;
    private String membershipLevel;

    public LoyaltyAccount( String passengerId) {
        this.loyaltyId = IdGenerator.generateLoyaltyId();
        this.passengerId = passengerId;
        this.loyaltyPoints = 0;
        this.totalFlights = 0;
        this.membershipLevel = "BRONZE";
    }

    public String getLoyaltyId() {
        return loyaltyId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public int getTotalFlights() {
        return totalFlights;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void addPoints(int points) {
        loyaltyPoints += points;
    }

    public boolean redeemPoints(int points) {

        if (loyaltyPoints >= points) {
            loyaltyPoints -= points;
            return true;
        }

        return false;
    }

    public void addFlight() {

        totalFlights++;

        updateMembership();
    }


    private void updateMembership() {

        if (totalFlights >= 50) {
            membershipLevel = "PLATINUM";
        } else if (totalFlights >= 25) {
            membershipLevel = "GOLD";
        } else if (totalFlights >= 10) {
            membershipLevel = "SILVER";
        } else {
            membershipLevel = "BRONZE";
        }
    }


    @Override
    public void displayInfo() {

        System.out.println("Loyalty ID      : " + loyaltyId);
        System.out.println("Passenger ID    : " + passengerId);
        System.out.println("Points          : " + loyaltyPoints);
        System.out.println("Flights         : " + totalFlights);
        System.out.println("Membership      : " + membershipLevel);
    }
}

package com.crimsonlogic.arilinemanangmentsystem.entity;

import java.time.LocalDate;

public class Crew extends Human {


    private int crewId;
    private LocalDate dateOfJoining;
    private  int yearOfExperience;
    public Crew(String id, String firstName, String lastName, LocalDate dateOfBirth, String passportNumber, String nationality, String phoneNumber, String email) {
        super(id, firstName, lastName, dateOfBirth, passportNumber, nationality, phoneNumber, email);
    }

    public int getCrewId() {
        return crewId;
    }

    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public int getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(int yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }


    @Override
    public void displayInfo() {

        System.out.println("\n========== Crew Information ==========");
        super.displayInfo(); // Displays Human details
        System.out.println("Crew ID           : " + crewId);
        System.out.println("Date of Joining   : " + dateOfJoining);
        System.out.println("Years Experience  : " + yearOfExperience);

        System.out.println("======================================");
    }
}

package com.crimsonlogic.arilinemanangmentsystem.model;

import com.crimsonlogic.arilinemanangmentsystem.utility.IdGenerator;

import java.time.LocalDate;

public class Crew extends Human    {


    private String crewId;
    private LocalDate dateOfJoining;
    private  int yearOfExperience;

    public Crew(Human human,
                LocalDate dateOfJoining,
                int yearOfExperience) {

        super(human);

        this.crewId = IdGenerator.generateCrewId();
        this.dateOfJoining = dateOfJoining;
        this.yearOfExperience = yearOfExperience;
    }

    public String getCrewId() {
        return crewId;
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
        System.out.println("Crew ID           : " + crewId);
        super.displayInfo(); // Displays Human details
        System.out.println("Date of Joining   : " + dateOfJoining);
        System.out.println("Years Experience  : " + yearOfExperience);

        System.out.println("======================================");
    }
}

package com.crimsonlogic.arilinemanangmentsystem.model;

import java.time.LocalDate;

public  class Human implements  DisplayInfo {


    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String passportNumber;
    private String nationality;
    private String phoneNumber;
    private String email;


    public Human(Human human) {

        this.firstName = human.firstName;
        this.lastName = human.lastName;
        this.dateOfBirth = human.dateOfBirth;
        this.passportNumber = human.passportNumber;
        this.nationality = human.nationality;
        this.phoneNumber = human.phoneNumber;
        this.email = human.email;
    }

    public Human( String firstName, String lastName,
                 LocalDate dateOfBirth, String passportNumber,
                 String nationality, String phoneNumber,
                 String email) {


        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void displayInfo() {

    System.out.println("Name: " + firstName + " " + lastName);
    System.out.println("DOB: " + dateOfBirth);
    System.out.println("Passport No: " + passportNumber);
    System.out.println("Nationality: " + nationality);
    System.out.println("Phone: " + phoneNumber);
    System.out.println("Email: " + email);   }


}

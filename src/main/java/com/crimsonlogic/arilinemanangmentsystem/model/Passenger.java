package com.crimsonlogic.arilinemanangmentsystem.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a registered airline passenger.
 * <p>
 * Stores personal details (ID, name, email, phone), login credentials,
 * and an associated {@link LoyaltyAccount} for rewards tracking.
 */
public class Passenger {

    private String passengerId;
    private String name;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String bankName;
    private String accountNumber;



    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private LoyaltyAccount loyalty;

    public Passenger() {
    }

    public Passenger(String passengerId, String name, String email, String phone, LoyaltyAccount loyalty) {
        this.passengerId = passengerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.loyalty = loyalty;
        this.password = "123456";
        this.bankName="state bank of India";
        this.accountNumber="12334678AC";
    }

    public Passenger(String passengerId, String name, String email, String phone, LoyaltyAccount loyalty,
            String password) {
        this.passengerId = passengerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.loyalty = loyalty;
        this.password = password;
        this.bankName="state bank of India";
        this.accountNumber="12334678AC";
    }

    public Passenger(String passengerId, String name, String email, String phone, LocalDate dateOfBirth, String bankName, String accountNumber, LoyaltyAccount loyalty, String password) {
        this.passengerId = passengerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.loyalty = loyalty;
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * Verifies the passenger password.
     *
     * @param password Password entered by the user
     * @return true if password is correct, otherwise false
     */
    public boolean verifyPassword(String password) {

        return this.password.equals(password);
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LoyaltyAccount getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(LoyaltyAccount loyalty) {
        this.loyalty = loyalty;
    }

    /**
     * Displays passenger information.
     */
    public void displayInfo() {
        System.out.println("\n========== PASSENGER DETAILS ==========");
        System.out.println("Passenger ID : " + passengerId);
        System.out.println("Name         : " + name);
        System.out.println("Email        : " + email);
        System.out.println("Phone        : " + phone);
        System.out.println("DOB          : " + (dateOfBirth != null ? dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""));
        System.out.println("Bank Name    : " + (bankName != null ? bankName : ""));
        System.out.println("Account No.  : " + (accountNumber != null ? accountNumber : ""));
        loyalty.displayInfo();

        System.out.println("\n========== PASSENGER DETAILS ==========");
        System.out.println("Passenger ID : " + passengerId);
        System.out.println("Name         : " + name);
        System.out.println("Email        : " + email);
        System.out.println("Phone        : " + phone);

        loyalty.displayInfo();
    }
}
package com.crimsonlogic.arilinemanangmentsystem.service;

import com.crimsonlogic.arilinemanangmentsystem.exception.InvalidHumanException;
import com.crimsonlogic.arilinemanangmentsystem.exception.RecordNotFoundException;
import com.crimsonlogic.arilinemanangmentsystem.model.LoyaltyAccount;
import com.crimsonlogic.arilinemanangmentsystem.model.Passenger;
import com.crimsonlogic.arilinemanangmentsystem.utility.IdGenerator;
import com.crimsonlogic.arilinemanangmentsystem.utility.InputUtil;
import com.crimsonlogic.arilinemanangmentsystem.utility.ValidatorUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class PassengerService {

    private final HashMap<String, Passenger> passengers = new HashMap<>();
    private final InputUtil input = new InputUtil();

    public void registerPassenger() {

        System.out.println("\n========== REGISTER PASSENGER ==========");

        String name = input.getString("Enter Name : ");

        String email;
        while (true) {
            try {
                email = input.getString("Enter Email : ");
                ValidatorUtil.validateEmail(email);
                break;
            } catch (InvalidHumanException e) {
                System.out.println(e.getMessage());
            }
        }

        String phone;
        while (true) {
            try {
                phone = input.getString("Enter Phone : ");
                ValidatorUtil.validatePhone(phone);
                break;
            } catch (InvalidHumanException e) {
                System.out.println(e.getMessage());
            }
        }
        String password;
        while (true) {
            try {
                password = input.getString("Enter password : ");
                if (password.equals("0")) {
                    System.out.println("Exiting registration.");
                    return;
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Date of Birth input
        LocalDate dob = null;
        while (true) {
            String dobStr = input.getString("Enter Date of Birth (yyyy-MM-dd) (or 0 to exit): ");
            if (dobStr.equals("0")) {
                System.out.println("Exiting registration.");
                return;
            }
            try {
                dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        // Bank details input
        String bankName = input.getString("Enter Bank Name : ");
        String accountNumber = input.getString("Enter Bank Account Number : ");

        String passengerId = IdGenerator.generatePassengerId();

        LoyaltyAccount loyalty = new LoyaltyAccount();

        Passenger passenger = new Passenger(
                passengerId,
                name,
                email,
                phone,
                dob,
                bankName,
                accountNumber,
                loyalty,
                password
        );
        passengers.put(passengerId, passenger);

        System.out.println("\nPassenger Registered Successfully.");
        System.out.println("Passenger ID : " + passengerId);
    }

    public Passenger getPassengerById(String passengerId) throws RecordNotFoundException {
        if(passengers.containsKey(passengerId)){
             return passengers.get(passengerId); 
        }
        throw  new RecordNotFoundException("Passenger with this Id is not found");
    } 

    /**
     * Inserts demo passengers into the system.
     */
    public void initializeDemoPassengers() {

        Passenger passenger1 = new Passenger(
                IdGenerator.generatePassengerId(),
                "Rahul Sharma",
                "rahul@gmail.com",
                "9876543210",
                new LoyaltyAccount(150, "Silver")
        );
        Passenger passenger2 = new Passenger(
                IdGenerator.generatePassengerId(),
                "Priya Patel",
                "priya@gmail.com",
                "9876543211",
                new LoyaltyAccount(420, LoyaltyAccount.GOLD_TIER)
        );
        Passenger passenger3 = new Passenger(
                IdGenerator.generatePassengerId(),
                "Amit Kumar",
                "amit@gmail.com",
                "9876543212",
                new LoyaltyAccount(80, "Silver")
        );
        Passenger passenger4 = new Passenger(
                IdGenerator.generatePassengerId(),
                "Sneha Reddy",
                "sneha@gmail.com",
                "9876543213",
                new LoyaltyAccount(900, LoyaltyAccount.DIAMOND_TIER)
        );
        Passenger passenger5 = new Passenger(
                IdGenerator.generatePassengerId(),
                "Arjun Singh",
                "arjun@gmail.com",
                "9876543214",
                new LoyaltyAccount()
        );
        Passenger passenger6 = new Passenger(
                IdGenerator.generatePassengerId(),
                "Neha Verma",
                "neha@gmail.com",
                "9876543215",
                new LoyaltyAccount(250, LoyaltyAccount.SILVER_TIER)
        );
        Passenger passenger7 = new Passenger(
                IdGenerator.generatePassengerId(),
                "Rohan Mehta",
                "rohan@gmail.com",
                "9876543216",
                new LoyaltyAccount(650, LoyaltyAccount.GOLD_TIER)
        );
        Passenger passenger8 = new Passenger(
                IdGenerator.generatePassengerId(),
                "Kavya Nair",
                "kavya@gmail.com",
                "9876543217",
                new LoyaltyAccount(1200, LoyaltyAccount.DIAMOND_TIER)
        );
        Passenger passenger9 = new Passenger(
                IdGenerator.generatePassengerId(),
                "Vikram Joshi",
                "vikram@gmail.com",
                "9876543218",
                new LoyaltyAccount()
        );
        Passenger passenger10 = new Passenger(
                IdGenerator.generatePassengerId(),
                "Ananya Das",
                "ananya@gmail.com",
                "9876543219",
                new LoyaltyAccount(350, LoyaltyAccount.SILVER_TIER)
        );
        passengers.put(passenger1.getPassengerId(), passenger1);
        passengers.put(passenger2.getPassengerId(), passenger2);
        passengers.put(passenger3.getPassengerId(), passenger3);
        passengers.put(passenger4.getPassengerId(), passenger4);
        passengers.put(passenger5.getPassengerId(), passenger5);
        passengers.put(passenger6.getPassengerId(), passenger6);
        passengers.put(passenger7.getPassengerId(), passenger7);
        passengers.put(passenger8.getPassengerId(), passenger8);
        passengers.put(passenger9.getPassengerId(), passenger9);
        passengers.put(passenger10.getPassengerId(), passenger10);

    }


    public static Passenger readPassenger() {

        while (true) {

            String passengerId =
                    input.getString("Enter Passenger ID (0 to Cancel) : ");

            if (passengerId.equals("0")) {
                return null;
            }

            try {

                return getPassengerById(passengerId);

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }
        }
    }
    public HashMap<String, Passenger> getPassengers() {
        return passengers;
    }
}
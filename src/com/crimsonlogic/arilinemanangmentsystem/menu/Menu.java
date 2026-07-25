package com.crimsonlogic.arilinemanangmentsystem.menu;

import com.crimsonlogic.arilinemanangmentsystem.Functionality.RegisterFunctionality;
import com.crimsonlogic.arilinemanangmentsystem.service.AirportandAirCraftService;
import com.crimsonlogic.arilinemanangmentsystem.service.FlightService;
import com.crimsonlogic.arilinemanangmentsystem.utility.InputUtil;

public class Menu {
    RegisterFunctionality registerFunc  = new RegisterFunctionality();
    InputUtil inputer = new InputUtil();

    public  void start(AirportandAirCraftService aas , FlightService fs) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println(" AIRLINE MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Admin");
            System.out.println("2. Passenger");
            System.out.println("0. Exit");

            int choice = inputer.getInt("Enter Choice: ");

            switch (choice) {

                case 1:

                    adminMenu(aas ,fs);
                    break;

                case 2:
                    passengerMenu();
                    break;

                case 0:
                    System.out.println("Thank you for using the system.");
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    /**
     * Displays the administrator menu and handles admin operations.
     */
    private void adminMenu(AirportandAirCraftService aas , FlightService fs) {

        while (true) {

            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1. Register Crew");
            System.out.println("2. Register Flight");
            System.out.println("0. Back");

            int choice = inputer.getInt("Enter Choice: ");

            switch (choice) {

                case 1:
                    registerFunc.registerCrew();
                    break;

                case 2:
                    try {
                        registerFunc.registerFlight(fs,aas);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    private  void passengerMenu() {

        while (true) {

            System.out.println("\n======= PASSENGER MENU =======");
            System.out.println("1. Register Passenger");
            System.out.println("0. Back");

            int choice = inputer.getInt("Enter Choice: ");

            switch (choice) {

                case 1:
                    registerFunc.registerPassenger();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}
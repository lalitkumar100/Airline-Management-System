package com.crimsonlogic.arilinemanangmentsystem.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.crimsonlogic.arilinemanangmentsystem.exception.InvalidInputException;

public class InputUtil {

    private static final Scanner sc = new Scanner(System.in);

    public static int getInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {
                throw new InvalidInputException(
                        "Invalid Integer Input. Please enter a valid integer.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static double getDouble(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine());

            } catch (NumberFormatException e) {
                throw new InvalidInputException(
                        "Invalid Double Input. Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getString(String message) {

        while (true) {

            try {
                System.out.print(message);

                String value = sc.nextLine().trim();

                if (value.isEmpty()) {
                    throw new InvalidInputException(
                            "Input cannot be empty.");
                }

                return value;

            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static LocalDate getDate(String message) {

        while (true) {

            try {
                System.out.print(message + " (yyyy-MM-dd): ");
                return LocalDate.parse(sc.nextLine());

            } catch (DateTimeParseException e) {
                throw new InvalidInputException(
                        "Invalid Date Format. Use yyyy-MM-dd.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static LocalDateTime getDateTime(String message) {

        while (true) {

            try {
                System.out.print(message + " (yyyy-MM-ddTHH:mm:ss): ");
                return LocalDateTime.parse(sc.nextLine());

            } catch (DateTimeParseException e) {
                throw new InvalidInputException(
                        "Invalid DateTime Format. Use yyyy-MM-ddTHH:mm:ss.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean getBoolean(String message) {

        while (true) {

            try {

                System.out.print(message + " (true/false): ");

                String value = sc.nextLine().trim().toLowerCase();

                if ("true".equals(value)) {
                    return true;
                }

                if ("false".equals(value)) {
                    return false;
                }

                throw new InvalidInputException(
                        "Please enter only true or false.");

            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
package com.crimsonlogic.arilinemanangmentsystem.utility;

import com.crimsonlogic.arilinemanangmentsystem.exception.InvalidCrewException;
import com.crimsonlogic.arilinemanangmentsystem.exception.InvalidHumanException;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public final class ValidatorUtil {


    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final String PHONE_REGEX =
            "^[0-9]{10}$";

    /**
     * Validates whether the given email address matches the required format.
     *
     * @param email the email address to validate
     * @return true if the email format is valid; otherwise false
     */
    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    /**
     * Validates whether the phone number contains exactly 10 digits.
     *
     * @param phone the phone number to validate
     * @return true if the phone number is valid; otherwise false
     */
    public static boolean isValidPhone(String phone) {
        return Pattern.matches(PHONE_REGEX, phone);
    }

    /**
     * Validates whether the age calculated from the date of birth
     * is between 0 and 120 years.
     *
     * @param dob the person's date of birth
     * @return true if the age is valid; otherwise false
     */
    public static boolean isValidAge(LocalDate dob) {
        int age = Period.between(dob, LocalDate.now()).getYears();
        return age >= 0 && age <= 120;
    }

    /**
     * Validates the email address.
     *
     * @param email the email address to validate
     * @throws InvalidHumanException if the email format is invalid
     */
    public static void validateEmail(String email)
            throws InvalidHumanException {

        if (!isValidEmail(email)) {
            throw new InvalidHumanException(
                    "Invalid email address. Please enter a valid email.");
        }
    }

    /**
     * Validates the phone number.
     *
     * @param phone the phone number to validate
     * @throws InvalidHumanException if the phone number is invalid
     */
    public static void validatePhone(String phone)
            throws InvalidHumanException {

        if (!isValidPhone(phone)) {
            throw new InvalidHumanException(
                    "Invalid phone number. It must contain exactly 10 digits.");
        }
    }

    /**
     * Validates the date of birth.
     *
     * @param dob the person's date of birth
     * @throws InvalidHumanException if the age is not between 0 and 120 years
     */
    public static void validateAge(LocalDate dob)
            throws InvalidHumanException {

        if (!isValidAge(dob)) {
            throw new InvalidHumanException(
                    "Invalid date of birth. Age must be between 0 and 120 years.");
        }
    }

    /**
     * Validates the crew member's date of joining.
     * <p>
     * Rules:
     * <ul>
     *   <li>Joining date cannot be in the future.</li>
     *   <li>The crew member must be at least 18 years old on the joining date.</li>
     * </ul>
     *
     * @param dob the crew member's date of birth
     * @param joiningDate the date of joining
     * @return true if the joining date is valid; otherwise false
     */
    public static boolean isValidDateOfJoining(LocalDate dob,
                                               LocalDate joiningDate) {

        return !joiningDate.isAfter(LocalDate.now())
                && Period.between(dob, joiningDate).getYears() >= 18;
    }

    /**
     * Validates the crew member's years of experience.
     * <p>
     * Rules:
     * <ul>
     *   <li>Experience cannot be negative.</li>
     *   <li>Experience cannot exceed the number of years worked
     *       after the crew member turned 18.</li>
     * </ul>
     *
     * @param dob the crew member's date of birth
     * @param joiningDate the crew member's date of joining
     * @param experience the years of experience
     * @return true if the experience is valid; otherwise false
     */
    public static boolean isValidExperience(LocalDate dob,
                                            LocalDate joiningDate,
                                            int experience) {

        return experience >= 0
                && experience <= Period.between(
                dob.plusYears(18), joiningDate).getYears();
    }

    /**
     * Validates the crew member's date of joining.
     *
     * @param dob the crew member's date of birth
     * @param joiningDate the date of joining
     * @throws InvalidCrewException if the joining date is invalid
     */
    public static void validateDateOfJoining(LocalDate dob,
                                             LocalDate joiningDate)
            throws InvalidCrewException {

        if (!isValidDateOfJoining(dob, joiningDate)) {
            throw new InvalidCrewException(
                    "Invalid joining date. Crew must be at least 18 years old and the joining date cannot be in the future.");
        }
    }

    /**
     * Validates the crew member's years of experience.
     *
     * @param dob the crew member's date of birth
     * @param joiningDate the date of joining
     * @param experience the years of experience
     * @throws InvalidCrewException if the experience is invalid
     */
    public static void validateExperience(LocalDate dob,
                                          LocalDate joiningDate,
                                          int experience)
            throws InvalidCrewException {

        if (!isValidExperience(dob, joiningDate, experience)) {
            throw new InvalidCrewException(
                    "Invalid experience. It cannot be negative or exceed the years worked after turning 18.");
        }
    }
}
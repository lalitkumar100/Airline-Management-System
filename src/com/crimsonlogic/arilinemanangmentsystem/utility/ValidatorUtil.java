package com.crimsonlogic.arilinemanangmentsystem.utility;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class ValidatorUtil {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final String PHONE_REGEX =
            "^[0-9]{10}$";

    public static boolean isValidEmail(String email) {

        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidPhone(String phone) {

        return Pattern.matches(PHONE_REGEX, phone);
    }

    public static boolean isValidAge(LocalDate dob) {

        int age = Period.between(dob, LocalDate.now()).getYears();

        return age >= 0 && age <= 120;
    }
}
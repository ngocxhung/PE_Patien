package com.example.pe_patien.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Validation {
    // Regex for Patient Code: Starts with 'P', followed by 9 alphanumeric characters (total length 10)
    private static final String PATIENT_CODE_REGEX = "^P[a-zA-Z0-9]{1,10}$";

    // Regex for Patient Name: Letters and spaces only, up to 50 characters
    private static final String PATIENT_NAME_REGEX = "^[a-zA-Z\\s]{1,50}$";

    // Regex for DOB: DD/MM/YYYY format
    private static final String DOB_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";

    // Validate Patient Code
    public static boolean validatePatientCode(String patientCode) {
        if (patientCode == null || patientCode.isEmpty()) {
            return false; // Mandatory field
        }
        return Pattern.matches(PATIENT_CODE_REGEX, patientCode);
    }

    // Validate Patient Name
    public static boolean validatePatientName(String patientName) {
        if (patientName == null || patientName.isEmpty()) {
            return false; // Mandatory field
        }
        return Pattern.matches(PATIENT_NAME_REGEX, patientName);
    }

    // Validate DOB
    public static boolean validateDOB(String dob) {
        if (dob == null || dob.isEmpty()) {
            return false; // Mandatory field
        }

        // Check format with regex
        if (!Pattern.matches(DOB_REGEX, dob)) {
            return false;
        }

        // Parse and validate date range
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Strict date parsing
        try {
            Date date = sdf.parse(dob);

            // Check if date is after 1920
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            if (year <= 1920) {
                return false;
            }

            // Check if date is before current date
            Date currentDate = new Date();
            if (date.after(currentDate)) {
                return false;
            }

            return true;
        } catch (ParseException e) {
            return false; // Invalid date format
        }
    }

    // Validate Age
    public static boolean validateAge(String ageStr) {
        if (ageStr == null || ageStr.isEmpty()) {
            return false; // Mandatory field
        }

        try {
            int age = Integer.parseInt(ageStr);
            return age > 20 && age < 100;
        } catch (NumberFormatException e) {
            return false; // Not a valid integer
        }
    }

    // Method to validate all fields at once (e.g., when Save button is clicked)
    public static boolean validateAll(String patientCode, String patientName, String dob, String age) {
        return validatePatientCode(patientCode) &&
                validatePatientName(patientName) &&
                validateDOB(dob) &&
                validateAge(age);
    }
}
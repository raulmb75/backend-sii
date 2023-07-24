package com.sii.aspirantes.aspirantes.Utils;
import java.util.regex.Pattern;
public class RegexValidators {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    private static final String CURP_REGEX = "^[A-Z]{4}\\d{6}[HM]{1}[A-Z]{2}[^aeiouAEIOU]{3}\\d{2}$";
    //private static final String CURP_REGEX = "^[A-Z]{4}[0-9]{6}[HM]{1}[A-Z]{5}[0-9]{2}$";
    private static final Pattern curpPattern = Pattern.compile(CURP_REGEX);
    private static final String PHONE_REGEX = "^\\d{10}$";
    private static final Pattern phonePattern = Pattern.compile(PHONE_REGEX);

    public static boolean validateEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public static boolean validateCURP(String curp) {
        return curpPattern.matcher(curp).matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phonePattern.matcher(phoneNumber).matches();
    }
}

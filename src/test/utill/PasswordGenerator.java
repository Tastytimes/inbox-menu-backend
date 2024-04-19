package com.rs.listing.Restuarant.listing.utill;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
//    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";

    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER ;
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomPassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters");
        }

        StringBuilder sb = new StringBuilder(length);
        // random selection of characters from the available character sets
        for (int i = 0; i < length; i++) {
            int randomCharIndex = random.nextInt(PASSWORD_ALLOW_BASE.length());
            char randomChar = PASSWORD_ALLOW_BASE.charAt(randomCharIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }



}

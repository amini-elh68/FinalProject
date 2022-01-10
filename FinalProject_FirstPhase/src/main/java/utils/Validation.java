package utils;

import data.enums.Role;

public class Validation {

    public boolean isValidPassword(String password) {
        boolean isValidPassword = false;
        char[] chars = password.toCharArray();
        if (chars.length != 8) {
            return false;
        }
        boolean hasNumber = false;
        boolean hasLetter = false;
        for (char c : chars) {
            int i = Integer.valueOf(c);
            if (i >= 48 && i <= 57) {
                hasNumber = true;
            } else if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
                hasLetter = true;
            } else {
                return false;
            }
        }
        if (hasLetter && hasNumber) {
            isValidPassword = true;
        }
        return isValidPassword;
    }

    public boolean isValidRole(String role) {
        boolean isValidRole;
        try {
            Role.valueOf(role);
            isValidRole = true;
        } catch (Exception e) {
            isValidRole = false;
        }
        return isValidRole;
    }

    public boolean isValidEmail(String email) {
        String[] splitTxt = email.split("@");
        if (splitTxt.length != 2) {
            return false;
        }
        splitTxt = email.substring(email.indexOf("@")).split(".");
        if (splitTxt.length != 2) {
            return false;
        }
        if (email.indexOf("@") != 0 && email.lastIndexOf(".") != 0 && email.indexOf("@") < email.lastIndexOf(".")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidNumber(String number) {
        boolean isValidNumber;
        try {
            Integer.parseInt(number);
            isValidNumber = true;
        } catch (Exception e) {
            isValidNumber = false;
        }
        return isValidNumber;
    }

}

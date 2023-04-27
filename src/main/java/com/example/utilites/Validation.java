package com.example.utilites;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public boolean isValidPassword(String password) {
        return password.length() >= 8 && password.length()<=20;
    }
    public boolean isValidLogin(String login) {
        return login == null || login.trim().length() == 0;
    }
    public boolean isValidRole(String role){
        return role != null;
    }
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public boolean isValidDate(String date) {
        return date.length() == 10;
    }

    public Date date (String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }return birthday;
    }
}

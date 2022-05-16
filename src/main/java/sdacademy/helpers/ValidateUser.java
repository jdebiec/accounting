package sdacademy.helpers;

import sdacademy.exceptions.WrongLoginException;
import sdacademy.exceptions.WrongPasswordException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUser {
    public static void validateLogin (String login) throws WrongLoginException{
        Pattern pattern = Pattern.compile("[a-z0-9.]");
        Matcher matcher = pattern.matcher(login);

        if (login.length() < 4 || !matcher.find()){
            throw new WrongLoginException();
        }
    }

    public static void validatePassword (String password) throws WrongPasswordException{
        if(password.length() < 6 || password.contains(" ")){
            throw new WrongPasswordException();
        }
    }


}

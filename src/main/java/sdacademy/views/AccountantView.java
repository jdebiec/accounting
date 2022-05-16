package sdacademy.views;

import sdacademy.models.Accountant;
import java.util.List;

public class AccountantView {
    public static String printAccountants(List<Accountant> accountants) {
        StringBuilder sb = new StringBuilder();
        for (Accountant accountant : accountants) {
            sb.append(accountant.getLogin()).append("\n");
        }
        return sb.toString();
    }

    public static String printLoginSuccess(String login) {
        return "Good morning " + login;
    }

    public static String printNotFound(String login) {
        return "User " + login + " not found";
    }

    public static String printAddSuccess(String login){
        return "Added new user: " + login;
    }

    public static String printDuplicateFound(String login){
        return "User " + login + " already exists";
    }

    public static String printRemovedSuccess(String login){
        return "Removed user: " + login;
    }

    public static String printWrongLogin(){
        return "Login must be at least 4 characters long, allowed characters: a-b 0-9 and period [.]";
    }

    public static String printWrongPassword(){
        return "Password must be at least 6 characters long and must not contain spaces";
    }
}

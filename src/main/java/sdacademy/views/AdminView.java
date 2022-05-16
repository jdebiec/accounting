package sdacademy.views;

import sdacademy.models.Admin;
import java.util.List;

public class AdminView {
    public static String printAdmins(List<Admin> admins) {
        StringBuilder sb = new StringBuilder();
        for (Admin admin : admins) {
            sb.append(admin.getLogin()).append("\n");
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

    public static String printMinimumAccount(){
        return "There must be at least one administrator";
    }

    public static String printWrongLogin(){
        return "Login must be at least 4 characters long, allowed characters: a-b 0-9 and period [.]";
    }

    public static String printWrongPassword(){
        return "Password must be at least 6 characters long and must not contain spaces";
    }
}

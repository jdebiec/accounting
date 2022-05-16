package sdacademy.models;

import sdacademy.exceptions.AdminNotFoundException;
import sdacademy.exceptions.DuplicateFoundException;
import sdacademy.exceptions.MinimumAccountException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class AdminRegistry implements Serializable {
    private static AdminRegistry instance = null;
    final private String filename = "data/admin.dat";

    public static AdminRegistry getInstance() {
        if (instance == null) {
            instance = new AdminRegistry();
        }
        return instance;
    }

    private ArrayList<Admin> admins;

    private AdminRegistry() {
        try {
            admins = (ArrayList<Admin>) FileHandler.deserialize(filename);
        } catch (IOException e) {
            admins = new ArrayList<>();
            admins.add(new Admin("admin", "admin"));
        } catch (ClassNotFoundException e) {
            System.err.println("Serialization error!");
        }
    }

    public void findAdmin(String login, String password) throws AdminNotFoundException {
        for (Admin admin : this.admins) {
            if (admin.getLogin().equals(login) && admin.getPassword().equals(password)) {
                return;
            }
        }
        throw new AdminNotFoundException();
    }

    public void addAdminAccount(String login, String password) throws DuplicateFoundException {
        for (Admin admin : admins) {
            if (admin.getLogin().equals(login))
                throw new DuplicateFoundException();
        }
        this.admins.add(new Admin(login, password));
    }

    public void removeAdminAccount(String login) throws AdminNotFoundException, MinimumAccountException {
        if (this.admins.size() <= 1) {
            throw new MinimumAccountException();
        }
        for (Admin admin : admins) {
            if (admin.getLogin().equals(login)) {
                this.admins.remove(admin);
                return;
            }
        }
        throw new AdminNotFoundException();
    }

    public void saveData() {
        try {
            FileHandler.serialize(this.admins, filename);
        } catch (IOException e) {
            System.err.println("Write error or file not found.");
        }
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }
}

package sdacademy.models;

import java.io.Serializable;

public class Accountant implements Serializable{

    private String login;
    private String password;

    public Accountant (String login, String password) {
        this.login = login;
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}

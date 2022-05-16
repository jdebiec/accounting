package sdacademy;

import sdacademy.controllers.AccountantController;
import sdacademy.controllers.AdminController;
import sdacademy.models.AccountantRegistry;
import sdacademy.models.AdminRegistry;
import java.util.Scanner;

public class Main {

    private static boolean loggedAsAdmin = false;
    private static boolean loggedAsAcct = false;

    public enum State {
        INIT,
        LOGGING_IN_AS_ADMIN,
        LOGGING_IN_AS_ACCT,

        LOGGED_AS_ADMIN,
        ADMIN_ACCOUNTS_OPTIONS,
        ACCT_ACCOUNTS_OPTIONS,
        COMPANY_OPTIONS,
        CREATING_COMPANY,

        LOGGED_AS_ACCT,
        ACCT_OPTIONS,

        SAVE_DATA,
        EXIT,
    }

    public static void main(String[] args) {
        State state = State.INIT;
        Scanner scanner = new Scanner(System.in);

        while (state != State.EXIT) {
            switch (state) {
                case INIT: {
                    System.out.println("Good morning, what do you want to do?");
                    System.out.println(" 1 - Log in as an administrator");
                    System.out.println(" 2 - Log in as an accountant");
                    System.out.println(" 0 - Exit the program");

                    String answer = scanner.nextLine();

                    switch (answer) {
                        case ("1"):
                            state = State.LOGGING_IN_AS_ADMIN;
                            break;

                        case ("2"):
                            state = State.LOGGING_IN_AS_ACCT;
                            break;

                        case ("0"):
                            state = State.EXIT;
                            break;

                        default:
                            System.out.println("Wrong answer");
                            state = State.INIT;
                            break;
                    }
                    break;
                }

                case LOGGING_IN_AS_ADMIN: {
                    System.out.println("Enter login:");
                    String login = scanner.nextLine();

                    System.out.println("Enter hasło:");
                    String password = scanner.nextLine();

                    boolean loginSuccess = AdminController.loginAdmin(login, password);
                    if (loginSuccess) {
                        state = State.LOGGED_AS_ADMIN;
                    } else {
                        state = State.INIT;
                    }
                    break;
                }

                case LOGGED_AS_ADMIN: {
                    System.out.println("What do you want to do?");
                    System.out.println(" 1 - admins accounts management");
                    System.out.println(" 2 - accountants accounts management");
                    System.out.println(" 3 - companies management");
                    System.out.println(" 0 - exit the program");

                    String answer = scanner.nextLine();

                    switch (answer) {
                        case ("1"):
                            state = State.ADMIN_ACCOUNTS_OPTIONS;
                            break;
                        case ("2"):
                            state = State.ACCT_ACCOUNTS_OPTIONS;
                            break;
                        case ("3"):
                            state = State.COMPANY_OPTIONS;
                            break;

                        case ("0"):
                            state = State.EXIT;
                            break;

                        default:
                            System.out.println("Wrong answer");
                            state = State.LOGGED_AS_ADMIN;
                            break;
                    }
                    break;
                }

                case ADMIN_ACCOUNTS_OPTIONS: {
                    String login, password;

                    System.out.println("Administrators' accounts");
                    System.out.println("1 - Show admin accounts");
                    System.out.println("2 - add new admin account");
                    System.out.println("3 - remove admin account");
                    System.out.println("0 - go back");

                    String answer = scanner.nextLine();

                    switch (answer) {
                        case ("1"):
                            AdminController.printAdmins();
                            break;

                        case ("2"):
                            System.out.println("Enter login: ");
                            login = scanner.nextLine();
                            System.out.println("Enter password: ");
                            password = scanner.nextLine();
                            AdminController.addAdmin(login, password);
                            break;

                        case ("3"):
                            System.out.println("Enter login: ");
                            login = scanner.nextLine();
                            AdminController.removeAdmin(login);
                            break;

                        case ("0"):
                            state = State.LOGGED_AS_ADMIN;
                            break;

                        default: {
                            System.out.println("Wrong answer");
                            state = State.ADMIN_ACCOUNTS_OPTIONS;
                        }
                    }
                    break;
                }
                case LOGGING_IN_AS_ACCT: {
                    System.out.println("Enter login:");
                    String login = scanner.nextLine();

                    System.out.println("Enter password:");
                    String password = scanner.nextLine();

                    boolean loginSuccess = AdminController.loginAdmin(login, password);
                    if (loginSuccess) {
                        state = State.ACCT_ACCOUNTS_OPTIONS;
                    } else {
                        state = State.INIT;
                    }
                    break;
                }
                case ACCT_ACCOUNTS_OPTIONS: {
                    String login, password;

                    System.out.println("Accountants");
                    System.out.println("1 - show accountant accounts");
                    System.out.println("2 - add new accountant account");
                    System.out.println("3 - remove accountant account");
                    System.out.println("0 - go back");

                    String answer = scanner.nextLine();
                    switch (answer) {
                        case "1":
                            AccountantController.listAccountants();

                            break;
                        case "2":
                            System.out.println("Enter login: ");
                            login = scanner.nextLine();
                            System.out.println("Enter password: ");
                            password = scanner.nextLine();
                            AccountantController.addAccountant(login, password);

                            break;
                        case "3":
                            System.out.println("Enter login: ");
                            login = scanner.nextLine();

                            AccountantController.removeAccountant(login);

                            break;
                        case "0":
                            state = State.LOGGED_AS_ADMIN;
                            break;
                        default: {
                            System.out.println("Wrong answer");
                            state = State.ACCT_ACCOUNTS_OPTIONS;
                        }
                    }
                    break;
                }

                case LOGGED_AS_ACCT: {
                    System.out.println("What do you want to do?");
                    System.out.println("1 - show accountant accounts");
                    System.out.println("2 - add company");
                    System.out.println("3 - add contractor");
                    System.out.println("4 - add invoice");
                    System.out.println("0 - go back");

                    String answer = scanner.nextLine();
                    switch (answer) {
                        case "1":
                            AccountantController.listAccountants();
                        case "2":
                            AccountantController.addCompany();
                        case "3":
                            AccountantController.addContrctor();
                        case "4":
                            AccountantController.addInvoice();
                        case "0":
                            state = State.ACCT_ACCOUNTS_OPTIONS;
                    }
                }
                case COMPANY_OPTIONS: {
                    //todo company options for admin
                }
            }
        }
        saveData();
    }

    private static void saveData() {
        AccountantRegistry.getInstance().saveData();
        AdminRegistry.getInstance().saveData();

    }
}

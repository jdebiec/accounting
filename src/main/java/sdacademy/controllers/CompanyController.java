package sdacademy.controllers;

import sdacademy.exceptions.*;
import sdacademy.models.Admin;
import sdacademy.models.Company;
import sdacademy.models.CompanyRegistry;
import sdacademy.views.CompanyView;

public class CompanyController {
    public static void createCompany(String name, int yearFound, String nip) throws DuplicateFoundException, IncorrectNipException {
        CompanyRegistry.getInstance().add(new Company(name, yearFound, nip));
    }

    public static void listCompanies() {
        CompanyView.printCompanies(CompanyRegistry.getInstance().getCompanies());

    }

    public static void removeCompany(String name) {
        try {
            CompanyRegistry.getInstance().removeCompany(name);

        } catch (CompanyNotFoundException e) {

        }
    }

    public static void assignCompany(String name, String login) {
        try {
            CompanyRegistry.getInstance().assignAccountant(name, login);
        } catch (CompanyNotFoundException e) {

        } catch (AccountantNotFoundException e) {

        }
        CompanyView.assignedAccountant(name, login);
    }
}

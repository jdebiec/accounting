package sdacademy.ui;

import sdacademy.exceptions.DuplicateFoundException;
import sdacademy.exceptions.IncorrectNipException;
import sdacademy.models.Company;
import sdacademy.models.CompanyRegistry;

import java.util.Scanner;

public class CompaniesUI {
    public static void main(String[] args) {


    }
    public static Company addCompany(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("NIP number of the company you want to add: ");
        String nip = scanner.nextLine();
        if(CompanyRegistry.checkCompanyByNip(nip)) {
            System.out.println("This company is already in the system!");
            return null;
        }

        System.out.println("Company's name: ");
        String name = scanner.nextLine();
        System.out.println("Year of establishment: ");
        int yearOfFound = scanner.nextInt();
        Company company = null;
        try {
            company = new Company(name, yearOfFound, nip);
        } catch (IncorrectNipException e) {
            e.getMessage();
        }
        return company;

    }
}

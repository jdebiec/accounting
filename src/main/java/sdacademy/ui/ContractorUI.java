package sdacademy.ui;

import sdacademy.exceptions.ContractorNotFoundException;
import sdacademy.models.Contractor;
import sdacademy.models.ContractorRegistry;

import java.util.Scanner;

public class ContractorUI {
    public static void main(String[] args) {

    }
    public static Contractor addContractor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("NIP of the contractor you want to add: ");
        String nip = scanner.nextLine();
        try{
            ContractorRegistry.findContractorByNip(nip);
            System.out.println("This contractor is already in the system!");
            return null;
        } catch (ContractorNotFoundException e) {
            e.getMessage();
        }
        System.out.println("Company's name: ");
        String name = scanner.nextLine();
        System.out.println("Year of establishment: ");
        int yearOfFound = scanner.nextInt();
        Contractor contractor = new Contractor(name, yearOfFound, nip);
        return contractor;
    }
}



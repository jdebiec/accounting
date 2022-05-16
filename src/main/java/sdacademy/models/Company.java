package sdacademy.models;

import sdacademy.exceptions.DuplicateFoundException;
import sdacademy.exceptions.IncorrectNipException;
import sdacademy.helpers.NipValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private int yearFound;
    private String nip;
    final private String filename = "data/invoiceListCompany.dat";

    public Company(String name, int yearFound, String nip) throws IncorrectNipException {
        CompanyRegistry.getInstance().lookForDuplicate(nip);

        if (NipValidator.validateNip(nip)) {
            this.name = name;
            this.yearFound = yearFound;
            this.nip = nip;
        }
        throw new IncorrectNipException("Unable to create company, invalid NIP number");
    }

    public void assignAccountant(String accountant) {
        accountantsCompany.add(accountant);
    }

    public void addInvoice(Invoice invoice){
        invoicesListCompany.add(invoice);
        try {
            FileHandler.serialize(invoice, filename);

        } catch (IOException e) {
            System.err.println("Write error or file not found.");
        }
    }

    public List<Invoice> getInvoicesListCompany(){
        return invoicesListCompany;
    }

    public String getName() {
        return name;
    }

    public int getYearFound() {
        return yearFound;
    }

    public String getNip() {
        return nip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    private ArrayList<String> accountantsCompany = new ArrayList<>();

    private List<Invoice> invoicesListCompany = new ArrayList<>();
}

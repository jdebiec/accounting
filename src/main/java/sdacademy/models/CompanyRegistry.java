package sdacademy.models;

import sdacademy.exceptions.AccountantNotFoundException;
import sdacademy.exceptions.CompanyNotFoundException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompanyRegistry implements Serializable{
    private static CompanyRegistry instance = null;
    final private String filename = "data/company.dat";
    public static CompanyRegistry getInstance() {
        if(instance == null) {
            instance = new CompanyRegistry();
        }
        return instance;
    }

    private ArrayList<Company> companies;

    public CompanyRegistry() {
        try{
            this.companies = (ArrayList<Company>) FileHandler.deserialize(filename);
        }catch (IOException e){
            this.companies = new ArrayList<>();
        }catch (ClassNotFoundException e){
            System.err.println("Serialization error!");
        }
    }

    public Company findCompany(String name) throws CompanyNotFoundException {
        for (Company company : companies) {
            if (company.getName().equals(name)) {
                return company;
            }
        }
        throw new CompanyNotFoundException();
    }

    public List<Company> getCompanies() {
        return this.companies;
    }

    public void add(Company company) {
        this.companies.add(company);
    }

    public void saveData(String filename){
        try {
            FileHandler.serialize(this.companies, filename);
        }catch (IOException e){
            System.err.println("Write error or file not found.");
        }
    }

    public void removeCompany(String name) throws CompanyNotFoundException {
        try {
            Company temp = findCompany(name);
            companies.remove(temp);
        } catch (CompanyNotFoundException e) {
            // return "Firma nie istnieje";
        }
    }

    public boolean lookForDuplicate(String nip) {
        for (Company company : companies) {
            if (company.getNip().equals(nip)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkCompanyByNip(String nip){
        for(Company company : getInstance().companies) {
            if (company.getNip().equals(nip)) {
                return true;
            }
        }
        return false;
    }

    public static Company findCompanyByNip(String nip) throws CompanyNotFoundException {
        for(Company company : getInstance().companies){
            if(company.getNip().equals(nip)){
                return company;
            }
        }
        throw new CompanyNotFoundException();
    }

    public void assignAccountant(String name, String login) throws AccountantNotFoundException, CompanyNotFoundException {
       Accountant accountant = AccountantRegistry.getInstance().findAccountantByLogin(login);
        Company company = findCompany(name);
        company.assignAccountant(accountant.getLogin());
    }
}

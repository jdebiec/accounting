package sdacademy.models;

import sdacademy.exceptions.ContractorNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ContractorRegistry {
    private static ContractorRegistry instance = null;

    public static ContractorRegistry getInstance() {
        if (instance == null) {
            instance = new ContractorRegistry();
        }
        return instance;
    }

    private static ArrayList<Contractor> contractors;

    public ContractorRegistry() {
        this.contractors = new ArrayList<>();

        this.contractors.add(new Contractor("Jurex sp. z o.o.", 1992, " 7251801126"));
        this.contractors.add(new Contractor("MP-Mosty s.j.", 1999, "4582668978"));
    }

    public Contractor findContractorByName(String name) throws ContractorNotFoundException {
        for (Contractor contractor : contractors) {
            if (contractor.getName().equals(name)) {
                return contractor;
            }
        }
        throw new ContractorNotFoundException();
    }

    public static Contractor findContractorByNip(String nip) throws ContractorNotFoundException{
        for(Contractor contractor : contractors){
            if(contractor.getNip().equals(nip)){
                return contractor;
            }
        }
        throw new ContractorNotFoundException();
    }

    public static boolean checkContractorByNip(String nip){
        for(Contractor contractor : contractors) {
            if (contractor.getNip().equals(nip)) {
                return true;
            }
        }
        return false;
    }

    public List<Contractor> getContractor() {
        return this.contractors;
    }

    public void addContractor(Contractor contractor) {
        this.contractors.add(contractor);
    }

    public void removeContractor(String name) throws ContractorNotFoundException {
        try {
            Contractor temp = findContractorByName(name);
            contractors.remove(temp);
        } catch (ContractorNotFoundException e) {
           e.getMessage();
        }
    }
}

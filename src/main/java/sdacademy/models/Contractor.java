package sdacademy.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Contractor {
    private String name;
    private int yearFound;
    private String nip;
    final private String filename = "data/invoiceListContractor.dat";

    public Contractor(String name, int yearFound, String nip) {
        this.name = name;
        this.yearFound = yearFound;
        this.nip = nip;
    }

    private List<Invoice> invoicesListContractor = new ArrayList<>();

    public void addInvoice(Invoice invoice){
        invoicesListContractor.add(invoice);
        try {
            FileHandler.serialize(invoice, filename);

        } catch (IOException e) {
            System.err.println("Write error or file not found.");
        }
    }

    public List<Invoice> getInvoicesListContractor(){
        return invoicesListContractor;
    }
    public String getName() {
        return name;
    }

    public int getYearFound() {
        return yearFound;
    }

    public String getNip() { return nip; }

    public void setName(String name) {
        this.name = name;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}


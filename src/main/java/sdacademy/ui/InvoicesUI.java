package sdacademy.ui;

import sdacademy.enums.TypeInvoice;
import sdacademy.exceptions.CompanyNotFoundException;
import sdacademy.exceptions.ContractorNotFoundException;
import sdacademy.models.*;
import java.math.BigDecimal;
import java.util.Scanner;

enum State{
    INIT,
    ADD_COMPANY,
    ADD_CONTRACTOR,
    INIT_CD,
    ADD_INVOICE,
}

public class InvoicesUI {
    public static void main(String[] args) {


    }
    public static Invoice getInvoice() {
        Invoice invoice = null;
        Company company = null;
        Contractor contractor = null;
        TypeInvoice type;
        BigDecimal rateVat;
        boolean stateInvoice;

        State state = State.INIT;
        Scanner scanner = new Scanner(System.in);
        switch (state) {
            case INIT :{
                System.out.println("NIP of the company you want to add invoice for: ");
                String nipCompany = scanner.nextLine();
                try {
                    company = CompanyRegistry.findCompanyByNip(nipCompany);
                } catch (CompanyNotFoundException e) {
                    System.out.println("Do you want to add new company?\n" +
                            "1 - yes\n" +
                            "2 - no");
                    String choice = scanner.nextLine();
                    if (choice.equals("1")) {
                        state = State.ADD_COMPANY;
                        break;
                    } else {
                        break;
                    }
                }
                state = State.INIT_CD;
                break;
            }
            case ADD_COMPANY: {
                company = CompaniesUI.addCompany();
                state = State.INIT_CD;
                break;
            }
            case INIT_CD: {
                System.out.println("NIP  of the contractor you want to add invoice for: ");
                String nipContractor = scanner.nextLine();
                try {
                    contractor = ContractorRegistry.findContractorByNip(nipContractor);
                } catch (ContractorNotFoundException e) {
                    System.out.println("Do you want to add new contractor?\n" +
                            "1 - yes\n" +
                            "2 - no?");
                    String choice = scanner.nextLine();
                    if (choice.equals("1")) {
                        state = State.ADD_CONTRACTOR;
                        break;
                    } else {
                        break;
                    }
                }
                state = State.ADD_INVOICE;
                break;
            }
            case ADD_CONTRACTOR:{
                contractor = ContractorUI.addContractor();
                state = State.ADD_INVOICE;
                break;
            }
            case ADD_INVOICE:{
                System.out.println("Invoice type:\n" +
                        "1 - sales invoice\n" +
                        "2 - purchase invoice");
                String choice = scanner.nextLine();
                if(choice.equals("1")){
                    type = TypeInvoice.SALE;
                }else if(choice.equals("2")){
                    type = TypeInvoice.PURCHASE;
                }else {
                    System.out.println("Wrong invoice type");
                    state = State.ADD_INVOICE;
                    break;
                }
                System.out.println("The net amount of the invoice");
                double netAmout = scanner.nextDouble();
                BigDecimal netAmountBD = new BigDecimal(netAmout);
                System.out.println("VAT rate:\n" +
                        "1 - VAT = 8%\n" +
                        "2 - VAT = 23%");
                choice = scanner.nextLine();
                if(choice.equals("1")){
                    rateVat = new BigDecimal(0.08);
                }else if(choice.equals("2")){
                    rateVat = new BigDecimal(0.23);
                }else {
                    System.out.println("Wrong VAT rate");
                    state = State.ADD_INVOICE;
                    break;
                }
                System.out.println("Is the invoice paid?\n" +
                        "1 - Yes, it is\n" +
                        "2 - No, it is not");
                if(choice.equals("1")){
                    stateInvoice = true;
                }else if(choice.equals("2")){
                    stateInvoice = false;
                }else {
                    System.out.println("You did not choose the correct invoice state");
                    state = State.ADD_INVOICE;
                    break;
                }
                invoice = new Invoice(type, netAmountBD, rateVat, stateInvoice);
            }
        }
        return invoice;
    }
}

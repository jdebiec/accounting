package sdacademy.views;

import sdacademy.models.Company;

import java.util.List;

public class CompanyView {
    public static void printCompanies(List<Company> companies) {
        for(Company company : companies) {
            System.out.println(company.getName() + " (Year of establishment: " + company.getYearFound() + ")"
                                                + "NIP: " + company.getNip());
        }
    }

    public static String assignedAccountant (String name, String login) {
        return "Accountant " + login +  " was assigned to " + name + " company";
    }

    public static String companyAdded () {
        //System.out.println("Company " + );
        return null;
    }


}

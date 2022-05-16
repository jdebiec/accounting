package sdacademy.helpers;

import sdacademy.exceptions.IncorrectNipException;
import sdacademy.models.Company;
import sdacademy.models.CompanyRegistry;

public class NipValidator {
    CompanyRegistry companyRegistry;

    public static boolean validateNip(String nip) {

        int sum = (nip.charAt(0) * 6 +
                nip.charAt(1) * 5 +
                nip.charAt(2) * 7 +
                nip.charAt(3) * 2 +
                nip.charAt(4) * 3 +
                nip.charAt(5) * 4 +
                nip.charAt(6) * 5 +
                nip.charAt(7) * 6 +
                nip.charAt(8) * 7);

        sum %= 11;

        if (!(nip.length() == 10 && sum == nip.charAt(9))) {
           return false;
        }
        return true;
    }
}
package sdacademy.models;

import sdacademy.enums.TypeInvoice;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class Invoice implements Serializable{
    private TypeInvoice type;
    private String numberInvoice;
    private BigDecimal netAmout;
    private BigDecimal rateVat;
    private boolean state;
    private Integer NumberSaleInvoice = 1;
    private Integer NumberPurhaseInvoice = 1;
    final private String filenameSale = "data/numberSaleInvoice.dat";
    final private String filenamePurhase = "data/numberPurhaseInvoice.dat";

    public Invoice(TypeInvoice type, BigDecimal netAmout, BigDecimal rateVat, boolean state) {
        this.type = type;
        this.numberInvoice = createInvoiceNumber(type);
        this.netAmout = netAmout;
        this.rateVat = rateVat;
        this.state = state;
        saveInvoiceNumber();
    }

    private String createInvoiceNumber(TypeInvoice typeInvoice){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        deserializableInvoicSaleNumber();
        eserializableInvoicPurhaseNumber();

        if(typeInvoice.equals(TypeInvoice.SALE))  {
            sb.append(dateFormat)
                    .append("/" + String.valueOf(NumberSaleInvoice));
            this.NumberSaleInvoice++;
        }else {
            sb.append(dateFormat)
                    .append("/" + String.valueOf(NumberPurhaseInvoice));
            this.NumberPurhaseInvoice++;
        }
        saveInvoiceNumber();
        return sb.toString();
    }

    private void eserializableInvoicPurhaseNumber() {
        try {
            this.NumberPurhaseInvoice = (Integer) FileHandler.deserialize(filenamePurhase);
        }catch (ClassNotFoundException e) {
            System.err.println("Serialization error!");
        } catch (IOException e) {
            this.NumberPurhaseInvoice =1;
        }
    }

    private void deserializableInvoicSaleNumber() {
        try {
            this.NumberSaleInvoice = (Integer) FileHandler.deserialize(filenameSale);
        }catch (ClassNotFoundException e) {
            System.err.println("Serialization error!");
        } catch (IOException e) {
            this.NumberSaleInvoice =1;
        }
    }

    private void saveInvoiceNumber() {
        try {
            Object objectSale = NumberSaleInvoice;
            Object objectPurhase = NumberPurhaseInvoice;
            FileHandler.serialize(objectSale, this.numberInvoice);
            FileHandler.serialize(objectPurhase, this.numberInvoice);
        } catch (IOException e) {
            System.err.println("Write error or file not found.");
        }
    }

}
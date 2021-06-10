package pl.futurecollars.invocing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import pl.futurecollars.invocing.db.Database;
import pl.futurecollars.invocing.db.InMemoryDatabase;
import pl.futurecollars.invocing.model.Company;
import pl.futurecollars.invocing.model.Invoice;
import pl.futurecollars.invocing.model.InvoiceEntry;
import pl.futurecollars.invocing.model.Vat;
import pl.futurecollars.invocing.service.InvoiceService;

public class App {


    public static void main(String[] args) {

        Database db = new InMemoryDatabase();
        InvoiceService service = new InvoiceService(db);

        Company buyer = new Company("5213861303", "ul. Bukowińska 24d/7 02-703 Warszawa, Polska", "iCode Trust Sp. z o.o");
        Company seller = new Company("552-168-66-00", "32-005 Niepolomice, Nagietkowa 19", "Piotr Kolacz Development");

        List<InvoiceEntry> products = List.of(new InvoiceEntry("Programming course", BigDecimal.valueOf(10000), BigDecimal.valueOf(2300), Vat.VAT_23));

        Invoice invoice = new Invoice(LocalDate.now(), buyer, seller, products);

        int id = service.save(invoice);

        service.getById(id).ifPresent(System.out::println);

        System.out.println(service.getAll());

        service.delete(id);

    }
}


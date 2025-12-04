package org.example;

import org.example.soapperfume.PerfumeTransaction;
import org.example.soapperfume.PerfumeTransactionSoapService;
import org.example.soapperfume.PerfumeTransactionSoapServiceImplService;

import java.util.Scanner;
import java.net.URL;

/**
 * Handles SOAP operations
 *
 * @author Fardin
 * @since 2025-11-24
 */

public class Main {

    private static final String WSDL_URL = "http://localhost:8083/perfumetransactionsoapservice?wsdl";
    final public static String MENU = "\nMain Menu \n"
            + "G) Get by ID (SOAP)\n"
            + "X) eXit";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String option;

        do {
            System.out.println(MENU);
            System.out.print("Enter option: ");
            option = input.nextLine().toUpperCase();

            switch (option) {

                case "G":
                    getByIdSoap(input);
                    break;

                case "X":
                    System.out.println("Exiting the client application.");
                    break;

                default:
                    if (!option.equals("X")) {
                        System.out.println("Invalid option. Please try again.");
                    }
            }
        } while (!option.equals("X"));

        input.close();
    }


    public static void getByIdSoap(Scanner input) {
        System.out.print("Enter ID of transaction to retrieve via SOAP: ");

        try {
            int idToGet = Integer.parseInt(input.nextLine());
            URL wsdlLocation = new URL(WSDL_URL);
            PerfumeTransactionSoapServiceImplService service = new PerfumeTransactionSoapServiceImplService(wsdlLocation);
            PerfumeTransactionSoapService port = service.getPerfumeTransactionSoapServiceImplPort();
            PerfumeTransaction transaction = port.findById(idToGet);

            if (transaction != null) {
                System.out.println("\n✅ Retrieved via SOAP:");
                System.out.println("ID: " + transaction.getId());
                System.out.println("Date: " + transaction.getTransactionDate());
                System.out.println("Customer: " + transaction.getCustomerName());
                System.out.println("Choice: " + transaction.getPerfumeChoice());
                System.out.println("Total: $" + transaction.getTotal());
            } else {
                System.out.println("❌ No transaction found with ID: " + idToGet);
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numerical ID.");
        } catch (Exception e) {
            System.err.println("SOAP Service Error: Could not connect or retrieve data. Ensure the SOAP publisher is running on port 8083.");
        }
    }
}
package ca.hccis.main;

import ca.hccis.student.util.UtilityRest;
import com.google.gson.Gson;
// Import your client model class
import ca.hccis.model.jpa.PerfumeTransactionClient;

import org.json.JSONArray;

import java.util.Scanner;

/**
 * Handles REST operations
 *
 * @author Fardin
 * @since 2025-11-24
 */

public class Controller {

    final public static String MENU = "\nMain Menu for the REST service\n"
            + "A) Add Transaction\n"
            + "U) Update Transaction\n"
            + "V) View All Transactions\n"
            + "R) Read by ID\n"
            + "D) Delete Transaction\n"
            + "X) eXit";

    final static Scanner input = new Scanner(System.in);
    private static final String URL_STRING = "http://localhost:8080/api/PerfumeService/v1/transactions";

    public static void main(String[] args) {
        boolean endProgram = false;
        do {
            System.out.println(MENU);
            System.out.print("Enter choice: ");
            String choice = input.nextLine();

            PerfumeTransactionClient transaction;
            String url = URL_STRING;

            switch (choice.toUpperCase()) {
                case "A":
                    transaction = create();
                    System.out.println("Sending to URL: " + url);
                    Object response = UtilityRest.addUsingRest(url, transaction);

                    if (response != null) {
                        System.out.println("Transaction processed.");
                    }
                    break;

                case "U":
                    System.out.print("Enter ID of transaction to update: ");
                    int idToUpdate = Integer.parseInt(input.nextLine());

                    PerfumeTransactionClient updatedTransaction = create();
                    updatedTransaction.setId(idToUpdate);

                    url = URL_STRING;

                    System.out.println("Sending update for ID: " + idToUpdate);

                    PerfumeTransactionClient temp = (PerfumeTransactionClient) UtilityRest.updateUsingRest(url, updatedTransaction);

                    if (temp != null) {
                        System.out.println("Successfully Updated Entity: " + temp.toString());
                    }
                    break;

                case "R":
                    System.out.print("Enter ID of transaction to retrieve via REST: ");
                    try {
                        int idToGet = Integer.parseInt(input.nextLine());
                        PerfumeTransactionClient transactionForIdView = (PerfumeTransactionClient) UtilityRest.getByIdUsingRest(URL_STRING, idToGet);
                        if (transactionForIdView != null) {
                            System.out.println("\n✅ Retrieved via REST:");
                            System.out.println(transactionForIdView.toString());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case "D":
                    System.out.print("Enter ID to delete: ");
                    int id = 0;
                    try {
                        id = Integer.parseInt(input.nextLine());
                        UtilityRest.deleteUsingRest(url, id);
                        System.out.println("Delete request sent.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                    }
                    break;

                case "V":
                    String jsonReturned = UtilityRest.getJsonFromRest(url);

                    if (jsonReturned == null || jsonReturned.isEmpty()) {
                        System.out.println("No data returned or API not reachable.");
                        break;
                    }

                    System.out.println("--- Current Transactions ---");
                    JSONArray jsonArray = new JSONArray(jsonReturned);
                    Gson gson = new Gson();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        PerfumeTransactionClient current = gson.fromJson(jsonArray.getJSONObject(i).toString(), PerfumeTransactionClient.class);
                        System.out.println(current.toString());
                    }
                    break;

                case "X":
                    endProgram = true;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("INVALID OPTION");
            }
        } while (!endProgram);
    }

    /**
     * Helper to create the object and trigger the user input method
     */
    public static PerfumeTransactionClient create() {
        PerfumeTransactionClient pt = new PerfumeTransactionClient();
        pt.getInformation();
        return pt;
    }
}
package ca.hccis.main; // Adjust package
// Ensure you have the UtilityRest class available in your client project

import ca.hccis.student.util.UtilityRest;
import com.google.gson.Gson;
// Import your client model class
import ca.hccis.model.jpa.PerfumeTransactionClient;

import org.json.JSONArray;

import java.util.Scanner;

public class Controller {

    final public static String MENU = "\nMain Menu \n"
            + "A) Add Transaction\n"
            + "U) Update Transaction\n"
            + "V) View All Transactions(REST)\n"
            + "R) Read by ID (REST)\n"
            + "G) Get by ID (SOAP)\n"
            + "D) Delete Transaction\n"
            + "X) eXit";

    final static Scanner input = new Scanner(System.in);

    // **IMPORTANT:** Update this URL to match your server's port and path
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
                    // 1. Create object and get input from user
                    transaction = create();
                    System.out.println("Sending to URL: " + url);

                    // 2. Send object to API
                    // Note: UtilityRest.addUsingRest needs to return a generic Object that we cast
                    // You might need to adjust UtilityRest or how you cast depending on that class's implementation.
                    // Assuming UtilityRest returns a mapped object or you map it manually:
                    Object response = UtilityRest.addUsingRest(url, transaction);

                    if (response != null) {
                        System.out.println("Transaction processed.");
                    }
                    break;

                case "U":
                    System.out.print("Enter ID of transaction to update: ");
                    int idToUpdate = Integer.parseInt(input.nextLine());

                    PerfumeTransactionClient updatedTransaction = create(); // Re-use create to get all new field values
                    updatedTransaction.setId(idToUpdate); // Set the required ID on the object

                    url = URL_STRING; // Base URL is typically used for PUT

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

                        // Call the new UtilityRest method
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
                        // 3. Call Delete
                        UtilityRest.deleteUsingRest(url, id);
                        System.out.println("Delete request sent.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                    }
                    break;

                case "V":
                    // 4. Get JSON String from API
                    String jsonReturned = UtilityRest.getJsonFromRest(url);

                    if (jsonReturned == null || jsonReturned.isEmpty()) {
                        System.out.println("No data returned or API not reachable.");
                        break;
                    }

                    System.out.println("--- Current Transactions ---");
                    JSONArray jsonArray = new JSONArray(jsonReturned);
                    Gson gson = new Gson();

                    // 5. Loop and Display
                    for (int i = 0; i < jsonArray.length(); i++) {
                        // Deserialize JSON back to your Client Object
                        PerfumeTransactionClient current = gson.fromJson(jsonArray.getJSONObject(i).toString(), PerfumeTransactionClient.class);
                        System.out.println(current.toString());
                    }
                    break;
                case "G":
                    System.out.println("\nPlease run the client application for SOAP \n" +
                            "It's inside the project folder. Thanks!");
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
        pt.getInformation(); // This calls the method we wrote in Step 1
        return pt;
    }
}
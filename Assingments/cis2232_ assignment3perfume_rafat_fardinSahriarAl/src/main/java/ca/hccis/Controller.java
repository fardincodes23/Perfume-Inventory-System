package ca.hccis;

import ca.hccis.entity.Perfume;
import ca.hccis.util.CisUtility;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Working to create an Application for a perfume shop. Here it will prompt user with the options choose.A
 *
 * @author Fardin Sahriar Al Rafat
 * @since 20250917
 * Took the structure from @BJM
 */
public class Controller {


    public static final String EXIT = "X";

    private static final String MENU
            = "\n-------------------------\n"
            + "- Perfume Shop -\n"
            + "- Select A,B or X -\n"
            + "- A) Buy a perfume\n"
            + "- V) View perfume total\n"
            + "- X) EXIT\n"
            + "-------------------------\n"
            + "Option-->";

    //Adding Json stuffs
    public static final String PATH = "c:\\CIS2232\\";
    public static final String FILE_NAME = "perfume_data_rafat_fardinsahriaral.json";
    private static Path journalPath = null;
    private static FileWriter journalWriter = null;

    private static Perfume perfume; // it will keep record of the perfume purchase

    public static void main(String[] args) {

        // Create new file if it doesn't already exist
        journalPath = Paths.get(PATH + FILE_NAME);

        // Make sure the directory exists
        File directory = new File(PATH);
        if (!directory.exists()) {
            directory.mkdirs(); // actually creates C:\CIS2232 if missing
        }

        // make sure the file exists
        File journalFile = new File(journalPath.toString());
        if (!journalFile.exists()) {
            try {
                journalFile.createNewFile(); // actually creates the .json file
            } catch (IOException e) {
                System.out.println("Error creating file");
                throw new RuntimeException(e);
            }
        }

        //safely open the writer
        try {
            journalWriter = new FileWriter(journalFile, true);
        } catch (IOException e) {
            System.out.println("Error creating file writer");
            throw new RuntimeException(e);
        }


        String option = " ";

        do {
            option = CisUtility.getInputString(MENU);
            processMenuOption(option);
        } while (!option.equalsIgnoreCase(EXIT));

        try {
            journalWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void processMenuOption(String option) {
        switch (option.toUpperCase()) {
            case "A":
                processPurchase();
                break;

            case "V":
                viewPurchase();
                break;

            case "X":
                System.out.println(" Thanks for the purchase");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    public static void processPurchase() {
        System.out.println("Processing option A");

        Perfume perfume = new Perfume();
        perfume.getInformation();
        System.out.println("Perfume added successfully!");


        // Insert new entry into journal.txt and catch possible errors
        try {
            String jsonValue = perfume.toJson();
            System.out.println(jsonValue);
            journalWriter.write(jsonValue + System.lineSeparator());
            journalWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void viewPurchase() {

        System.out.println();
        Gson gson = new Gson();
        try {
            List<String> lines = Files.readAllLines(Paths.get(PATH + FILE_NAME));
            if (lines.isEmpty()) {
                System.out.println("No assessments found");
            } else {
                System.out.println("Here are the assessments found");
                for (String current : lines) {
                    Perfume perfume = gson.fromJson(current, Perfume.class);
                    System.out.println(perfume.toString());
                    System.out.println();
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
            throw new RuntimeException(e);
        }

    }

}

package info.hccis;

import info.hccis.perfume.Perfume;
import info.hccis.util.CisUtility;
import java.util.Scanner;

/**
 * Working to create an Application for a perfume shop. Here it will prompt user with the options choose.
 *
 * @author Fardin Sahriar Al Rafat
 * @since 20250917
 */
public class Controller {

    private static CisUtility cisUtility = new CisUtility();

    public static final String EXIT = "X";

    private static final String MENU
            = "\n-------------------------\n"
            + "- Perfume Shop -\n"
            + "- A) Buy a perfume\n"
            + "- B) Show perfume total\n"
            + "- X) EXIT\n"
            + "-------------------------\n"
            + "Option-->";

    private static Perfume perfume; // it will keep record of the perfume purchase

    public static void main(String[] args) {

        //Add a loop below to continuously prompt the user for their choice
        //until they choose to exit.
        String option = " ";
        do {
            option = CisUtility.getInputString(MENU);
            processMenuOption(option);
        } while (!option.equalsIgnoreCase(EXIT));

    }

    /**
     * This method will process the menu option specified in the input
     * parameter. It will call appropriate functionality based on its value.
     *
     * @param option The menu option
     * @since 20171102
     * @author cis1201b
     *
     */
    public static void processMenuOption(String option) {
        switch (option.toUpperCase()) {
            case "A":
                perfume = new Perfume();
                perfume.getInformation();
                System.out.println("Perfume added successfully!");
                break;

            case "B":
                if(perfume != null) {
                    System.out.println(perfume.toString());
                }else {
                    System.out.println("No perfume added to the cart");
                }
                break;

            case "X":
                System.out.println(" Thanks for the purchase");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

}

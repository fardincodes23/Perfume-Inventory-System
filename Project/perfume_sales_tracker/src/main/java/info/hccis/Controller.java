package info.hccis;

import info.hccis.util.CisUtility;
import java.util.Scanner;

/**
 * A starting project which we can use for applications that need a menu driven
 * program. Note that the name of the project should be modified to reflect the
 * specific requirements.
 *
 * @author bjmaclean
 * @since 20181115
 */
public class Controller {

    private static CisUtility cisUtility = new CisUtility();

    public static final String EXIT = "X";

    private static final String MENU
            = "\n-------------------------\n"
            + "- CIS Menu\n"
            + "- A-Process A\n"
            + "- B-Process B\n"
            + "- C-Process C\n"
            + "- X-eXit\n"
            + "-------------------------\n"
            + "Option-->";

    public static void main(String[] args) {

        //Add a loop below to continuously promput the user for their choice
        //until they choose to exit.
        String option = "";

        System.out.println((char)27 + "[33mYELLOW");

        cisUtility.setIsGUI(true);

        cisUtility.display("Today is: "+cisUtility.getCurrentDate(null), "Red");
        cisUtility.display("The random number is "+cisUtility.getRandom(20), "Green");

        do {
            option = cisUtility.getInputString(MENU, "Green");
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
        //Add a switch to process the option
        switch (option.toUpperCase()) {
            case "A":
                cisUtility.display("User picked a");
                break;
            case "B":
                cisUtility.display("User picked b");
                break;
            case "C":
                cisUtility.display("User picked c");
                break;
            case "GV":
                cisUtility.display(cisUtility.getRandom());
                break;
            case "X":
                cisUtility.display("User picked x");
                break;
            default:
                cisUtility.display("Invalid entry");
        }
    }

}

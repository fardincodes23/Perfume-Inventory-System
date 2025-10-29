package ca.hccis.perfume.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Class containing utility methods that are useful for our projects.
 *
 * @author bjmaclean
 * @since 20181115
 */
public class CisUtility {

    private static Scanner input = new Scanner(System.in);

    //The isGUI will be used to determine if JOptionPane is used or console
    private static boolean isGUI = false;

    public static void setIsGUI(boolean isGUI) {
        CisUtility.isGUI = isGUI;
    }


    /*
    Methods to get input from the user
     */
    /**
     * Method which will prompt the user and return the value entered as a
     * String.
     *
     * @author BJ MacLean
     * @param prompt The user prompt
     * @return The String entered by the user
     * @since 20181121
     */
    public static String getInputString(String prompt) {

        String userOption;

        if (isGUI) {
            userOption = JOptionPane.showInputDialog(prompt);
        } else {
            System.out.println(prompt);
            userOption = input.nextLine();
        }

        return userOption;
    }

    /**
     * Method which will prompt the user and return an int value.
     *
     * @author BJ MacLean
     * @param prompt The user prompt
     * @return The number entered by the user
     * @since 20181121
     */
    public static int getInputInt(String prompt) {
        String enteredString = getInputString(prompt);
        int entered = Integer.parseInt(enteredString);
        return entered;
    }

    /**
     * Method which will prompt the user and return a double value.
     *
     * @author BJ MacLean
     * @param prompt The user prompt
     * @return The number entered by the user
     * @since 20181121
     */
    public static double getInputDouble(String prompt) {
        String enteredString = getInputString(prompt);
        double entered = Double.parseDouble(enteredString);
        return entered;
    }

    /**
     * Method to input a boolean value.The prompt will have y/n instructions
 appended to it.
     *
     * @author BJ MacLean
     * @param prompt Base prompt for the user
     * @return true/false
     * @since 20200129
     */
    public static boolean getInputBoolean(String prompt) {
        String temp = getInputString(prompt+" (y/n)");
        return temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes") || temp.equalsIgnoreCase("true")
                || temp.equalsIgnoreCase("1");
    }
    
    /**
     * Method to display a string for the user
     *
     * @param output The string that will be displayed to the user
     * @since 20181115
     * @author BJM
     */
    public static void display(String output) {
        //System.out.println(output);
        if (isGUI) {
            JOptionPane.showMessageDialog(null, output);
        } else {
            System.out.println(output);
        }
    }

    /**
     * This method will use the Math class to get a random number between 1 and
     * the max inclusive.
     *
     * @author BJ MacLean
     * @param max The upper limit for the random number (inclusive)
     * @since 20181121
     */
    public static int getRandom(int max) {

        //Math.random will give a fraction between 0 
        double theFraction = Math.random();
        int theResult = (int) (theFraction * max);
        return 1 + theResult;
    }

    /**
     *
     * @author BJ MacLean
     * @since 2020
     */
    public static String getRandom() {

        String[][] theClass;

        String name = null;
        final int NUMBER_OF_ROWS = 4;
        final int NUMBER_OF_SEATS_PER_ROW = 6;

        String section = getInputString("A or B section?");

        theClass = new String[NUMBER_OF_ROWS][NUMBER_OF_SEATS_PER_ROW];

        if (section.equalsIgnoreCase("B")) {

            theClass[0][0] = "Coffee Break";
            theClass[0][1] = "";
            theClass[0][2] = "Brody";
            theClass[0][3] = "Ryan";
            theClass[0][4] = "Khari";
            theClass[0][5] = "";
            theClass[1][0] = "Bruce";
            theClass[1][1] = "";
            theClass[1][2] = "Cameron";
            theClass[1][3] = "Cole";
            theClass[1][4] = "Neil";
            theClass[1][5] = "";
            theClass[2][0] = "Jems";
            theClass[2][1] = "";
            theClass[2][2] = "Mohammad";
            theClass[2][3] = "";
            theClass[2][4] = "Domanic";
            theClass[2][5] = "Karen";
            theClass[3][0] = "BJ";
            theClass[3][1] = "";
            theClass[3][2] = "";
            theClass[3][3] = "";
            theClass[3][4] = "";
            theClass[3][5] = "";
        } else {
            if (section.equalsIgnoreCase("a")) {

                theClass[0][0] = "";
                theClass[0][1] = "Thomas";
                theClass[0][2] = "Max";
                theClass[0][3] = "Marc";
                theClass[0][4] = "Brandon";
                theClass[0][5] = "Alex";
                theClass[1][0] = "Elizabeth";
                theClass[1][1] = "Nathan";
                theClass[1][2] = "Ahsun";
                theClass[1][3] = "Kahla";
                theClass[1][4] = "Philip";
                theClass[1][5] = "Logan";
                theClass[2][0] = "Devon";
                theClass[2][1] = "Katie";
                theClass[2][2] = "Kelsie";
                theClass[2][3] = "Kapil";
                theClass[2][4] = "Reilly";
                theClass[2][5] = "";
                theClass[3][0] = "";
                theClass[3][1] = "";
                theClass[3][2] = "";
                theClass[3][3] = "";
                theClass[3][4] = "Coffee Break";
                theClass[3][5] = "BJ";
            }

        }

        do {
            int rowRandom = (int) (Math.random() * NUMBER_OF_ROWS);
            int seatRandom = (int) (Math.random() * NUMBER_OF_SEATS_PER_ROW);
            name = theClass[rowRandom][seatRandom];
            //CisUtility.display("Person at the random seat=" + name);
        } while (name == null || name.equals(""));
        CisUtility.display("The winner is=" + name);
        return name;
    }

    /**
     * Get the formatted date
     * https://stackoverflow.com/questions/1459656/how-to-get-the-current-time-in-yyyy-mm-dd-hhmisec-millisecond-format-in-java
     *
     * @author BJ MacLean
     * @since 20190301
     */
    public static String getCurrentDate(String format) {
        //Set the default format.
        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd";
        }

        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));

    }

    /**
     * Get the formatted date adjusted based on offset
     * https://stackoverflow.com/questions/1459656/how-to-get-the-current-time-in-yyyy-mm-dd-hhmisec-millisecond-format-in-java
     * @param offsetDays Days to offset the date
     * @param format Format for date (default yyyy-MM-dd)
     * @author BJ MacLean
     * @since 20241018
     */
    public static String getCurrentDate(int offsetDays, String format) {
        //Set the default format.
        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd";
        }

        LocalDateTime theDate = LocalDateTime.now().plusDays(offsetDays);
        return theDate.format(DateTimeFormatter.ofPattern(format));

    }


    /**
     * This method will return the current time in milliseconds since a specific
     * start of time.
     *
     * https://www.tutorialspoint.com/java/lang/system_currenttimemillis.htm
     * 
     * @author BJ MacLean
     * @since 20200127
     */
    public static long getNowMillis() {
        return System.currentTimeMillis();
    }

}

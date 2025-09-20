package info.hccis.bus.pass.util;

import java.security.*;
import java.math.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * General program utilities
 *
 * @author bjmaclean
 * @since 20150918
 */
public class Utility {

    private static Scanner input = new Scanner(System.in);

    //Used to turn on/off additonal sout in application.
    public static final boolean debugging = true;

    //Get the scanner
    public static Scanner getInput() {
        return input;
    }

    public static String getCSV(String[] test) {
        String output = "";
        boolean found = false;
        for (String current : test) {
            found = true;
            output += current + ",";
        }
        output = output.substring(0, output.length());
        return output;
    }

    public static String getCSV(int[] test) {
        String output = "";
        boolean found = false;
        for (int current : test) {
            found = true;
            output += "" + current + ",";
        }
        output = output.substring(0, output.length());
        return output;
    }

    /**
     * This method will use md5 to create a hash value for the pw.
     *
     * @since 20170516
     * @author BJM
     */
    public static String getHashPassword(String inPassword) {
        try {
            return getMD5Hash(inPassword);
        } catch (Exception ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Should not see this!";
    }

    public static String getMD5Hash(String passwordIn) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(passwordIn.getBytes(), 0, passwordIn.length());
        return "" + new BigInteger(1, m.digest()).toString(16);
    }

    /**
     * This wil;l give current date/time in format provided
     *
     * @since 20170516
     * @param format
     * @return formatted now
     */
    public static String getNow(String format) {
        if (format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        String now = dateFormat.format(date);
        return now;

    }
}

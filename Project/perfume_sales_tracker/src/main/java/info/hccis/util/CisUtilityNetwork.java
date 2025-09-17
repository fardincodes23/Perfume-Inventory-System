package info.hccis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Utility class to make a network connection
 * @author BJM
 * @since 20241104
 */

public class CisUtilityNetwork {

    private static final Logger logger = LoggerFactory.getLogger(CisUtilityNetwork.class);


    /**
     * Code from:
     * https://medium.com/swlh/getting-json-data-from-a-restful-api-using-java-b327aafb3751
     * @param urlString
     * @return
     * @since 20241104
     * @author BJM
     */
    public static String connectToApi(String urlString) {
        try {
            logger.info("Attempting to connect to: ***" + urlString+"***");

            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

//                //Using the JSON simple library parse the string into a json object
//                JSONParser parse = new JSONParser();
//                JSONObject data_obj = (JSONObject) parse.parse(inline);
//
//                //Get the required object from the above created object
//                JSONObject obj = (JSONObject) data_obj.get("Global");

                //Get the required data using its key
                System.out.println(inline);
                return inline;
            }
        } catch (Exception e) {
            logger.error("Exception connecting to url: "+e.getMessage());
            return null;
        }

    }

}

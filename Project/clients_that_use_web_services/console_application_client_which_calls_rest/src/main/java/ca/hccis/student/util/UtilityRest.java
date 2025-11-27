package ca.hccis.student.util;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author bjmaclean
 * @since Nov 17, 2017
 */
public class UtilityRest {

    /**
     * This method will call the rest web service and give back the json
     *
     * @since 20171117
     * @author BJM
     */
    public static String getJsonFromRest(String urlString) {

        String content = "";
        try {

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 204) {
                System.out.println("No data found");
            } else if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;

            while ((output = br.readLine()) != null) {
                content += output;
            }

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * This method will access the service to get all of the objecrts from
     * the web application service.
     *
     * @since 20161115
     * @author BJM
     */
    public static Object addUsingRest(String urlIn, Object objectIn) {
        
        
        //**********************************
        //Create a test camper
        //**********************************

        Gson gson = new Gson();
        String temp = "";

        //************************************
        //convert the camper to a json string
        //************************************
        temp = gson.toJson(objectIn);

        //*********************************************
        // Access the rest web service
        //https://www.tutorialspoint.com/restful/restful_quick_guide.htm
        //*********************************************
        Object objectReturned = null;
        try {

            URL url = new URL(urlIn);
            //System.out.println(urlIn);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            //System.out.println("json=" + temp);
            String input = temp;

            //Write the bytes of json to the output stream for the connection.
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED
                    && conn.getResponseCode() != HttpURLConnection.HTTP_OK ) {
                System.out.println("Failed : HTTP error code : "
                        + conn.getResponseCode()
                        + conn.getResponseMessage());
            } else {

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                String content = "";
                while ((output = br.readLine()) != null) {
                    content += output;
                }
                objectReturned = gson.fromJson(content,objectIn.getClass());
                //System.out.println("Success : Added booking (" + studentReturned.toString() + ")\n");
                
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectReturned;
    }

    /**
     * This method will access the CamperService to get all of the campers from
     * the campers web application service.
     *
     * @since 20161115
     * @author BJM
     */
//    public static void updateUsingRest(String urlIn, Camper camper) {
//        //**********************************
//        //Create a test camper
//        //**********************************
//
//        Gson gson = new Gson();
//        String temp = "";
//
//        //************************************
//        //convert the camper to a json string
//        //************************************
//        temp = gson.toJson(camper);
//
//        //*********************************************
//        // Access the rest web service
//        //https://www.tutorialspoint.com/restful/restful_quick_guide.htm
//        //*********************************************
//        try {
//
//            URL url = new URL(urlIn);
//            //System.out.println(urlIn);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setRequestMethod("PUT");
//            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
//
//            //System.out.println("json=" + temp);
//            String input = temp;
//
//            //Write the bytes of json to the output stream for the connection.
//            OutputStream os = conn.getOutputStream();
//            os.write(input.getBytes());
//            os.flush();
//
//            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//                System.out.println("Failed : HTTP error code : "
//                        + conn.getResponseCode()
//                        + conn.getResponseMessage());
//            } else {
//
//                BufferedReader br = new BufferedReader(new InputStreamReader(
//                        (conn.getInputStream())));
//
//                String output;
//                String content = "";
//                while ((output = br.readLine()) != null) {
//                    content += output;
//                }
//                Camper camperReturned = gson.fromJson(content, Camper.class);
//                System.out.println("Success : Updated camper (" + camperReturned.getId() + ")\n");
//            }
//            conn.disconnect();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
    /**
     * This method will access the service to delete a row
     *
     * @since 20251117
     * @author BJM
     */
    public static void deleteUsingRest(String urlIn, int id) {
        //*********************************************
        // Access the rest web service
        //https://www.tutorialspoint.com/restful/restful_quick_guide.htm
        //*********************************************
        try {

            URL url = new URL(urlIn +"/"+ id);
            System.out.println(urlIn);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            //Write the bytes of json to the output stream for the connection.
            OutputStream os = conn.getOutputStream();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("Failed : HTTP error code : "
                        + conn.getResponseCode()
                        + conn.getResponseMessage());
            } else {

                System.out.println("Success : deleted booking (" + id + ")\n");
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

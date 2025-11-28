package ca.hccis.perfume.rest;

import ca.hccis.perfume.exception.AllAttributesNeededException;
import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import ca.hccis.perfume.repositories.PerfumeTransactionRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Service class for accessing Perfume Transactions using JAX-RS (Jersey).
 * Path: /PerfumeService/v1/transactions
 *
 * @author BJM
 * @since 20251125
 */
@Component // Must be a Spring component to enable Autowiring
@Path("/PerfumeService/v1/transactions")
public class PerfumeTransactionService {

    private final PerfumeTransactionRepository _ptr;
    private final Gson gson = new Gson();

    @Autowired
    public PerfumeTransactionService(PerfumeTransactionRepository ptr) {
        this._ptr = ptr;
    }

    /**
     * Method to get all transactions.
     * Path: GET /api/PerfumeService/v1/transactions
     *
     * @return List of transactions or HTTP 204 No Content.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        ArrayList<PerfumeTransaction> transactions = (ArrayList<PerfumeTransaction>) _ptr.findAll();

        if (transactions == null || transactions.isEmpty()) {
            int responseCode = HttpURLConnection.HTTP_NO_CONTENT; // 204
            return Response.status(responseCode).build();
        } else {
            return Response
                    .status(HttpURLConnection.HTTP_OK) // 200
                    .entity(transactions).build();
        }
    }

    /**
     * Method to get transaction by its id.
     * Path: GET /api/PerfumeService/v1/transactions/{id}
     *
     * @param id The ID of the transaction.
     * @return The transaction or HTTP 204 No Content.
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionById(@PathParam("id") int id) {
        Optional<PerfumeTransaction> theTransaction = _ptr.findById(id);
        if (!theTransaction.isPresent()) {
            return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build(); // 204
        } else {
            return Response
                    .status(HttpURLConnection.HTTP_OK) // 200
                    .entity(theTransaction.get()).build();
        }
    }

    /**
     * Method to create a new transaction.
     * Path: POST /api/PerfumeService/v1/transactions
     *
     * @param inJson JSON representation of the PerfumeTransaction.
     * @return The saved transaction with HTTP 201 CREATED or HTTP 406 Not Acceptable on validation error.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String inJson) {
        // DEBUG 1: Check what JSON the server actually received
        System.out.println("DEBUG: Received JSON: " + inJson);
        try {
            String savedJson = save(inJson);
            return Response.status(HttpURLConnection.HTTP_CREATED) // 201
                    .entity(savedJson)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
        } catch (AllAttributesNeededException aane) {
            System.out.println("AllAttributesNeededException happened while adding transaction.");
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(aane.getMessage()).build(); //400 Bad Request
        } catch (Exception e) {
            System.out.println("Exception happened while adding transaction: " + e.getMessage());
            // 400 Bad Request
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Method to update a transaction.
     * Path: PUT /api/PerfumeService/v1/transactions/{id}
     *
     * @param id             The ID to update (unused but required by signature).
     * @param transactionJson JSON representation of the PerfumeTransaction (must include ID).
     * @return The updated transaction with HTTP 201 or HTTP 400 on error.
     */
    @PUT
   // @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTransaction(String transactionJson) {

        try {
            // Re-use the save logic
            String temp = save(transactionJson);
            return Response.status(HttpURLConnection.HTTP_CREATED) // 201 Created/Updated
                    .entity(temp)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
        } catch (AllAttributesNeededException aane) {
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(aane.getMessage()).build(); // 400
        }
    }


    /**
     * Method to delete a transaction by ID.
     * Path: DELETE /api/PerfumeService/v1/transactions/{id}
     *
     * @param id The ID of the transaction to delete.
     * @return HTTP 200 OK on success, HTTP 204 No Content if ID not found, or HTTP 406 Not Acceptable on error.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {

        try {
            Optional<PerfumeTransaction> theTransactionOptionalObject = _ptr.findById(Integer.valueOf(id));
            if (!theTransactionOptionalObject.isPresent()) {
                return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build(); // 204
            } else {
                _ptr.delete(theTransactionOptionalObject.get());
            }
        } catch (Exception e) {
            // 406 Not Acceptable - per example
            return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
        return Response.status(HttpURLConnection.HTTP_OK)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build(); // 200 OK
    }

    /**
     * Method to deserialize JSON, check for required fields, calculate derived fields, and save the entity.
     *
     * @param json JSON string containing transaction data.
     * @return JSON string of the saved entity.
     */
    public String save(String json) throws AllAttributesNeededException {

        // 1. Deserialize
        PerfumeTransaction theObject = gson.fromJson(json, PerfumeTransaction.class);

        if (theObject == null) {
            // Return a better error code like 400 with a message,
            // or let the validation below catch it if you prefer 406.
            throw new AllAttributesNeededException("JSON payload was empty or invalid, object creation failed.");
        }

        // 2. Simple Validation Check for required fields
        if (theObject.getQuantity() == 0.0 ||
                theObject.getPricePerBottle() == 0.0 ||
                theObject.getCustomerName() == null ||
                theObject.getPerfumeChoice() == null ||
                theObject.getTransactionDate() == null ||
                theObject.getPhoneNumber() == null
        ) {
            throw new AllAttributesNeededException("All required fields (quantity, price, customerName, perfumeChoice etc) must be present and valid.");
        }

        // 3. Perform business logic calculations
        double quantity = theObject.getQuantity();
        double price = theObject.getPricePerBottle();

        double subTotal = quantity * price;
        final double TAX_RATE = 0.10;
        double tax = subTotal * TAX_RATE;
        double total = subTotal + tax;

        theObject.setSubTotal(subTotal);
        theObject.setTaxAmount(tax);
        theObject.setTotal(total);

        // 4. Save
        theObject = _ptr.save(theObject);

        // 5. Reserialize and return
        String temp = gson.toJson(theObject);
        return temp;
    }


}
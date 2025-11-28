package ca.hccis.perfume.rest;

import ca.hccis.perfume.bo.BusPassBO;
import ca.hccis.perfume.exception.AllAttributesNeededException;
import ca.hccis.perfume.jpa.entity.BusPass;
import ca.hccis.perfume.repositories.BusPassRepository;
import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Bus Pass Service class for accessing using REST.
 *
 * @author 2232
 * @since 20241114
 */
@Path("/BusPassService/busPasses")
public class BusPassService
{
    private final BusPassRepository _bpr;
    
    @Autowired
    public BusPassService(BusPassRepository bpr){
        this._bpr = bpr;
    }
    
    /**
     * Method to get all.
     * 
     * @author 2250
     * @since 20201116
     * @return bus passes
     */
    @GET
    @Produces("application/json")
    public Response getAll()
    {
        ArrayList<BusPass> busPasses = (ArrayList<BusPass>) _bpr.findAll();

        if (busPasses == null || busPasses.isEmpty()) {
            return Response.status(204).build();
        } else {
            return Response
                    .status(200)
                    .entity(busPasses).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){

        try {
            Optional<BusPass> busPass = _bpr.findById(Integer.valueOf(id));
            if (busPass==null) {
                return Response.status(204).build();
            }else{
                _bpr.delete(busPass.get());
            }
        }catch (Exception e) {
            return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
        return Response.status(HttpURLConnection.HTTP_OK).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
    }

    /**
     * Method to get by their id using REST.
     * 
     * @author 2250
     * @since 20220201
     * @return response
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getBusPassesById(@PathParam("id") Integer id) throws URISyntaxException
    {
        Optional<BusPass> busPass = _bpr.findById(id);
         if (!busPass.isPresent()) {
            return Response.status(204).build();
        } else {
            return Response
                    .status(200)
                    .entity(busPass).build();
        }
    }
    
    /**
     * Method to create using REST.
     *
     * @author 2250
     * @since 20201116
     * @return response
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String inJson)
    {
        try{
            System.out.println(inJson);
            String temp = save(inJson);
            return Response.status(HttpURLConnection.HTTP_OK).entity(temp).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
        }catch(AllAttributesNeededException aane){
            System.out.println("AANE Exception happened adding ticket order.");
            //https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#successful_responses
            return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(aane.getMessage()).build();
        }catch(Exception e){
            System.out.println("Exception happened adding ticket order.");
            //https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#successful_responses

            return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }
////
////    /**
////     * Method to update a customer using REST.
////     *
////     * @author PAAG
////     * @since 20201116
////     * @return response
////     */
//    @PUT
//    @Path("/{id}")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response updateTicketOrder(@javax.ws.rs.PathParam("id") int id, String ticketOrderJson) throws URISyntaxException
//    {
//
//        try{
//            String temp = save(ticketOrderJson);
//            return Response.status(201).entity(temp).header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
//        }catch(AllAttributesNeededException aane){
//            return Response.status(400).entity(aane.getMessage()).build();
//        }
//
//    }
//
    /**
     * Method to make sure all required inputs are present.
     *
     * @author 2250
     * @since 20220201
     * @return string
     */
    public String save(String json) throws AllAttributesNeededException{

        Gson gson = new Gson();
        BusPass busPass = gson.fromJson(json, BusPass.class);

        if(busPass.getId() == null){
            busPass.setId(0);
        }
        BusPassBO.calculateBusPassCost(busPass);
        busPass = _bpr.save(busPass);

        String temp = "";
        temp = gson.toJson(busPass);

        return temp;

    }
    
}

package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.GuideDto;
import dtos.RoleDto;
import dtos.TripDto;
import dtos.UserDto;
import entities.Guide;
import entities.Trip;
import entities.User;
import facades.TripFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Path("trip")
public class TripEndpoint {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private ServletContext context;

    EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
    TripFacade facade = TripFacade.getTripFacade(em.getEntityManagerFactory());

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllTrips(){
        return facade.getAllTrips();
    }

    @POST
    @Path("join")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response joinTripAsUser(String prompt) {
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        String username = json.get("username").getAsString();
        int tripId = json.get("tripid").getAsInt();
        return facade.joinTripAsUser(username, tripId);
    }


    @POST
//    @RolesAllowed("admin")
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createTrip(String prompt) {

        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        String name = json.get("name").getAsString();
        String startdate = json.get("startdate").getAsString();
        String enddate = json.get("enddate").getAsString();
        String location = json.get("location").getAsString();
        String packing_list = json.get("packing_list").getAsString();
        int guide_id = json.get("guide_id").getAsInt();
        Instant istart = Instant.parse(startdate);
        LocalDateTime start = LocalDateTime.ofInstant(istart, ZoneId.systemDefault());
        Instant iend = Instant.parse(enddate);
        LocalDateTime end = LocalDateTime.ofInstant(iend, ZoneId.systemDefault());
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        Guide guide = em.find(Guide.class, guide_id);
        if(guide == null) return Response.status(404, "Guide with given id does not exist").build();
        TripDto tripDto = new TripDto(name, start, end, location, packing_list, new GuideDto(guide));
        return facade.createTrip(tripDto);
    }

    @PUT
//    @RolesAllowed("admin")
    @Path("update")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateGuideOnTrip(String prompt) {

        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        int tripId = json.get("id").getAsInt();
        int guide_id = json.get("guide_id").getAsInt();
        return facade.updateGuideOnTrip(tripId, guide_id);
    }



    @DELETE
    @Path("remove/{tripId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response removeTrip(@PathParam("tripId") int tripId) {
        return facade.removeTrip(tripId);

    }
}

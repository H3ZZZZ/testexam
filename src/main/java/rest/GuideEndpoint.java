package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.GuideDto;
import dtos.TripDto;
import entities.Guide;
import facades.GuideFacade;
import facades.TripFacade;
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

@Path("guide")
public class GuideEndpoint {


    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
    GuideFacade facade = GuideFacade.getGuideFacade(em.getEntityManagerFactory());

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllGuides(){
        return facade.getAllGuides();
    }

    @POST
//    @RolesAllowed("admin")
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createGuide(String prompt) {
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        String name = json.get("name").getAsString();
        String gender = json.get("gender").getAsString();
        String birth_year = json.get("birth_year").getAsString();
        String profile = json.get("profile").getAsString();
        String image_url = json.get("image_url").getAsString();

        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();

        GuideDto guideDto = new GuideDto(name, gender, birth_year, profile, image_url);
       return facade.createGuide(guideDto);
    }

//    @POST
//    @Path("join")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public String joinTrip(String userId, String tripId) {
//        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
//        Trip trip = em.find(Trip.class, tripId);
//        User user = em.find(User.class, userId);
//        Set<Trip> userTrips = user.getTrips();
//        userTrips.add(trip);
//        em.getTransaction().begin();
//        em.persist(userTrips);
//        em.getTransaction().commit();
//        return "hurray";
//    }




    

//    @POST
//    @Path("remove")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public boolean removeUser(String prompt) {
//        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
//        String userName = json.get("username").getAsString();
//        try {
//            EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
//            facade.remove(userName);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
}

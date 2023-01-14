package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.OwnerDto;
import facades.OwnerFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("owner")
public class OwnerEndpoint {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
    OwnerFacade facade = OwnerFacade.getOwnerFacade(em.getEntityManagerFactory());

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllOwners(){
        return facade.getAllOwners();
    }

    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getOwnersByBoatId(@PathParam("id") int id){
        List<OwnerDto> ownerDtos = facade.getOwnersOfBoatById(id);
        return Response.ok().entity(GSON.toJson(ownerDtos)).build();
    }



//    @POST
//    @Path("create")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public String createUser(String prompt) {
//
//        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
//        String username = json.get("username").getAsString();
//        String password = json.get("password").getAsString();
//        String role = json.get("role").getAsString();
//        if (UserFacade.checkUserExists(username)){
//            return "user already exists";
//        }
//
//        try {
//            facade.create(username, password, new Role(role));
//            return "true";
//        } catch (Exception e) {
//            return "false";
//        }
//    }
//
//    @POST
//    @Path("remove")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public boolean removeUser(String prompt) {
//        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
//        String userName = json.get("user_name").getAsString();
//        try {
//            EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
//            facade.remove(userName);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
}

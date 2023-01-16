package rest;

import com.google.gson.*;
import dtos.RoleDto;
import dtos.UserDto;
import entities.User;
import facades.UserFacade;
import security.entities.Role;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("user")
public class UserEndpoint {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
    UserFacade facade = UserFacade.getUserFacade(em.getEntityManagerFactory());
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllUsers(){
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        List<UserDto> userDtos = new ArrayList<>();
        try {
            List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
            for (User user : users) {
                userDtos.add(new UserDto(user));
            }
            return Response.ok(GSON.toJson(userDtos)).build();
        } finally {
            em.close();
        }
    }

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createUser(String prompt) {

        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        String email = json.get("email").getAsString();
        int role = json.get("role").getAsInt();
        if (UserFacade.checkUserExists(username)){
            return "user already exists";
        }

        try {
            facade.create(username, password, new RoleDto(role), email);
            return "true";
        } catch (Exception e) {
            return "false";
        }
    }
    @GET
    @Path("roles")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getroles() {
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        List<RoleDto> roleDtos = new ArrayList<>();
        try {
            List<Role> roles = em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
            for (Role role : roles) {
                roleDtos.add(new RoleDto(role.getId(), role.getRoleName()));
            }
            return Response.ok(roleDtos).build();
        } finally {
            em.close();
        }
    }

    @POST
    @Path("remove")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean removeUser(String prompt) {
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        String userName = json.get("username").getAsString();
        try {
            EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
            facade.remove(userName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import security.entities.Role;
import security.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private UserFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }


    public boolean remove(String userName) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, userName);
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public static boolean checkUserExists(String username) {
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        for (User u : query.getResultList()) {
            if (u.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public User create(String name, String password, Role role) {
        EntityManager em = emf.createEntityManager();
        User user = new User(name, password);
        user.addRole(role);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    public static void main(String[] args) throws IOException, AuthenticationException {
        EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactoryForTest();


        UserFacade userFacade = UserFacade.getUserFacade(EMF);
        userFacade.remove("christian");

//        boolean test = checkUserExists("christian");
//        System.out.println(test);

    }
}

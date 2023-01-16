package facades;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.EntityManagerFactory;

import dtos.RoleDto;
import entities.User;
import security.entities.Role;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

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
    public User create(String name, String password, RoleDto roledto, String email) {

        EntityManager em = emf.createEntityManager();
        Role existingRole = em.find(Role.class, roledto.getId());
        User user = new User(name, password, email);
        if(existingRole != null ) {
            Set<Role> roles = new LinkedHashSet<>(user.getRoles());
            roles.add(existingRole);
            user.setRoles(roles);
        }
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    public static void main(String[] args) throws IOException, AuthenticationException {
        EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactoryForTest();


//        UserFacade userFacade = UserFacade.getUserFacade(EMF);
//        userFacade.create("christian")
//        userFacade.remove("christian");

//        boolean test = checkUserExists("christian");
//        System.out.println(test);

    }
}

//package UnitTest;
//
//import dtos.RoleDto;
//import entities.User;
//import facades.UserFacade;
//
//import org.junit.jupiter.api.Test;
//import security.entities.Role;
//import security.entities.Role;
//import entities.User;
//import utils.EMF_Creator;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class userTest {
//
//
//    EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactoryForTest();
//    UserFacade FACADE = UserFacade.getUserFacade(EMF);
//
//
//    @Test
//    public void testCreateUser() {
//        // Create a new role
//        Role role = new Role(1,"admin");
//
//        // Create a new user
//        User user = FACADE.create("John", "password", new RoleDto(role.getId()), "mail2");
//
//        // Assert that the user's name is correct
//        assertEquals("John", user.getUserName());
//
//    }
//
//    @Test
//    public void testRemoveUser() {
//        EntityManager em = EMF.createEntityManager();
//        // Create a new role
//        Role role = new Role(1,"admin");
//
//        // Create a new user
//        User user = FACADE.create("John", "password", new RoleDto(role.getId()), "mail2");
//
//        // Delete new user
//        FACADE.remove("John");
//
//        // Try to make a new user "john" from the created user
//        User john = em.find(User.class, user.getUserName());
//
//        // Assert that user is null if looking for John
//        assertEquals(null, john);
//    }
//
//
//
//
//}

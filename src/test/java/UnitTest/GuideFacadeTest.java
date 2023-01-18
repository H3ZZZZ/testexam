package UnitTest;

import dtos.GuideDto;
import entities.Guide;
import facades.GuideFacade;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class GuideFacadeTest {

    private static EntityManagerFactory emf;
    private static GuideFacade facade;

    public GuideFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = GuideFacade.getGuideFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test

    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query delete = em.createQuery("DELETE FROM Guide");
            delete.executeUpdate();
            em.persist(new GuideDto(1, "guideName", "male", "1996", "er guide", "url"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method
    @Test
    public void testCreateGuide() throws Exception {
        EntityManager em = emf.createEntityManager();
        facade.createGuide(new GuideDto(2, "guideName2", "male", "1996", "er guide2", "url2"));

        assertEquals(true, facade.getAllGuides(), "Expects two rows in the database");
    }


}
package UnitTest;

import dtos.GuideDto;
import dtos.RoleDto;
import dtos.TripDto;
import entities.Guide;
import entities.Trip;
import entities.User;
import facades.GuideFacade;
import facades.TripFacade;
import facades.UserFacade;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import security.entities.Role;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Response;

import java.util.List;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class otherTest {

    EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactoryForTest();

    UserFacade FACADE = UserFacade.getUserFacade(EMF);

    GuideFacade guideFacade = GuideFacade.getGuideFacade(EMF);

    TripFacade tripFacade = TripFacade.getTripFacade(EMF);

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    public void testCreateGuide() {
        EntityManager em = EMF.createEntityManager();
        // Create a Guide
        Guide guide = new Guide("NyGuide", "male", "1996", "Im the best", "url");
        GuideDto guideDto = new GuideDto(guide);

        // Create him using the method
        guideFacade.createGuide(guideDto);

        // find the guide

        // Assert that the guides birthyear is 1996
        assertEquals("1996", guideDto.getBirthYear());
    }



    @Test
    void getAllTrips() {
        guideFacade.createGuide(new GuideDto());

    }
}
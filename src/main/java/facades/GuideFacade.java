package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.GuideDto;
import dtos.TripDto;
import entities.Guide;
import entities.Trip;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;
import utils.LocalDateTimeTypeAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class GuideFacade {

    private static EntityManagerFactory emf;
    private static GuideFacade instance;

    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).setPrettyPrinting().create();

    private GuideFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static GuideFacade getGuideFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GuideFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Response getAllGuides() {
        List<GuideDto> guideDtos = new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            List<Guide> guides = em.createQuery("SELECT g FROM Guide g", Guide.class).getResultList();
            for (Guide guide : guides) {
                guideDtos.add(new GuideDto(guide));
            }

            return Response.ok(GSON.toJson(guideDtos)).build();
        } finally {
            em.close();
        }
    }



    public Response createGuide(GuideDto guideDto) {
        Guide guide = new Guide(guideDto);
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(guide);
            em.getTransaction().commit();
            return Response.ok(guideDto).build();
        } finally {
            em.close();
        }
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

package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.TripDto;
import entities.*;
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
public class TripFacade {

    private static EntityManagerFactory emf;
    private static TripFacade instance;

    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).setPrettyPrinting().create();

    private TripFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static TripFacade getTripFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TripFacade();
        }
        return instance;
    }
    public Response joinTripAsUser(String username, int tripId) {
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        User user = em.find(User.class, username);
        Trip trip = em.find(Trip.class, tripId);

        UserTrip usertrip = new UserTrip(user, trip);
        em.getTransaction().begin();
        em.persist(usertrip);
        em.getTransaction().commit();
        return Response.ok().build();
    }

    public Response getAllTrips() {
        List<TripDto> tripDtos = new ArrayList<>();
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        try {
            List<Trip> trips = em.createQuery("SELECT t FROM Trip t", Trip.class).getResultList();

            for (Trip trip : trips) {
                tripDtos.add(new TripDto(trip));
            }

            return Response.ok(GSON.toJson(tripDtos)).build();
        } finally {
            em.close();
        }
    }

    public Response createTrip(TripDto tripDto) {
        Trip trip = new Trip(tripDto);
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(trip);
            em.getTransaction().commit();
            return Response.ok(tripDto).build();
        } finally {
            em.close();
        }
    }

    public Response updateGuideOnTrip(int tripId, int guide_id) {
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        Trip trip = em.find(Trip.class, tripId);
        Guide guide = em.find(Guide.class, guide_id);
        if(guide == null) {
            return Response.status(400).build();
        }
        trip.setGuide(guide);
        try {
            em.getTransaction().begin();
            em.persist(trip);
            em.getTransaction().commit();
            return Response.ok().build();
        } finally {
            em.close();
        }
    }

    public Response removeTrip(int tripId) {
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        Trip trip = em.find(Trip.class, tripId);
        em.getTransaction().begin();
        em.remove(trip);
        em.getTransaction().commit();
        return Response.ok().build();
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

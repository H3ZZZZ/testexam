package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoatDto;
import dtos.HarbourDto;
import dtos.OwnerDto;
import entities.Boat;
import entities.Harbour;
import entities.Owner;
import security.entities.Role;
import security.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class BoatFacade {
    private static EntityManagerFactory emf;
    private static BoatFacade instance;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private BoatFacade() {

    }

    public static BoatFacade getBoatFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BoatFacade();
        }
        return instance;
    }


// Super forvirret over hvordan jeg laver en boat

//    public BoatDto create(BoatDto boatDto, Harbour harbour) {
//        EntityManager em = emf.createEntityManager();
//
//        Boat boat = new Boat(boatDto.getBrand(), boatDto.getMake(), boatDto.getName(), boatDto.getImage());
//
//
//        user.addRole(role);
//        em.getTransaction().begin();
//        em.persist(user);
//        em.getTransaction().commit();
//        return user;
//    }
}

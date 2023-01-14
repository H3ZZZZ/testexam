package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.OwnerDto;
import entities.Owner;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class OwnerFacade {
    private static EntityManagerFactory emf;
    private static OwnerFacade instance;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private OwnerFacade() {

    }

    public static OwnerFacade getOwnerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerFacade();
        }
        return instance;
    }

    public Response getAllOwners() {
        List<OwnerDto> ownerList = new ArrayList<>();
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        try {
            List<Owner> owners = em.createQuery("SELECT o FROM Owner o", Owner.class).getResultList();
            for (Owner owner : owners) {
                ownerList.add(new OwnerDto(owner));
            }
            return Response.ok(GSON.toJson(ownerList)).build();
        } finally {
            em.close();
        }
    }

    public List<OwnerDto> getOwnersOfBoatById(int boatId) {
        List<OwnerDto> ownerList = new ArrayList<>();
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();

        Query query = em.createQuery("SELECT o FROM Owner o JOIN o.boats b JOIN b.owners bo WHERE bo.id = :boatId", Owner.class);
        query.setParameter("boatId", boatId);
        List<Owner> owners = query.getResultList();
            for (Owner owner : owners) {
                ownerList.add(new OwnerDto(owner));
            }
            if (ownerList.isEmpty()) {
                return null;
            }
            return ownerList;
    }


    public static void main(String[] args) {
        OwnerFacade.getOwnerFacade(emf).getOwnersOfBoatById(1);
    }



}

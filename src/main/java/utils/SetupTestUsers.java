package utils;


import entities.Boat;
import entities.Harbour;
import entities.Owner;
import security.entities.Role;
import security.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
//      EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
    EntityManager em = emf.createEntityManager();

    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "test1");
    User admin = new User("admin", "test2");
    User both = new User("user_admin", "test3");
    Harbour harbour = new Harbour("harbour name", "harbour address", 200);
    Boat boat = new Boat("brand name", "make name", "boat name", "boat image");
    Set<Owner> owners = new LinkedHashSet<>();
    Owner owner = new Owner("owner name", "owner address", 71241337);
    Owner owner2 = new Owner("owner name2", "owner address2", 12312332);

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
      boat.setHarbour(harbour);
      owners.add(owner);
      owners.add(owner2);
      boat.setOwners(owners);
      em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);

    em.persist(harbour);
      em.persist(owner);
      em.persist(owner2);
      em.persist(boat);
      em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");

  }

}

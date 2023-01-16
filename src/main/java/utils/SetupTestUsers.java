package utils;


import dtos.RoleDto;
import entities.User;
import facades.UserFacade;
import security.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
//      EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
    EntityManager em = emf.createEntityManager();
    UserFacade userFacade = UserFacade.getUserFacade(emf);
    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    em.persist(userRole);
    em.persist(adminRole);
    em.getTransaction().commit();

    userFacade.create("user","123",new RoleDto(userRole.getId()), "usermail");
    userFacade.create("admin","123",new RoleDto(adminRole.getId()), "adminmail");
    em.getTransaction().begin();
    User bothUser = userFacade.create("both","123",new RoleDto(userRole.getId()), "bothmail");
    Set<Role> roles = new LinkedHashSet<>(bothUser.getRoles());
    roles.add(adminRole);
    bothUser.setRoles(roles);
    em.getTransaction().commit();
    System.out.println("PW: " + bothUser.getUserPass());
//    System.out.println("Testing user with OK password: " + bothUser.verifyPassword("123"));
//    System.out.println("Testing user with wrong password: " + bothUser.verifyPassword("123"));
    System.out.println("Created TEST Users");

  }

}

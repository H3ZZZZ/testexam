package entities;

import org.mindrot.jbcrypt.BCrypt;
import security.entities.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Size(max = 255)
    @Column(name = "user_name", nullable = false)
    private String id;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_pass", nullable = false)
    private String userPass;

    @Size(max = 255)
    @Column(name = "fullname")
    private String fullname;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 45)
    @Column(name = "phone", length = 45)
    private String phone;

    @Size(max = 45)
    @NotNull
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Size(max = 45)
    @Column(name = "birth_year", length = 45)
    private String birthYear;

    @Size(max = 45)
    @Column(name = "gender", length = 45)
    private String gender;

    @ManyToMany
    @JoinTable(name = "user_trips",
            joinColumns = @JoinColumn(name = "user_name"),
            inverseJoinColumns = @JoinColumn(name = "trip_id"))
    private Set<Trip> trips = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "user_has_roles",
            joinColumns = @JoinColumn(name = "FK_user_name"),
            inverseJoinColumns = @JoinColumn(name = "FK_role_id"))
    private Set<Role> roles = new LinkedHashSet<>();


    public User() {
    }

    public User(String username, String userPass, String email) {
        this.id = username;
        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
        this.email = email;
    }

    public String getUserName() {
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }
    public List<String> getRolesAsStrings() {
        List<String> roleList = new ArrayList<>();
        for (Role role : roles) {
            roleList.add(role.getRoleName());
        }
        return roleList;
    }
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public boolean verifyPassword(String pw){
        return BCrypt.checkpw(pw, userPass);
    }

}
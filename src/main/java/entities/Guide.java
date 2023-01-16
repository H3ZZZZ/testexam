package entities;

import dtos.GuideDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "guide")
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 45)
    @Column(name = "gender", length = 45)
    private String gender;

    @Size(max = 45)
    @NotNull
    @Column(name = "birth_year", nullable = false, length = 45)
    private String birthYear;

    @Size(max = 255)
    @NotNull
    @Column(name = "profile", nullable = false)
    private String profile;

    @Size(max = 255)
    @NotNull
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "guide")
    private Set<Trip> trips = new LinkedHashSet<>();

    public Guide() {
    }

    public Guide(GuideDto guideDto) {
        this.id = guideDto.getId();
        this.name = guideDto.getName();
        this.gender = guideDto.getGender();
        this.birthYear = guideDto.getBirthYear();
        this.profile = guideDto.getProfile();
        this.imageUrl = guideDto.getImageUrl();
    }

    public Guide(String name, String gender, String birthYear, String profile, String imageUrl) {
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profile = profile;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

}
package dtos;

import entities.Guide;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Guide} entity
 */
public class GuideDto implements Serializable {
    private Integer id;
    @Size(max = 255)
    @NotNull
    private String name;
    @Size(max = 45)
    private String gender;
    @Size(max = 45)
    @NotNull
    private String birthYear;
    @Size(max = 255)
    @NotNull
    private String profile;
    @Size(max = 255)
    @NotNull
    private String imageUrl;

    public GuideDto() {
    }

    public GuideDto(Integer id, String name, String gender, String birthYear, String profile, String imageUrl) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profile = profile;
        this.imageUrl = imageUrl;
    }

    public GuideDto(Guide guide) {
        this.id = guide.getId();
        this.name = guide.getName();
        this.gender = guide.getGender();
        this.birthYear = guide.getBirthYear();
        this.profile = guide.getProfile();
        this.imageUrl = guide.getImageUrl();
    }

    public GuideDto(String name, String gender, String birthYear, String profile, String imageUrl) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuideDto entity = (GuideDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.birthYear, entity.birthYear) &&
                Objects.equals(this.profile, entity.profile) &&
                Objects.equals(this.imageUrl, entity.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, birthYear, profile, imageUrl);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "gender = " + gender + ", " +
                "birthYear = " + birthYear + ", " +
                "profile = " + profile + ", " +
                "imageUrl = " + imageUrl + ")";
    }
}
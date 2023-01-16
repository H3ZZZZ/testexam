package dtos;

import entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link security.entities.User} entity
 */
public class UserDto implements Serializable {
    @Size(max = 255)
    private String id;
    @Size(max = 255)
    @NotNull
    private String userPass;
    @Size(max = 255)
    private String fullname;
    @Size(max = 255)
    private String address;
    @Size(max = 45)
    private String phone;
    @Size(max = 45)
    @NotNull
    private String email;
    @Size(max = 45)
    private String birthYear;
    @Size(max = 45)
    private String gender;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getUserName();
        this.userPass = user.getUserPass();
        this.fullname = user.getFullname();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.birthYear = user.getBirthYear();
        this.gender = user.getGender();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.userPass, entity.userPass) &&
                Objects.equals(this.fullname, entity.fullname) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.phone, entity.phone) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.birthYear, entity.birthYear) &&
                Objects.equals(this.gender, entity.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userPass, fullname, address, phone, email, birthYear, gender);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "userPass = " + userPass + ", " +
                "fullname = " + fullname + ", " +
                "address = " + address + ", " +
                "phone = " + phone + ", " +
                "email = " + email + ", " +
                "birthYear = " + birthYear + ", " +
                "gender = " + gender + ")";
    }
}
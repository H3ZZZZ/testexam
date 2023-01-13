package dtos;

import security.entities.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link security.entities.User} entity
 */
public class UserDto implements Serializable {
    @NotNull
    private int userId;
    @NotNull
    private String userName;
    @NotNull
    private String userPass;

    public UserDto() {
    }

    public UserDto(User user) {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.userName, entity.userName) &&
                Objects.equals(this.userPass, entity.userPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPass);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "userId = " + userId + ", " +
                "userName = " + userName + ", " +
                "userPass = " + userPass + ")";
    }
}
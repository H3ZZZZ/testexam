package dtos;

import entities.UserHasRoleId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link UserHasRoleId} entity
 */
public class UserHasRoleIdDto implements Serializable {
    @NotNull
    private Integer fkRoleId;
    @Size(max = 255)
    @NotNull
    private String fkUserName;

    public UserHasRoleIdDto() {
    }

    public UserHasRoleIdDto(Integer fkRoleId, String fkUserName) {
        this.fkRoleId = fkRoleId;
        this.fkUserName = fkUserName;
    }

    public Integer getFkRoleId() {
        return fkRoleId;
    }

    public void setFkRoleId(Integer fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    public String getFkUserName() {
        return fkUserName;
    }

    public void setFkUserName(String fkUserName) {
        this.fkUserName = fkUserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasRoleIdDto entity = (UserHasRoleIdDto) o;
        return Objects.equals(this.fkRoleId, entity.fkRoleId) &&
                Objects.equals(this.fkUserName, entity.fkUserName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fkRoleId, fkUserName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "fkRoleId = " + fkRoleId + ", " +
                "fkUserName = " + fkUserName + ")";
    }
}
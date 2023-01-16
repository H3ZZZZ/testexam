package dtos;

import entities.UserHasRole;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link UserHasRole} entity
 */
public class UserHasRoleDto implements Serializable {
    private UserHasRoleIdDto id;

    public UserHasRoleDto() {
    }

    public UserHasRoleDto(UserHasRoleIdDto id) {
        this.id = id;
    }

    public UserHasRoleIdDto getId() {
        return id;
    }

    public void setId(UserHasRoleIdDto id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasRoleDto entity = (UserHasRoleDto) o;
        return Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}
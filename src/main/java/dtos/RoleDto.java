package dtos;

import security.entities.Role;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link security.entities.Role} entity
 */
public class RoleDto implements Serializable {
    @NotNull
    private int roleId;
    @NotNull
    private String roleName;

    public RoleDto() {
    }

    public RoleDto(Role role) {
        this.roleId = role.getRoleId();
        this.roleName = role.getRoleName();
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto entity = (RoleDto) o;
        return Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.roleName, entity.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "roleId = " + roleId + ", " +
                "roleName = " + roleName + ")";
    }
}
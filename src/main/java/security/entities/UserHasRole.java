package security.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_has_roles")
public class UserHasRole {
    @EmbeddedId
    private UserHasRoleId id;

    @MapsId("fkRoleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FK_role_id", nullable = false)
    private Role fkRole;

    @MapsId("fkUserName")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FK_user_name", nullable = false)
    private User fkUserName;

    public UserHasRoleId getId() {
        return id;
    }

    public void setId(UserHasRoleId id) {
        this.id = id;
    }

    public Role getFkRole() {
        return fkRole;
    }

    public void setFkRole(Role fkRole) {
        this.fkRole = fkRole;
    }

    public User getFkUserName() {
        return fkUserName;
    }

    public void setFkUserName(User fkUserName) {
        this.fkUserName = fkUserName;
    }

}
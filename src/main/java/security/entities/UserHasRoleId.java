package security.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserHasRoleId implements Serializable {
    private static final long serialVersionUID = 465049201001405538L;
    @NotNull
    @Column(name = "FK_role_id", nullable = false)
    private Integer fkRoleId;

    @Size(max = 255)
    @NotNull
    @Column(name = "FK_user_name", nullable = false)
    private String fkUserName;

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
        UserHasRoleId entity = (UserHasRoleId) o;
        return Objects.equals(this.fkRoleId, entity.fkRoleId) &&
                Objects.equals(this.fkUserName, entity.fkUserName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fkRoleId, fkUserName);
    }

}
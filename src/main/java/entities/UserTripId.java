package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserTripId implements Serializable {
    private static final long serialVersionUID = -1137231062203259245L;
    @NotNull
    @Column(name = "trip_id", nullable = false)
    private Integer tripId;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_name", nullable = false)
    private String userName;

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserTripId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTripId entity = (UserTripId) o;
        return Objects.equals(this.tripId, entity.tripId) &&
                Objects.equals(this.userName, entity.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, userName);
    }

}
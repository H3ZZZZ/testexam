package entities;

import javax.persistence.*;

@Entity
@Table(name = "user_trips", indexes = {
        @Index(name = "fk_trip_has_user_user1_idx", columnList = "user_name"),
        @Index(name = "fk_trip_has_user_trip_idx", columnList = "trip_id")
})
public class UserTrip {
    @EmbeddedId
    private UserTripId id;

    @MapsId("tripId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @MapsId("userName")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_name", nullable = false)
    private User userName;

    public UserTrip() {
    }

    public UserTrip(User username, Trip trip) {
        this.userName = username;
        this.trip = trip;
    }

    public UserTripId getId() {
        return id;
    }

    public void setId(UserTripId id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

}
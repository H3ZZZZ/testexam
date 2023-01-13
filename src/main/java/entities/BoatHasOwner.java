package entities;

import javax.persistence.*;

@Entity
@Table(name = "Boat_has_Owner")
public class BoatHasOwner {
    @EmbeddedId
    private BoatHasOwnerId id;

    @MapsId("boatId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Boat_id", nullable = false)
    private Boat boat;

    @MapsId("ownerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Owner_id", nullable = false)
    private Owner owner;

    public BoatHasOwner() {
    }

    public BoatHasOwnerId getId() {
        return id;
    }

    public void setId(BoatHasOwnerId id) {
        this.id = id;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}
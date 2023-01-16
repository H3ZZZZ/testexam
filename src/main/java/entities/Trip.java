package entities;

import dtos.TripDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "trip", indexes = {
        @Index(name = "fk_trip_guide1_idx", columnList = "guide_id")
})
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @NotNull
    @Column(name = "startdate", nullable = false)
    private LocalDateTime startdate;

    @NotNull
    @Column(name = "enddate", nullable = false)
    private LocalDateTime enddate;

    @Size(max = 45)
    @NotNull
    @Column(name = "location", nullable = false, length = 45)
    private String location;

    @Size(max = 255)
    @NotNull
    @Column(name = "packing_list", nullable = false)
    private String packingList;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guide_id", nullable = false)
    private Guide guide;

    @ManyToMany
    @JoinTable(name = "user_trips",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "user_name"))
    private Set<User> users = new LinkedHashSet<>();

    public Trip() {
    }

    public Trip(TripDto tripDto) {
        this.name = tripDto.getName();
        this.startdate = tripDto.getStartdate();
        this.enddate = tripDto.getEnddate();
        this.location = tripDto.getLocation();
        this.packingList = tripDto.getPackingList();
        this.guide = new Guide(tripDto.getGuide());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }

    public LocalDateTime getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDateTime enddate) {
        this.enddate = enddate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
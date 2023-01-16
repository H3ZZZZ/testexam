package dtos;

import entities.Trip;
import entities.UserTrip;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

/**
 * A DTO for the {@link Trip} entity
 */
public class TripDto implements Serializable {
    private Integer id;
    @Size(max = 45)
    @NotNull
    private String name;
    @NotNull
    private LocalDateTime startdate;
    @NotNull
    private LocalDateTime enddate;
    @Size(max = 45)
    @NotNull
    private String location;
    @Size(max = 255)
    @NotNull
    private String packingList;
    @NotNull
    private GuideDto guide;
    private Set<UserDto> users = new LinkedHashSet<>();

    public TripDto() {
    }

    public TripDto(Integer id, String name, LocalDateTime startdate, LocalDateTime enddate, String location, String packingList, GuideDto guide, Set<UserDto> users) {
        this.id = id;
        this.name = name;
        this.startdate = startdate;
        this.enddate = enddate;
        this.location = location;
        this.packingList = packingList;
        this.guide = guide;
        this.users = users;
    }

    public TripDto(Trip trip) {
        this.id = trip.getId();
        this.name = trip.getName();
        this.startdate = trip.getStartdate();
        this.enddate = trip.getEnddate();
        this.location = trip.getLocation();
        this.packingList = trip.getPackingList();
        this.guide = new GuideDto(trip.getGuide());
    }

    public TripDto(String name, LocalDateTime startdate, LocalDateTime enddate, String location, String packing_list, GuideDto guidedto) {
        this.name = name;
        this.startdate = startdate;
        this.enddate = enddate;
        this.location = location;
        this.packingList = packing_list;
        this.guide = guidedto;
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

    public GuideDto getGuide() {
        return guide;
    }

    public void setGuide(GuideDto guide) {
        this.guide = guide;
    }

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripDto entity = (TripDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.startdate, entity.startdate) &&
                Objects.equals(this.enddate, entity.enddate) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.packingList, entity.packingList) &&
                Objects.equals(this.guide, entity.guide) &&
                Objects.equals(this.users, entity.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startdate, enddate, location, packingList, guide, users);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "startdate = " + startdate + ", " +
                "enddate = " + enddate + ", " +
                "location = " + location + ", " +
                "packingList = " + packingList + ", " +
                "guide = " + guide + ", " +
                "users = " + users + ")";
    }
}
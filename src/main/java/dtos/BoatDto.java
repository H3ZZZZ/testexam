package dtos;

import entities.Boat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Boat} entity
 */
public class BoatDto implements Serializable {
    private Integer id;
    @Size(max = 45)
    @NotNull
    private String brand;
    @Size(max = 45)
    @NotNull
    private String make;
    @Size(max = 45)
    @NotNull
    private String name;
    @Size(max = 45)
    @NotNull
    private String image;

    public BoatDto() {
    }

    public BoatDto(Boat boat) {
        this.id = boat.getId();
        this.brand = boat.getBrand();
        this.make = boat.getMake();
        this.name = boat.getName();
        this.image = boat.getImage();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoatDto entity = (BoatDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.brand, entity.brand) &&
                Objects.equals(this.make, entity.make) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.image, entity.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, make, name, image);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "brand = " + brand + ", " +
                "make = " + make + ", " +
                "name = " + name + ", " +
                "image = " + image + ")";
    }
}
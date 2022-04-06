package web.model;

import java.util.List;

public class CarModel {

    private String color;
    private String carBrand;
    private int id;

    public CarModel(int id, String color, String carBrand) {
        this.color = color;
        this.carBrand = carBrand;
        this.id = id;
    }

    public CarModel() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car {" +
                "color='" + color + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", id=" + id + '}';
    }
}

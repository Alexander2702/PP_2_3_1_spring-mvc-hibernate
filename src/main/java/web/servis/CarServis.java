package web.servis;

import web.model.CarModel;

import java.util.List;
import java.util.stream.Collectors;

public class CarServis {

    public static int count;

    static List<CarModel> carsList = List.of(new CarModel(++count,"black", "Toyota"),
            new CarModel(++count, "white", "BMW"),
            new CarModel(++count, "red", "Mersedes"),
            new CarModel(++count, "pink", "Matiz"),
            new CarModel(++count, "blue", "ZIL-130"));


    public static List <CarModel> getCars(Integer id) {
        if (id == null) return carsList;
        return carsList.stream().limit(id).collect(Collectors.toList());
    }
}

package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.servis.CarServis;

@Controller
@RequestMapping("/cars")
public class CarsController {

    @GetMapping
    public String showCar(@RequestParam(value = "count", required = false) Integer id , ModelMap model) {
        model.addAttribute("carsList", CarServis.getCars(id));
        return "cars";
    }
}

package ru.stud.homer.springpreproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.stud.homer.springpreproject.services.CarService;

@Controller
public class CarsController {
    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public String cars(@RequestParam(value = "count", required = false) Integer count,
                       Model model) {
        model.addAttribute("cars", carService.findCars(count));
        return "cars";
    }
}

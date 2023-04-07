package ru.stud.homer.springpreproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.stud.homer.springpreproject.models.Car;
import ru.stud.homer.springpreproject.repositories.CarRepository;

import java.util.List;

@Controller
public class CarsController {
    @Autowired
    private CarRepository carRepository;

    @Value("${maxCar}")
    private Integer maxCar;

    @GetMapping("/cars")
    public String cars(@RequestParam(value = "count", required = false) Integer count,
                       Model model) {
        if (count == null || count >= maxCar) {
            model.addAttribute("cars", carRepository.findAll());
        } else {
            List<Car> cars = carRepository.findAll();
            model.addAttribute("cars", cars.subList(0, count));
        }

        return "cars";
    }
}

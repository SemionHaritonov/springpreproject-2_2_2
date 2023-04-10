package ru.stud.homer.springpreproject.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.stud.homer.springpreproject.services.CarService;
import ru.stud.homer.springpreproject.util.CarBadFilterException;
import ru.stud.homer.springpreproject.util.CarErrorResponse;

@Controller
@ResponseStatus
public class CarsController {
    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public String cars(@RequestParam(value = "count", required = false) Integer count,
                       @RequestParam(value = "filter", required = false) String filter,
                       Model model) {
        //model.addAttribute("cars", carService.findCars(count));
        model.addAttribute("cars", carService.findCars(count, filter));
        return "cars";
    }

    @ExceptionHandler
    private ResponseEntity<CarErrorResponse> handleException(CarBadFilterException e) {
        CarErrorResponse response = new CarErrorResponse("Bad filter");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

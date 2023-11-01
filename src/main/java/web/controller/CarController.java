package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.List;
import java.util.Optional;

@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String printAllCar(@RequestParam(required = false, name = "count") Optional<Integer> count, ModelMap model) {
        List<Car> cars = carService.getCars(count.orElse(5));
        model.addAttribute("cars", cars);
        return "cars";
    }
}

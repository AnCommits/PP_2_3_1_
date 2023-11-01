package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String printAllCar(@RequestParam(required = false, name = "count") Integer count, ModelMap model) {
        List<Car> cars = count == null ? carService.getAllCars() : carService.getCars(count);
        model.addAttribute("cars", cars);
        return "cars";
    }

//    @GetMapping("/cars/{amount}")
//    public String printCars(@PathVariable("amount") int count, ModelMap model) {
//        List<Car> cars = carService.getCars(count);
//        model.addAttribute("cars", cars);
//        return "cars";
//    }
}

package br.com.carstore.controller;

import br.com.carstore.dto.CarDTO;
import br.com.carstore.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexUserController {

    private final CarService carService;

    public IndexUserController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String indexUser(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "indexUser";
    }

    @GetMapping("/cars/{id}")
    public String getCarById(@PathVariable String id, Model model) {
        CarDTO car = carService.findById(id);
        model.addAttribute("car", car);
        return "carDetails";
    }

}

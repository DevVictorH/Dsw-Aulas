package br.com.carstore.controller;

import br.com.carstore.dto.CarDTO;
import br.com.carstore.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexAdminController {

    private CarService carService;

    public IndexAdminController(CarService carService){
        this.carService = carService;
    }

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("carDTO", new CarDTO());
        return "index";
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        List<CarDTO> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "dashboard";
    }

    @PostMapping("/cars/delete")
    public String deletarCarro(@RequestParam("id") String id){
        carService.deleteById(id);
        return "redirect:/cars";
    }

    @GetMapping("/cars/edit")
    public String editCarForm(@RequestParam("id") String id, Model model) {
        // ID será preenchido, indicando EDIÇÃO
        CarDTO carDTO = carService.findById(id);
        model.addAttribute("carDTO", carDTO);

        // Retorna a página 'index'
        return "index";
    }

    @PostMapping("/cars")
    public String saveOrUpdateCar(CarDTO carDTO, BindingResult result) {
        // A Lógica do Service decide se salva ou atualiza com base no ID
        if (carDTO.getId() != null && !carDTO.getId().isEmpty()) {
            carService.update(carDTO.getId(), carDTO);
        } else {
            carService.save(carDTO);
        }
        return "redirect:/cars";
    }

}

package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Gra_m on 2022 03 18
 */

@Controller
public class VetController {
    private final VetService VET_SERVICE;

    public VetController(VetService vet_service) {
        VET_SERVICE = vet_service;
    }

    @RequestMapping({"", "/","/vets", "/index", "/index.html", "/vets.html" })
    public String listVets(Model model) {
        model.addAttribute("vets", VET_SERVICE.findAll());
       return "vets/index";
    }
}

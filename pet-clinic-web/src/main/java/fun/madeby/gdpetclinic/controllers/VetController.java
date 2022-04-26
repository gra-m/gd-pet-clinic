package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.services.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Gra_m on 2022 03 18
 */

@Slf4j
@Controller
public class VetController {
    private final VetService VET_SERVICE;

    public VetController(VetService vet_service) {
        VET_SERVICE = vet_service;
    }

    @GetMapping( "/vets" )
    public String listVets(Model model) {
        log.debug("/vets");
        model.addAttribute("vets", VET_SERVICE.findAll());
       return "vets/list";
    }
}

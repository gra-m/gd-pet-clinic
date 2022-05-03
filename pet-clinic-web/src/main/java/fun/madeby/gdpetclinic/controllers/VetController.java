package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.model.Vet;
import fun.madeby.gdpetclinic.services.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * Created by Gra_m on 2022 03 18
 */

@Slf4j
@Controller
public class VetController {
    private final VetService vetService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    public VetController(VetService vet_service) {
        vetService = vet_service;
    }

    @GetMapping( "/vets" )
    public String listVets(Model model) {
        log.debug("/vets");
        model.addAttribute("vets", vetService.findAll());
       return "vets/list";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson() {
        return vetService.findAll();
    }
}

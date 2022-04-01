package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Gra_m on 2022 03 18
 */

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService OWNER_SERVICE;

    public OwnerController(OwnerService owner_service) {
        OWNER_SERVICE = owner_service;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String ownerList(Model model) {
        model.addAttribute("owners", OWNER_SERVICE.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners() {
        return "not-implemented";
    }
}

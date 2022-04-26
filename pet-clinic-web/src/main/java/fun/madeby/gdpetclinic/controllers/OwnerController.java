package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Gra_m on 2022 03 18
 */

@Slf4j
@Controller
public class OwnerController {
    private final OwnerService OWNER_SERVICE;

    public OwnerController(OwnerService owner_service) {
        OWNER_SERVICE = owner_service;
    }

    @GetMapping("/owners")
    public String ownerList(Model model) {
        log.debug("/owners");
        model.addAttribute("owners", OWNER_SERVICE.findAll());
        return "owners/list";
    }

    @GetMapping("/owners/find")
    public String findOwners() {
        log.debug("/owners/find");
        return "not-implemented";
    }
}

package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Gra_m on 2022 03 18
 */

@Slf4j
@Controller
public class OwnerController {
    private final OwnerService OWNER_SERVICE;
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    public OwnerController(OwnerService owner_service) {
        OWNER_SERVICE = owner_service;
    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView getOwner(@PathVariable("ownerId") Long ownerId) {
      log.debug("/owners/{ownerId}");

        ModelAndView modelAndView = new ModelAndView("owners/owner-details");
        modelAndView.addObject(OWNER_SERVICE.findById(ownerId));

        return modelAndView;
    }

    @GetMapping("/owners")
    public String ownerList(Model model) {
        log.debug("/owners");
        model.addAttribute("owners", OWNER_SERVICE.findAll());
        return "owners/basic-owner-list";
    }

    @GetMapping("/owners/find")
    public String findOwners() {
        log.debug("/owners/find");
        return "not-implemented";
    }
}

package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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


    @GetMapping("/owners/find")
    public String findOwners(Model model) {
        log.debug("/owners/find");
        model.addAttribute("owner", Owner.builder().build());
        return "owners/find-owners";
    }

    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if (owner.getLastName() == null) {
            log.debug("processFindForm 01 LastName was left blank");
            owner.setLastName("");
        }

        List<Owner> results = OWNER_SERVICE.findAllByLastName(owner.getLastName());

        if (results.isEmpty()) {
            log.debug("processFindForm 01b returning all owners");
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/find-owners";
        } else if (results.size() == 1) {
            log.debug("processFindForm 03 Found exactly 1 owner redirecting to details");
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            log.debug("processFindForm 04 Found Multiple Owners matching that last name");
            model.addAttribute("selections", results);
            return "owners/owner-list";
        }
    }
}

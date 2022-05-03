package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.model.PetType;
import fun.madeby.gdpetclinic.model.Visit;
import fun.madeby.gdpetclinic.services.OwnerService;
import fun.madeby.gdpetclinic.services.PetService;
import fun.madeby.gdpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


/**
 * Created by Gra_m on 2022 04 27
 */
@Controller
@RequestMapping("/owners/{ownerId}")
public class VisitController {

    private static final String VIEWS_VISITS_CREATE_OR_UPDATE_FORM = "pets/visit-form";
    private static final String VIEWS_DISPLAY_OWNER_WITH_NEW_OR_UPDATED_VISIT = "redirect:/owners/";

    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }


    @ModelAttribute("pet")
    public Pet findPet(@PathVariable Long petId) {
        return petService.findById(petId);
    }

    @InitBinder
    public void initPetBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    //region Add NEW visit to form, process form when POSTED

    @GetMapping("/pets/{petId}/visits/new")
    public String initCreateVisitForm(Model model, Pet pet) {
        Visit visit = Visit.builder().build();
        pet.getVisits().add(visit);
        visit.setPet(pet);

        model.addAttribute("visit", visit);

        return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("pets/{petId}/visits/new")
    public String processCreateVisitForm(Pet pet, Visit visit,  BindingResult result, Model model){


        pet.getVisits().add(visit);

        if(result.hasErrors()) {
            model.addAttribute("visit", visit);
            return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
        }
        else {
            visit.setPet(pet);
            visitService.save(visit);
            return VIEWS_DISPLAY_OWNER_WITH_NEW_OR_UPDATED_VISIT + pet.getOwner().getId();
        }
    }


    //endregion

}

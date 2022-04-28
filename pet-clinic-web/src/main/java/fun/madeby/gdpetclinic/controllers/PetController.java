package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.model.PetType;
import fun.madeby.gdpetclinic.services.OwnerService;
import fun.madeby.gdpetclinic.services.PetService;
import fun.madeby.gdpetclinic.services.PetTypeService;
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
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/pet-form";
    private static final String VIEWS_DISPLAY_OWNER_WITH_NEW_PET = "redirect:/owners/";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    //region Add NEW pet to form, process form when POSTED

    @GetMapping("/pets/new")
    public String initCreatePetForm(Model model, Owner owner) {
        Pet pet = Pet.builder().build();
        System.out.println("OWNER   "  + owner);
        owner.getPets().add(pet);
        pet.setOwner(owner);

        model.addAttribute("pet", pet);

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreatePetForm(Owner owner, Pet pet,  BindingResult result, Model model){


        owner.getPets().add(pet);

        if(result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }
        else {
            petService.save(pet);
            return VIEWS_DISPLAY_OWNER_WITH_NEW_PET + owner.getId();
        }
    }


    //endregion


    //region UPDATE pet to form, process form when POSTED

    @GetMapping("/pets/{petId}/update")
    public String initUpdatePetForm() {

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/update")
    public String processUpdatePetForm(){
        return VIEWS_DISPLAY_OWNER_WITH_NEW_PET;
    }

    //endregion
}

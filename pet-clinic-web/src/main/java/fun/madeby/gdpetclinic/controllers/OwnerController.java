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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by Gra_m on 2022 03 18
 */

@Slf4j
@Controller
public class OwnerController {
    private final OwnerService OWNER_SERVICE;
    private final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/owner-form";
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    public OwnerController(OwnerService owner_service) {
        OWNER_SERVICE = owner_service;
    }

    // region Owner Create/Update

    /**
     *
     * @param model
     * @return
     */
   @GetMapping("/owners/new")
   public String initOwnerCreationForm(Map<String, Object> model) {
        Owner owner = new Owner();
        model.put("owner", owner);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;

   }

    /**
     * @Valid Owner owner not recognised from original code
     * @param owner
     * @param result
     * @return
     */
   @PostMapping("/owners/new")
   public String processOwnerCreationForm(Owner owner, BindingResult result) {
        if (result.hasErrors())
            return  VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        else {
            Owner savedOwner = OWNER_SERVICE.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
   }

   @GetMapping("/owners/{ownerId}/edit")
   public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {

       model.addAttribute("owner", OWNER_SERVICE.findById(ownerId));

       return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;

   }

    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(Owner owner, BindingResult result, @PathVariable("ownerId") Long ownerId) {
       if(result.hasErrors())
           return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
       else {
           owner.setId(ownerId); // if hidden should never be able to be null?
           Owner savedOwner = OWNER_SERVICE.save(owner);
           return "redirect:/owners/" + savedOwner.getId();
       }

    }

   //endregion


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

    /** Process Find Form
     * Three pathways available to each form submission
     *
     * @param owner  submitted object
     * @param result injected, handles not found error
     * @param model
     * @return 01 error, try again, 02 single owner displayed, 03 matching selection displayed
     */
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
            log.debug("processFindForm 02 Found exactly 1 owner redirecting to details");
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            log.debug("processFindForm 03 Found Multiple Owners matching that last name");
            model.addAttribute("selections", results);
            return "owners/owner-list";
        }
    }
}

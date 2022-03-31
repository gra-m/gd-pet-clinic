package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.services.OwnerService;
import fun.madeby.gdpetclinic.services.PetService;
import fun.madeby.gdpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;
import java.util.Set;


/**
 * Created by Gra_m on 2022 03 17
 *
 */

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService{
    private final PetTypeService PET_TYPE_SERVICE;
    private final PetService PET_SERVICE;

    public OwnerServiceMap(PetTypeService pet_type_service, PetService pet_service) {
        PET_TYPE_SERVICE = pet_type_service;
        PET_SERVICE = pet_service;
    }


    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner obj) {
       // todo refactor this with helper methods
        // We want to emulate what we'll do in Hibernate, so if an owner has pets,
        // the PetType cannot be null and they must have or be given an id through saving. We are ensuring everything
        // has an id and they are all in sync.

       if(obj != null) {
           if (obj.getPets() != null) {
               obj.getPets().forEach(pet -> {
                   if (pet.getPetType() != null) { //check PetType not null
                      if (pet.getPetType().getId() == null) { // if PetType not persisted persist it now
                          pet.setPetType(PET_TYPE_SERVICE.save(pet.getPetType()));
                      }
                   }else {
                       throw new RuntimeException("Pet type is required");
                   }
                   // explicit check
                   if (pet.getId() == null) {
                       Pet savedPet = PET_SERVICE.save(pet);
                       pet.setId(savedPet.getId());
                   }
               });
           }
           return super.save(obj);
       } else {
           return null;
       }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}

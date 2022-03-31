package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.model.PetType;
import fun.madeby.gdpetclinic.services.OwnerService;
import fun.madeby.gdpetclinic.services.PetService;
import fun.madeby.gdpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        if (obj == null)
            return null;

        HashSet<Pet> pets = (HashSet<Pet>) obj.getPets();
        if (pets.isEmpty())
            return super.save(obj);

        boolean PetsAndPetTypesHaveIds = checkPetsAndTypes(pets);
        if (PetsAndPetTypesHaveIds)
            return super.save(obj);
        else
            return null;
    }

    /**
     * Aim: To emulate Hibernate by ensuring all object IDs are in sync.
     * PetType must not be null and must have an id when an owner is saved. Pets must also all be saved with ids.
     * @param pets
     * @return boolean
     */
    private boolean checkPetsAndTypes(HashSet<Pet> pets) {
        if(pets.isEmpty())
            return false;
        pets.forEach(pet -> {
            PetType petType = pet.getPetType();
            Long petTypeId = petType.getId();
            Long petId = pet.getId();
            if (petType == null)
                throw new RuntimeException("Pet type is required");
            if (petTypeId == null)
                pet.setPetType(PET_TYPE_SERVICE.save(petType));
            if (petId == null) {
                Pet savedPet = PET_SERVICE.save(pet);
                pet.setId(savedPet.getId());
            }
        });
        return true;
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

package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.model.Visit;
import fun.madeby.gdpetclinic.services.PetService;
import fun.madeby.gdpetclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Gra_m on 2022 04 08
 */
@Service
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {
    private final PetService PET_SERVICE;

    public VisitServiceMap(PetService pet_service) {
        PET_SERVICE = pet_service;
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit object) {
        if(object == null)
            return null;

        boolean visitIsValid = checkPet(object.getPet());
        if(visitIsValid)
            return super.save(object);
        else
            throw new RuntimeException("Invalid Visit, No pet||no Owner/OwnerId");
    }

    private boolean checkPet(Pet pet) {
        //Corrective for pet without Id
        if(pet == null || pet.getOwner() == null || pet.getOwner().getId() == null)
            return false;
        if (pet.getId() == null) {
            Pet savedPet = PET_SERVICE.save(pet);
            pet.setId(savedPet.getId());
        }
        return true;
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}

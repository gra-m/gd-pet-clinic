package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.services.CrudService;

import java.util.Set;

/**
 * Created by Gra_m on 2022 03 17
 */

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}

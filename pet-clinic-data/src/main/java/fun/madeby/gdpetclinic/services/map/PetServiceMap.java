package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * Created by Gra_m on 2022 03 17
 *
 */

@Service
@Profile({"default", "map"})
public class PetServiceMap extends AbstractMapService<Pet> implements PetService {

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
        return super.save(object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}

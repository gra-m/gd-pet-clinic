package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.services.OwnerService;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * Created by Gra_m on 2022 03 17
 *
 */

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService{
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
    public Owner save(Owner object) {
        return super.save(object);
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

package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Vet;
import fun.madeby.gdpetclinic.services.VetService;

import java.util.Set;

/**
 * Created by Gra_m on 2022 03 17
 */

public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet findByLastName(String lastName) {
        return null;
    }
}

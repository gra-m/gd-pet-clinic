package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.repositories.PetRepository;
import fun.madeby.gdpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Gra_m on 2022 04 08
 */

@Service
@Profile("jpaService")
public class PetSDJpaService implements PetService {
    private final PetRepository PET_REPOSITORY;

    public PetSDJpaService(PetRepository petRepository) {
        this.PET_REPOSITORY = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        return new HashSet<>((Collection<? extends Pet>) PET_REPOSITORY.findAll());

    }

    @Override
    public Pet findById(Long aLong) {
        return PET_REPOSITORY.findById(aLong).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Pet save(Pet object) {
        return PET_REPOSITORY.save(object);
    }

    @Override
    public void delete(Pet object) {
        PET_REPOSITORY.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        PET_REPOSITORY.deleteById(aLong);
    }
}

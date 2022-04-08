package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.PetType;
import fun.madeby.gdpetclinic.repositories.PetTypeRepository;
import fun.madeby.gdpetclinic.services.PetTypeService;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Gra_m on 2022 04 08
 */

public class PetTypeSDJpaService implements PetTypeService {
    private final PetTypeRepository PET_TYPE_REPOSITORY;

    public PetTypeSDJpaService(PetTypeRepository pet_type_repository) {
        PET_TYPE_REPOSITORY = pet_type_repository;
    }

    @Override
    public Set<PetType> findAll() {
        //return new HashSet<>((Collection<? extends PetType>) PET_TYPE_REPOSITORY.findAll());
        Set<PetType> setFromIterable = new HashSet<>();
        PET_TYPE_REPOSITORY.findAll().forEach(setFromIterable::add);
        return setFromIterable;
    }

    @Override
    public PetType findById(Long aLong) {
        return PET_TYPE_REPOSITORY.findById(aLong).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public PetType save(PetType object) {
        return PET_TYPE_REPOSITORY.save(object);
    }

    @Override
    public void delete(PetType object) {
        PET_TYPE_REPOSITORY.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        PET_TYPE_REPOSITORY.deleteById(aLong);
    }
}

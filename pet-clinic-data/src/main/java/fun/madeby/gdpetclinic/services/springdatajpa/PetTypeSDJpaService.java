package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.PetType;
import fun.madeby.gdpetclinic.repositories.PetTypeRepository;
import fun.madeby.gdpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Gra_m on 2022 04 08
 */

@Service
@Profile("jpaService")
public class PetTypeSDJpaService implements PetTypeService {
    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(PetTypeRepository pet_type_repository) {
        petTypeRepository = pet_type_repository;
    }

    @Override
    public Set<PetType> findAll() {
        //return new HashSet<>((Collection<? extends PetType>) PET_TYPE_REPOSITORY.findAll());
        Set<PetType> setFromIterable = new HashSet<>();
        petTypeRepository.findAll().forEach(setFromIterable::add);
        return setFromIterable;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.deleteById(aLong);
    }
}

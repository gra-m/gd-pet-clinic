package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Speciality;
import fun.madeby.gdpetclinic.repositories.SpecialityRepository;
import fun.madeby.gdpetclinic.services.SpecialitiesService;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Gra_m on 2022 04 08
 */

public class SpecialitiesSDJpaService implements SpecialitiesService {
    private final SpecialityRepository SPECIALITY_REPOSITORY;

    public SpecialitiesSDJpaService(SpecialityRepository speciality_repository) {
        SPECIALITY_REPOSITORY = speciality_repository;
    }

    @Override
    public Set<Speciality> findAll() {
        return new HashSet<>((Collection<? extends Speciality>)SPECIALITY_REPOSITORY.findAll());
    }

    @Override
    public Speciality findById(Long aLong) {
        return SPECIALITY_REPOSITORY.findById(aLong).orElseThrow(NoSuchElementException::new) ;
    }

    @Override
    public Speciality save(Speciality object) {
        return SPECIALITY_REPOSITORY.save(object);
    }

    @Override
    public void delete(Speciality object) {
        SPECIALITY_REPOSITORY.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        SPECIALITY_REPOSITORY.deleteById(aLong);
    }
}

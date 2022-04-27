package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Speciality;
import fun.madeby.gdpetclinic.repositories.SpecialityRepository;
import fun.madeby.gdpetclinic.services.SpecialitiesService;
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
public class SpecialitiesSDJpaService implements SpecialitiesService {
    private final SpecialityRepository specialityRepository;

    public SpecialitiesSDJpaService(SpecialityRepository speciality_repository) {
        specialityRepository = speciality_repository;
    }

    @Override
    public Set<Speciality> findAll() {
        return new HashSet<>((Collection<? extends Speciality>) specialityRepository.findAll());
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialityRepository.findById(aLong).orElseThrow(NoSuchElementException::new) ;
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialityRepository.deleteById(aLong);
    }
}

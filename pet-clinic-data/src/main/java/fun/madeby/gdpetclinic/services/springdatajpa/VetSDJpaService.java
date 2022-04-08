package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Vet;
import fun.madeby.gdpetclinic.repositories.VetRepository;
import fun.madeby.gdpetclinic.services.VetService;
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
public class VetSDJpaService implements VetService {

    private final VetRepository VET_REPOSITORY;
    public VetSDJpaService(VetRepository vetRepository) {
        this.VET_REPOSITORY = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> iterableToSet = new HashSet<>();
        VET_REPOSITORY.findAll().forEach(iterableToSet::add);
        return iterableToSet;
    }

    @Override
    public Vet findById(Long aLong) {
        return VET_REPOSITORY.findById(aLong).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Vet save(Vet object) {
        return VET_REPOSITORY.save(object);
    }

    @Override
    public void delete(Vet object) {
        VET_REPOSITORY.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        VET_REPOSITORY.deleteById(aLong);
    }

    @Override
    public Vet findByLastName(String lastName) {
        return VET_REPOSITORY.findVetByLastName(lastName);
    }
}

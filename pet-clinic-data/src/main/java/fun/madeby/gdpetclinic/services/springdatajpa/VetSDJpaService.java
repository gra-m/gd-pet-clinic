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

    private final VetRepository vetRepository;
    public VetSDJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> iterableToSet = new HashSet<>();
        vetRepository.findAll().forEach(iterableToSet::add);
        return iterableToSet;
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        vetRepository.deleteById(aLong);
    }

    @Override
    public Vet findByLastName(String lastName) {
        return vetRepository.findVetByLastName(lastName);
    }
}

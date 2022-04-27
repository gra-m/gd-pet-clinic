package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Visit;
import fun.madeby.gdpetclinic.repositories.VisitRepository;
import fun.madeby.gdpetclinic.services.VisitService;
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
public class VisitSDJpaService implements VisitService {
    private final VisitRepository visitRepository;

    public VisitSDJpaService(VisitRepository visit_repository) {
        visitRepository = visit_repository;
    }


    @Override
    public Set<Visit> findAll() {
        return new HashSet<>((Collection<? extends Visit>) visitRepository.findAll());
    }

    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        visitRepository.deleteById(aLong);
    }
}

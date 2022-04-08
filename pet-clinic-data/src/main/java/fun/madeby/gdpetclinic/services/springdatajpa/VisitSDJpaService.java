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
    private final VisitRepository VISIT_REPOSITORY;

    public VisitSDJpaService(VisitRepository visit_repository) {
        VISIT_REPOSITORY = visit_repository;
    }


    @Override
    public Set<Visit> findAll() {
        return new HashSet<>((Collection<? extends Visit>) VISIT_REPOSITORY.findAll());
    }

    @Override
    public Visit findById(Long aLong) {
        return VISIT_REPOSITORY.findById(aLong).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Visit save(Visit object) {
        return VISIT_REPOSITORY.save(object);
    }

    @Override
    public void delete(Visit object) {
        VISIT_REPOSITORY.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        VISIT_REPOSITORY.deleteById(aLong);
    }
}

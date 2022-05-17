package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Visit;
import fun.madeby.gdpetclinic.repositories.VisitRepository;
import fun.madeby.gdpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Gra_m on 2022 04 08
 */
@Service
@Profile("jpaService")
@Transactional
public class VisitSDJpaService extends AbstractJpaService<Visit, VisitRepository> implements VisitService {

    public VisitSDJpaService(VisitRepository repository) {
        super(repository);
    }
}

package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Speciality;
import fun.madeby.gdpetclinic.repositories.SpecialityRepository;
import fun.madeby.gdpetclinic.services.SpecialitiesService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Gra_m on 2022 04 08
 */

@Service
@Profile("jpaService")
@Transactional
public class SpecialitiesSDJpaService extends AbstractJpaService<Speciality, SpecialityRepository> implements SpecialitiesService {

    public SpecialitiesSDJpaService(SpecialityRepository repository) {
        super(repository);
    }

}

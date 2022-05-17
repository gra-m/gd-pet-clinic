package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Vet;
import fun.madeby.gdpetclinic.repositories.VetRepository;
import fun.madeby.gdpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Gra_m on 2022 04 08
 */

@Service
@Profile("jpaService")
@Transactional
public class VetSDJpaService extends AbstractJpaService<Vet, VetRepository> implements VetService {

    public VetSDJpaService(VetRepository repository) {
        super(repository);
    }

    @Override
    public Vet findByLastName(String lastName) {
       return repository.findVetByLastName(lastName);
    }
}

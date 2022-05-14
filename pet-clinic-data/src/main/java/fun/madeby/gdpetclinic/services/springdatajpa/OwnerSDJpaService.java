package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.repositories.OwnerRepository;
import fun.madeby.gdpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by Gra_m on 2022 04 07
 */

@Service
@Profile("jpaService")
@Transactional
public class OwnerSDJpaService extends AbstractJpaService<Owner, OwnerRepository> implements OwnerService {

    public OwnerSDJpaService(OwnerRepository repository) {
        super(repository);
    }


    @Override
    public Owner findByLastName(String lastName) {
        return repository.findOwnerByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastName(String lastName) {
       return repository.findAllByLastNameLikeIgnoreCase("%" + lastName + "%");
    }
}

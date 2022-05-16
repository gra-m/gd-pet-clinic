package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.repositories.PetRepository;
import fun.madeby.gdpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Gra_m on 2022 04 08
 */

@Service
@Profile("jpaService")
@Transactional
public class PetSDJpaService extends AbstractJpaService<Pet, PetRepository> implements PetService {

    public PetSDJpaService(PetRepository repository) {
        super(repository);
    }
}

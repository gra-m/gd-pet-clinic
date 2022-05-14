package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.repositories.OwnerRepository;
import fun.madeby.gdpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

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

/*    @Override
    public Set<Owner> findAll() {
        Set<Owner> allOwners = new HashSet<>();
        repository.findAll().forEach(allOwners::add);
        return allOwners;
    }*/

    @Override
    public Owner findById(Long aLong) {
        try {
            return repository.findById(aLong).orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e) {
            System.out.println("PURPOSELY THROWN NoSuchElementException:OwnerSDJpaService\n|| findById returning null: could display a code and be added to log");
            e.printStackTrace();
        }
        return null;
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

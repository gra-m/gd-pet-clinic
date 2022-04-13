package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.repositories.OwnerRepository;
import fun.madeby.gdpetclinic.repositories.PetRepository;
import fun.madeby.gdpetclinic.repositories.PetTypeRepository;
import fun.madeby.gdpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Gra_m on 2022 04 07
 */

@Service
@Profile("jpaService")
public class OwnerSDJpaService implements OwnerService {
    private final OwnerRepository OWNER_REPO;
    private final PetRepository PET_REPO;
    private final PetTypeRepository PET_TYPE_REPO;

    public OwnerSDJpaService(OwnerRepository owner_repo,
                             PetRepository pet_repo,
                             PetTypeRepository pet_type_repo) {
        OWNER_REPO = owner_repo;
        PET_REPO = pet_repo;
        PET_TYPE_REPO = pet_type_repo;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> allOwners = new HashSet<>();
        OWNER_REPO.findAll().forEach(allOwners::add);
        return allOwners;
    }

    @Override
    public Owner findById(Long aLong) {
        try {
            return OWNER_REPO.findById(aLong).orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e) {
            System.out.println("Owner findById returning null: could display a code and be added to log");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Owner save(Owner object) {
       return OWNER_REPO.save(object);
    }

    @Override
    public void delete(Owner object) {
        OWNER_REPO.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        OWNER_REPO.deleteById(aLong);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return OWNER_REPO.findOwnerByLastName(lastName);
    }
}

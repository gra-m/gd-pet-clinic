package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.repositories.OwnerRepository;
import fun.madeby.gdpetclinic.repositories.PetRepository;
import fun.madeby.gdpetclinic.repositories.PetTypeRepository;
import fun.madeby.gdpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Gra_m on 2022 04 07
 */

@Service
@Profile("jpaService")
public class OwnerSDJpaService implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepository owner_repo,
                             PetRepository pet_repo,
                             PetTypeRepository pet_type_repo) {
        ownerRepository = owner_repo;
        petRepository = pet_repo;
        petTypeRepository = pet_type_repo;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> allOwners = new HashSet<>();
        ownerRepository.findAll().forEach(allOwners::add);
        return allOwners;
    }

    @Override
    public Owner findById(Long aLong) {
        try {
            return ownerRepository.findById(aLong).orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e) {
            System.out.println("PURPOSELY THROWN NoSuchElementException:OwnerSDJpaService\n|| findById returning null: could display a code and be added to log");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Owner save(Owner object) {
       return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findOwnerByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastName(String lastName) {
       return ownerRepository.findAllByLastNameLikeIgnoreCase("%" + lastName + "%");
    }
}

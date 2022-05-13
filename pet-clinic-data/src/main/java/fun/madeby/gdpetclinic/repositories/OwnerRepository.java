package fun.madeby.gdpetclinic.repositories;

import fun.madeby.gdpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Gra_m on 2022 04 07
 */
@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findOwnerByLastName(String lastName);
    List<Owner> findAllByLastNameLikeIgnoreCase(String lastName);
}

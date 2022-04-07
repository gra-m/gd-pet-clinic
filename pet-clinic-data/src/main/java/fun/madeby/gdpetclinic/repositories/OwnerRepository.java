package fun.madeby.gdpetclinic.repositories;

import fun.madeby.gdpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Gra_m on 2022 04 07
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
}

package fun.madeby.gdpetclinic.repositories;

import fun.madeby.gdpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Gra_m on 2022 04 07
 */

public interface PetRepository extends CrudRepository<Pet, Long> {
}

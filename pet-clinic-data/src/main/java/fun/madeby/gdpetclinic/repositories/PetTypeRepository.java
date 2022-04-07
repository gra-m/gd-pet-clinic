package fun.madeby.gdpetclinic.repositories;

import fun.madeby.gdpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Gra_m on 2022 04 07
 */

public interface PetTypeRepository extends CrudRepository <PetType, Long> {
}

package fun.madeby.gdpetclinic.repositories;

import fun.madeby.gdpetclinic.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Gra_m on 2022 04 07
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}

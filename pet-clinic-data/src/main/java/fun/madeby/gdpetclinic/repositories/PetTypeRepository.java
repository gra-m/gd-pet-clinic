package fun.madeby.gdpetclinic.repositories;

import fun.madeby.gdpetclinic.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Gra_m on 2022 04 07
 */
@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long> {
}

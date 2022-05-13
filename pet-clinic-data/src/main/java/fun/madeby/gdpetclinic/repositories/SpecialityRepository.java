package fun.madeby.gdpetclinic.repositories;

import fun.madeby.gdpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Gra_m on 2022 04 07
 */
@Repository
public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}

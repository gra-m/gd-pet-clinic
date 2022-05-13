package fun.madeby.gdpetclinic.repositories;

import fun.madeby.gdpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Gra_m on 2022 04 07
 */
@Repository
public interface VetRepository extends CrudRepository<Vet, Long> {

    Vet findVetByLastName(String lastName);
}

package fun.madeby.gdpetclinic.repositories;

import fun.madeby.gdpetclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Gra_m on 2022 04 07
 *
 * Refactoring CrudRepository -> PagingAndSorting -> JpaRepository
 *
 *
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findOwnerByLastName(String lastName);
    List<Owner> findAllByLastNameLikeIgnoreCase(String lastName);
}

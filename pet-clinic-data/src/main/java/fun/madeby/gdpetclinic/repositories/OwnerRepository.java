package fun.madeby.gdpetclinic.repositories;

import fun.madeby.gdpetclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Gra_m on 2022 04 07
 *
 * Refactoring 14/05, extending both CrudRepository and JpaRepository is a temp measure
 */
@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long>, JpaRepository<Owner, Long> {
    Owner findOwnerByLastName(String lastName);
    List<Owner> findAllByLastNameLikeIgnoreCase(String lastName);
}

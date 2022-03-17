package fun.madeby.gdpetclinic.services;

import fun.madeby.gdpetclinic.model.Owner;

import java.util.Set;

/**
 * Created by Gra_m on 2022 03 17
 */

public interface OwnerService {
    Owner findByLastName(String lastName);
    Owner findById(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}

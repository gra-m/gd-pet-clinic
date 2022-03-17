package fun.madeby.gdpetclinic.services;

import fun.madeby.gdpetclinic.model.Owner;

/**
 * Created by Gra_m on 2022 03 17
 */

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}

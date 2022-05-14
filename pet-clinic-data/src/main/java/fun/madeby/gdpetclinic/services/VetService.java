package fun.madeby.gdpetclinic.services;

import fun.madeby.gdpetclinic.model.Vet;

/**
 * Created by Gra_m on 2022 03 17
 */

public interface VetService extends CrudService<Vet>{
    Vet findByLastName(String lastName);
}

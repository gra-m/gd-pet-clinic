package fun.madeby.gdpetclinic.services;

import fun.madeby.gdpetclinic.model.Vet;

import java.util.Set;

/**
 * Created by Gra_m on 2022 03 17
 */

public interface VetService {
    Vet findByLastName(String lastName);
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}

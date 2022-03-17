package fun.madeby.gdpetclinic.services;

import fun.madeby.gdpetclinic.model.Pet;

import java.util.Set;

/**
 * Created by Gra_m on 2022 03 17
 */

public interface PetService {
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}

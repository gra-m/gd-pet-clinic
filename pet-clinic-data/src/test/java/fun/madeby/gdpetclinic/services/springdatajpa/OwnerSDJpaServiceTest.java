package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.repositories.OwnerRepository;
import fun.madeby.gdpetclinic.repositories.PetRepository;
import fun.madeby.gdpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    OwnerRepository OWNER_REPO;
    PetRepository PET_REPO;
    PetTypeRepository PET_TYPE_REPO;

    OwnerSDJpaService ownerSDJpaService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findByLastName() {
    }
}
package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long ID = 1L;
    final String LAST_NAME = "Smith";
    final Set<Pet> pets = new HashSet<>();
    Owner owner;

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        owner = Owner.builder().id(1L).pets(pets).lastName(LAST_NAME).build();
        ownerServiceMap.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void deleteById() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());

        ownerServiceMap.deleteById(ID);
        ownerSet = ownerServiceMap.findAll();
        assertEquals(0, ownerSet.size());

    }

    @Test
    void deleteByObject() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());

        ownerServiceMap.delete(owner);
        ownerSet = ownerServiceMap.findAll();
        assertEquals(0, ownerSet.size());
    }

    @Test
    void save() {
        Long O2_ID = 2L;
        Owner owner2 = Owner.builder().id(O2_ID).pets(pets).build();

        Owner savedOwner2 = ownerServiceMap.save(owner2);

        assertEquals(O2_ID, savedOwner2.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ID);

        assertEquals(ID, owner.getId());
    }

    @org.junit.jupiter.api.Test
    void findByLastName() {
        Owner smith = ownerServiceMap.findByLastName(LAST_NAME);

        assertNotNull(smith);
        assertEquals(ID, smith.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerServiceMap.findByLastName("asdfas");

        assertNull(smith);

    }
}
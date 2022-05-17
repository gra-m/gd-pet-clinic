package fun.madeby.gdpetclinic.services.map;

import fun.madeby.gdpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapTest {

	PetServiceMap petServiceMap;

	final Long ID1 = 4L;
	final Long ID2 = 5L;
	final Long ID3 = 6L;
	final String PET_NAME = "Wiggles";
	Pet pet1;
	Pet pet2;



	@BeforeEach
	void setUp() {
		petServiceMap = new PetServiceMap();
		pet1 = new Pet();
		pet2 = new Pet();

		pet1.setId(ID1);
		pet2.setId(ID2);

		pet1.setName("Chewfronta");
		pet2.setName("Chigwell");
		petServiceMap.save(pet1);
		petServiceMap.save(pet2);
	}

	@Test
	void findAll() {
		Set<Pet> petSet = petServiceMap.findAll();
		assertEquals(2, petSet.size());
	}

	@Test
	void deleteById() {
		petServiceMap.deleteById(ID2);
		Set<Pet> petSet = petServiceMap.findAll();


		assertEquals(1, petSet.size());
	}

	@Test
	void delete() {
		petServiceMap.delete(pet1);
		Set<Pet> petSet = petServiceMap.findAll();

		assertEquals(1, petSet.size());
	}

	@Test
	void save() {
		Pet pet3 = new Pet();
		pet3.setId(ID3);
		petServiceMap.save(pet3);

		Set<Pet> petSet = petServiceMap.findAll();
		assertEquals(3, petSet.size());
	}

	@Test
	void findById() {
		Pet recoveredPet = petServiceMap.findById(ID1);

		assertEquals("Chewfronta", recoveredPet.getName());
	}
}
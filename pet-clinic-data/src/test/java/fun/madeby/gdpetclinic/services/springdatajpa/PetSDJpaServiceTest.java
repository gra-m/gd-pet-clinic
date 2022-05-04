package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.model.PetType;
import fun.madeby.gdpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

	@InjectMocks
	PetSDJpaService serviceUnderTest;

	@Mock
	PetRepository petRepository;

	Owner returnOwner;
	Pet returnPet1;
	Pet returnPet2;
	Pet returnPet3;
	PetType returnPetType1;
	PetType returnPetType2;
	Set<Pet> petSet = new HashSet<>();

	Long ownerId = 1L;
	Long returnPet1Id = 1L;
	Long returnPet2Id = 45L;
	Long returnPet3Id = 8L;


	@BeforeEach
	void setUp() {


		returnOwner = Owner.builder().id(ownerId).lastName("Smith").build();
		returnPetType1 = PetType.builder().name("Dog").build();
		returnPetType2 = PetType.builder().name("Cat").build();
		returnPet1 = Pet.builder().id(returnPet1Id).name("Dogkey").build();
		returnPet2 = Pet.builder().id(returnPet2Id).name("Tiddles").build();
		returnPet3 = Pet.builder().id(returnPet3Id).name("Sang-Froid").build();

		petSet.add(returnPet1);
		petSet.add(returnPet2);
		petSet.add(returnPet3);
	}

	@Test
	@DisplayName("PassPath_FindAllPets")
	void testFindAll() {
		//given
		when(petRepository.findAll())
				.thenReturn(petSet);
		//when
		Set<Pet> returnedSet = serviceUnderTest.findAll();

		//then
		assertNotNull(returnedSet);
		assertEquals(petSet.size(), returnedSet.size());
		verify(petRepository, times(1)).findAll();

	}

	@Test
	@DisplayName("PassPath_FindPetById")
	void testFindById() {
		Optional<Pet> returnPet1Optional = Optional.of(returnPet1);
		//given
		when(petRepository.findById(anyLong()))
				.thenReturn(returnPet1Optional);

		//when
		Pet returnedPet =  serviceUnderTest.findById(returnPet1Id);

		assertNotNull(returnedPet);

	}


	@Test
	@DisplayName("TestFindByIdNotFound: Throws ElementNotFoundException")
	void testFindByIdNotFound() {
		Optional<Pet> returnPet1Optional = Optional.empty();
		//given
		when(petRepository.findById(anyLong()))
				.thenReturn(returnPet1Optional);

		assertThrows(NoSuchElementException.class, () -> serviceUnderTest.findById(returnPet1Id));
		verify(petRepository, times(1)).findById(anyLong());
	}


	@Test
	@DisplayName("testSaveSuccessfully: ")
	void testSaveSuccessfully() {
		//given
		when(petRepository.save(any(Pet.class)))
				.thenReturn(returnPet1);
		//when
		Pet savedPet = serviceUnderTest.save(returnPet1);

		//then
		verify(petRepository).save(any(Pet.class));


	}

	@Test
	@DisplayName("testDeleteSuccessfullyPassingPetObject")
	void testDeleteSuccessfullyPassingPetObject() {
		serviceUnderTest.delete(returnPet3);

		verify(petRepository).delete(any());
	}

	@Test
	@DisplayName("testDeleteSuccessfullyById")
	void testDeleteSuccessfullyById() {
		serviceUnderTest.deleteById(returnPet3Id);

		verify(petRepository).deleteById(anyLong());
	}
}
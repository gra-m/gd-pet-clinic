package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.PetType;
import fun.madeby.gdpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PetTypeSDJpaServiceTest {

	@Mock
	PetTypeRepository petTypeRepository;

	@InjectMocks
	PetTypeSDJpaService serviceUnderTest;

	PetType returnPetType1;
	PetType returnPetType2;
	final Long RETURNED_PET_TYPE1_ID = 1L;
	final Long RETURNED_PET_TYPE2_ID = 2L;
	final String RETURNED_PET_TYPE1_NAME = "Thing";
	final String RETURNED_PET_TYPE2_NAME = "Otterman";

	List<PetType> allPetTypesList = new ArrayList<>(2);

	@BeforeEach
	void setUp() {
		returnPetType1 = new PetType();
		returnPetType1.setId(RETURNED_PET_TYPE1_ID);
		returnPetType1.setName(RETURNED_PET_TYPE1_NAME);
		returnPetType2 = new PetType();
		returnPetType2.setId(RETURNED_PET_TYPE2_ID);
		returnPetType2.setName(RETURNED_PET_TYPE2_NAME);

		allPetTypesList.add(returnPetType1);
		allPetTypesList.add(returnPetType2);
	}

	@Test
	@DisplayName("PassPath_FindAllPetTypes")
 	void testFindAll() {
		when(petTypeRepository.findAll())
				.thenReturn(allPetTypesList);

		Set<PetType> returnedPetTypeSet = serviceUnderTest.findAll();

		assertNotNull(returnedPetTypeSet);
		assertEquals(allPetTypesList.size(), returnedPetTypeSet.size());
		verify(petTypeRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("PassPath_FindPetTypeById")
	void testFindById() {
		Optional<PetType> returnPetType1Optional = Optional.of(returnPetType1);

		when(petTypeRepository.findById(anyLong()))
				.thenReturn(returnPetType1Optional);

		PetType returnedPetType = serviceUnderTest.findById(RETURNED_PET_TYPE1_ID);

		assertNotNull(returnedPetType);

	}

	@Test
	@DisplayName("FailPath_TestFindPetTypeByIdNotFound_Throws ElementNotFoundException")
	void testFindByIdNotFound() {
		Optional<PetType> returnPet2Optional = Optional.empty();
		//given
		when(petTypeRepository.findById(anyLong()))
				.thenReturn(returnPet2Optional);

		assertThrows(NoSuchElementException.class, () -> serviceUnderTest.findById(RETURNED_PET_TYPE2_ID));
		verify(petTypeRepository, times(1)).findById(anyLong());
	}




	@Test
	@DisplayName("PassPath_SavePetType")
	void testSave() {
		//given returnOwner
		when(petTypeRepository.save(any()))
				.thenReturn(returnPetType1);
		//then
		PetType savedPetType1 = serviceUnderTest.save(returnPetType1);
		assertNotNull(savedPetType1);

		verify(petTypeRepository).save(any());
	}

	@Test
	@DisplayName("PassPath_DeletePetType")
	void testDelete() {
		//when
		serviceUnderTest.delete(returnPetType1);
		//then
		verify(petTypeRepository).delete(any()); // required here void method
	}

	@Test
	@DisplayName("PassPath_DeletePetTypeById")
	void deleteById() {
		//when
		serviceUnderTest.deleteById(1L);
		//then
		verify(petTypeRepository).deleteById(anyLong());
	}

}
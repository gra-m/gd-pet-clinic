package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Speciality;
import fun.madeby.gdpetclinic.repositories.SpecialityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class SpecialitiesSDJpaServiceTest {

	@Mock
	SpecialityRepository specialityRepository;

	@InjectMocks
	SpecialitiesSDJpaService serviceUnderTest;

	Speciality speciality01;
	Speciality speciality02;

	final Long SPECIALITY1_ID = 1L;
	final Long SPECIALITY2_ID = 2L;
	final String SPECIALITY1_DESCRIPTION = "Cardiology";
	final String SPECIALITY2_DESCRIPTION = "Virology";

	List<Speciality> specialityList = new ArrayList<>(2);


	@BeforeEach
	void setUp() {
		speciality01 = new Speciality();
		speciality01.setId(SPECIALITY1_ID);
		speciality01.setDescription(SPECIALITY1_DESCRIPTION);
		speciality02 = new Speciality();
		speciality02.setId(SPECIALITY2_ID);
		speciality02.setDescription(SPECIALITY2_DESCRIPTION);

		specialityList.add(speciality01);
		specialityList.add(speciality02);
	}



	@Test
	@DisplayName("PassPath_FindAllSpecialities")
	void testFindAll() {
		when(specialityRepository.findAll())
				.thenReturn(specialityList);

		Set<Speciality> returnedSpecialitySet = serviceUnderTest.findAll();

		assertNotNull(returnedSpecialitySet);
		assertEquals(specialityList.size(), returnedSpecialitySet.size());
		verify(specialityRepository).findAll();
	}

	@Test
	@DisplayName("PassPath_FindSpecialityById")
	void testFindById() {
		Optional<Speciality> returnSpeciality1Optional = Optional.of(speciality01);

		when(specialityRepository.findById(anyLong()))
				.thenReturn(returnSpeciality1Optional);

		Speciality returnedSpeciality = serviceUnderTest.findById(SPECIALITY1_ID);

		assertNotNull(returnedSpeciality);

	}

	@Test
	@DisplayName("FailPath_TestFindSpecialityByIdNotFound_Throws ElementNotFoundException")
	void testFindByIdNotFound() {
		Optional<Speciality> returnEmptyOptional = Optional.empty();
		//given
		when(specialityRepository.findById(anyLong()))
				.thenReturn(returnEmptyOptional);

		assertThrows(NoSuchElementException.class, () -> serviceUnderTest.findById(SPECIALITY2_ID));
		verify(specialityRepository, times(1)).findById(anyLong());
	}




	@Test
	@DisplayName("PassPath_SaveSpeciality")
	void testSave() {
		//given returnOwner
		when(specialityRepository.save(any()))
				.thenReturn(speciality01);
		//then
		Speciality savedSpeciality1 = serviceUnderTest.save(speciality01);
		assertNotNull(savedSpeciality1);

		verify(specialityRepository).save(any());
	}

	@Test
	@DisplayName("PassPath_DeleteSpeciality")
	void testDelete() {
		//when
		serviceUnderTest.delete(speciality01);
		//then
		verify(specialityRepository).delete(any()); // required here void method
	}

	@Test
	@DisplayName("PassPath_DeleteSpecialityById")
	void deleteById() {
		//when
		serviceUnderTest.deleteById(1L);
		//then
		verify(specialityRepository).deleteById(anyLong());
	}










}
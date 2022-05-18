package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Vet;
import fun.madeby.gdpetclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

	@Mock
	VetRepository vetRepository;
	Vet returnVet;
	Vet returnVet2;
	String RETURN_VET_LASTNAME = "Prise";

	List<Vet> vetList = new ArrayList<>(2);
	@InjectMocks
	VetSDJpaService serviceUnderTest;

	final Long VET1_ID = 1L;
	final Long VET2_ID = 2L;
	final Long NON_EXISTING_VET_ID = 100_000L;

	@BeforeEach
	void setUp() {
		returnVet = new Vet();
		returnVet.setId(VET1_ID);
		returnVet.setFirstName("Gill");
		returnVet.setLastName(RETURN_VET_LASTNAME);
		returnVet2 = new Vet();
		returnVet2.setId(VET2_ID);

		vetList.add(returnVet);
		vetList.add(returnVet2);
	}

	@Test
	@DisplayName("PassPath_FindVetByLastName")
	void testFindByLastName() {
		//given return owner
		when(vetRepository.findVetByLastName(any()))
				.thenReturn(returnVet);

		Vet vet = serviceUnderTest.findByVetByLastName(RETURN_VET_LASTNAME);

		assertNotNull(vet);
		assertEquals(RETURN_VET_LASTNAME, vet.getLastName());
	}

	@Test
	@DisplayName("PassPath_FindAllVets")
	void findAll() {
		//given
		//JpaRepository  returns a list by default
		when(vetRepository.findAll())
				.thenReturn(vetList);
		//service Set comes from implementation of CrudService via OwnerService
		Set<Vet> vets = serviceUnderTest.findAll();
		//then
		assertNotNull(vets);
		verify(vetRepository, times(1)).findAll();
		assertEquals(2, vets.size());
	}


	@Test
	@DisplayName("PassPath_FindVetbyID")
	void findByIdFound() {
		//given returnOwner
		when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));
		Vet vet = serviceUnderTest.findById(1L);
		//then
		assertNotNull(vet);
	}

	@Test
    @DisplayName("FailPath_findVetbyID_ThrowsElementNotFoundException")
    void findByIdNotFound() {
        //given returnOwner
        when(vetRepository.findById(anyLong()))
				.thenReturn(Optional.empty());
        //then
		assertThrows(NoSuchElementException.class, () -> serviceUnderTest.findById(NON_EXISTING_VET_ID));
		verify(vetRepository).findById(anyLong());

    }

	@Test
	@DisplayName("PassPath_save(Vet)")
	void save() {
		when(vetRepository.save(any()))
				.thenReturn(returnVet);
		//then
		Vet savedVet = serviceUnderTest.save(returnVet);

		assertNotNull(savedVet);
		verify(vetRepository).save(any());

	}

	@Test
	@DisplayName("PassPath_delete(Vet)")
	void delete() {
		//given returnOwner
		//when
		serviceUnderTest.delete(returnVet);
		//then
		verify(vetRepository).delete(any()); // required here void method
	}

	@Test
	@DisplayName("PassPath_deleteById(Long id)")
	void deleteById() {
		//given returnOwner
		//when
		serviceUnderTest.deleteById(1L);
		//then
		verify(vetRepository, times(1)).deleteById(anyLong());
	}

}



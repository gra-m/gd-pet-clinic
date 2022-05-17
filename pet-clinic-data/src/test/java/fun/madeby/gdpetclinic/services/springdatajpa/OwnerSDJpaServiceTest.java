package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.repositories.OwnerRepository;
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


@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository OWNER_REPO;
    Owner returnOwner;
    @InjectMocks
    OwnerSDJpaService serviceUnderTest;

    @BeforeEach
    void setUp() {
        // ensure clean object for each test
        returnOwner = Owner.builder().id(1L).lastName("Smith").build();
    }

    @Test
    @DisplayName("PassPath_FindAllOwners")
    void findAll() {
        //given
        //JpaRepository can only return a list
        List<Owner> returnOwnersList = new ArrayList<>();
        returnOwnersList.add(returnOwner);
        returnOwnersList.add(Owner.builder().id(2L).build());
        when(OWNER_REPO.findAll())
                .thenReturn(returnOwnersList);
        //service Set comes from implementation of CrudService via OwnerService
        Set<Owner> owners = serviceUnderTest.findAll();
        //then
        assertNotNull(owners);
        verify(OWNER_REPO, times(1)).findAll();
        assertEquals(2, owners.size());
    }

    @Test
    @DisplayName("PassPath_FindOwnerbyID")
    void findByIdFound() {
        //given returnOwner
        when(OWNER_REPO.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = serviceUnderTest.findById(1L);
        //then
        assertNotNull(owner);
    }

    /*@Test
    @DisplayName("FailPath findOwnerbyID")
    void findByIdNotFound() {
        //given returnOwner
        when(OWNER_REPO.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = service.findById(1L);
        //then
        assertNull(owner);
    }*/

    @Test
    @DisplayName("TestFindOwnerByIdNotFound: Throws ElementNotFoundException")
    void testFindByIdNotFound() {
        Optional<Owner> returnOwner1Optional = Optional.empty();
        //given
        when(OWNER_REPO.findById(anyLong()))
                .thenReturn(returnOwner1Optional);

        assertThrows(NoSuchElementException.class, () -> serviceUnderTest.findById(returnOwner.getId()));
        verify(OWNER_REPO, times(1)).findById(anyLong());
    }




    @Test
    void save() {
        when(OWNER_REPO.save(any()))
                .thenReturn(returnOwner);
        //then
        Owner savedOwner = serviceUnderTest.save(returnOwner);
        assertNotNull(savedOwner);

        verify(OWNER_REPO).save(any());

    }

    @Test
    void delete() {
        //given returnOwner
        //when
        serviceUnderTest.delete(returnOwner);
        //then
        verify(OWNER_REPO).delete(any()); // required here void method
    }

    @Test
    void deleteById() {
        //given returnOwner
        //when
        serviceUnderTest.deleteById(1L);
        //then
        verify(OWNER_REPO, times(1)).deleteById(anyLong()); // here showing how no of 'times'
    }

    @Test
    void findByLastName() {
        //given return owner
        when(OWNER_REPO.findOwnerByLastName(any()))
                .thenReturn(returnOwner);
        Owner owner = serviceUnderTest.findByLastName("smith");
    }
}
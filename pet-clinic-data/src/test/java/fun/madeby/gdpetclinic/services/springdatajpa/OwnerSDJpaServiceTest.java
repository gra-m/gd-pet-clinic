package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.repositories.OwnerRepository;
import fun.madeby.gdpetclinic.repositories.PetRepository;
import fun.madeby.gdpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository OWNER_REPO;
    @Mock
    PetRepository PET_REPO;
    @Mock
    PetTypeRepository PET_TYPE_REPO;
    Owner returnOwner;
    @InjectMocks
    OwnerSDJpaService service;

    @BeforeEach
    void setUp() {
        // ensure clean object for each test
        returnOwner = Owner.builder().id(1L).lastName("Smith").build();
    }

    @Test
    void findAll() {
        //given
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(returnOwner);
        returnOwnersSet.add(Owner.builder().id(2L).build());
        when(OWNER_REPO.findAll())
                .thenReturn(returnOwnersSet);
        Set<Owner> owners = service.findAll();
        //then
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findByIdFound() {
        //given returnOwner
        when(OWNER_REPO.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = service.findById(1L);
        //then
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        //given returnOwner
        when(OWNER_REPO.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = service.findById(1L);
        //then
        assertNull(owner);
    }

    @Test
    void save() {
        //given returnOwner
        when(OWNER_REPO.save(any()))
                .thenReturn(returnOwner);
        //then
        Owner savedOwner = service.save(returnOwner); // not saving another made owner think any() makes arbitrary
        assertNotNull(savedOwner);

        verify(OWNER_REPO).save(any()); // Mockito verify to called default once (belt and braces)

    }

    @Test
    void delete() {
        //given returnOwner
        //when
        service.delete(returnOwner);
        //then
        verify(OWNER_REPO).delete(any()); // required here void method
    }

    @Test
    void deleteById() {
        //given returnOwner
        //when
        service.deleteById(1L);
        //then
        verify(OWNER_REPO, times(1)).deleteById(anyLong()); // here showing how no of 'times'
    }

    @Test
    void findByLastName() {
        //given return owner
        when(OWNER_REPO.findOwnerByLastName(any()))
                .thenReturn(returnOwner);
        Owner owner = service.findByLastName("smith");
    }
}
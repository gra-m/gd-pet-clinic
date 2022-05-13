package fun.madeby.gdpetclinic.services.springdatajpa;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.repositories.OwnerRepository;
import fun.madeby.gdpetclinic.repositories.PetRepository;
import fun.madeby.gdpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

// 1 Have added fun.madeby.pet-clinic-web to this modules dependencies

//@ComponentScan(basePackages = {"fun.madeby.pet-clinic-web", "fun.madeby.pet-clinic-data"}) //Has to bring in another module, tried for first time == annotation processing is not supported for module cycles.
@SpringBootTest
class OwnerSdJpaServiceIntTest {

    static final String NEW_NAME = "This is their New Name";
    static final String OLD_NAME = "This is their Old Name";

    Owner owner1 = new Owner();

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    OwnerSDJpaService ownerSDJpaService;


    @BeforeEach
    void setUp() {
        owner1 = Owner.builder().id(1L).firstName(OLD_NAME).lastName("Smith").build();
    }

/*
    @Test
    void testConvertingSaveNewFirstName() {
        owner1.setFirstName(NEW_NAME);

        assertNotNull(owner1);
        Owner updatedOwner1 = ownerSDJpaService.save(owner1);

        assertNotNull(updatedOwner1);




    }
*/

}
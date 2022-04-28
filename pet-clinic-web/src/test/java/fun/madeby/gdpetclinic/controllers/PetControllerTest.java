package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.services.OwnerService;
import fun.madeby.gdpetclinic.services.PetService;
import fun.madeby.gdpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {
    @InjectMocks
    PetController controllerUnderTest;

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    Pet pet1;
    Long pet1Id = 1L;
    Set<Pet> petSet;
    MockMvc mockMvc;


    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/pet-form";

    @BeforeEach
    void setUp() {
        petSet = new HashSet<>();
        pet1 = Pet.builder().id(pet1Id).build();
        petSet.add(pet1);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controllerUnderTest)
                .build();
    }


   /* @Test
    void testInitCreatePetForm() {
    }

    @Test
    void testProcessCreatePetForm() {
    }

    @Test
    void testInitUpdatePetForm() {
    }

    @Test
    void testProcessUpdatePetForm() {
    }*/
}
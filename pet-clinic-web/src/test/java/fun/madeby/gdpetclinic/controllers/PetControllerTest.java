package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.model.PetType;
import fun.madeby.gdpetclinic.services.OwnerService;
import fun.madeby.gdpetclinic.services.PetService;
import fun.madeby.gdpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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

    Owner owner1;
    Long owner1Id = 1L;
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


        owner1 = Owner.builder().id(owner1Id).build();
        mockMvc = MockMvcBuilders
                .standaloneSetup(controllerUnderTest)
                .build();
    }


    //region ADDNEW
    @Test
    void testInitCreatePetForm() throws Exception {
        when(ownerService.findById(anyLong()))
                .thenReturn(owner1);


        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM))
                .andExpect(model().attributeExists("pet"));
                //.andExpect(model().attributeExists("owner")); exists Owner name displayed, @ModelAttribute.

    }

    @Test
    void testProcessCreatePetForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
        //verify(petService.save(ArgumentMatchers.any(Pet.class)));

    }

    //endregion

    //region UPDATE
    @Test
    void testInitUpdatePetForm() throws Exception {

        when(petService.findById(anyLong())).thenReturn(pet1);

        mockMvc.perform(get("/owners/1/pets/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM))
                .andExpect(model().attributeExists("pet"));
        verify(petService, times(1)).findById(anyLong());

    }

    @Test
    void testProcessUpdatePetForm() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(owner1);

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    //endregion
}
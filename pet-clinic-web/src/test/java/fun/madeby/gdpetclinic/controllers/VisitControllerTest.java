package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.model.Visit;
import fun.madeby.gdpetclinic.services.PetService;
import fun.madeby.gdpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    private static final String NEW_VISIT_PATH = "/owners/1/pets/1/visits/new";
    private static final String VIEWS_VISITS_CREATE_OR_UPDATE_FORM = "pets/visit-form";
    private static final String VIEWS_DISPLAY_OWNER_WITH_NEW_OR_UPDATED_VISIT = "redirect:/owners/";

    @InjectMocks
    VisitController controllerUnderTest;

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    MockMvc mockMvc;

    Pet pet1;
    Long pet1Id = 1L;
    Owner owner1;
    Long owner1Id = 5L;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();

        owner1 = Owner.builder().id(owner1Id).build();
        pet1 = Pet.builder().id(pet1Id).owner(owner1).build();
    }

    @Test
    void testInitCreateVisitForm() throws Exception {

        //Not explicitly called in initCreateVisitForm see injection && findById in @ModelAttribute
        when(petService.findById(pet1Id))
                .thenReturn(pet1);

        mockMvc.perform(get(NEW_VISIT_PATH))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name(VIEWS_VISITS_CREATE_OR_UPDATE_FORM));
    }

    @Test
    void processCreateVisitForm() throws Exception {
        when(petService.findById(pet1Id))
                .thenReturn(pet1);

        mockMvc.perform(post(NEW_VISIT_PATH))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("visit"))
                        .andExpect(view().name(VIEWS_DISPLAY_OWNER_WITH_NEW_OR_UPDATED_VISIT + owner1Id));

        verify(visitService, times(1)).save(ArgumentMatchers.any(Visit.class));
    }
}
package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController controllerUnderTest;
    // for comparison if poss to get to returned objs
    Owner owner1;
    Long owner1Id = 1L;
    Set<Owner> ownerSet;
    // My business logic insists on petSet, can be empty
    Set<Pet> petSet;
    // Test endpoints, standalone == less resource
    MockMvc mockMvc;
    String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "/unknown/at/present";

    @BeforeEach
    void setUp() {
        ownerSet = new HashSet<>();
        //(same petset poss issue if H2)
        owner1 = Owner.builder().id(owner1Id).pets(petSet).build();
        ownerSet.add(owner1);
        ownerSet.add(Owner.builder().id(2L).pets(petSet).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controllerUnderTest)
                .build();
    }


    // region Owner Create/Update

    @Test
    void testInitOwnerCreationForm() throws Exception {
        //given
        Map<String, Object> model = new HashMap<>(1);
        Owner owner = Owner.builder().id(3L).build();
        model.put("owner", owner);

        //when

        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(0)).save(ArgumentMatchers.any(Owner.class));

    }

    @Test
    void testProcessOwnerCreationForm() throws Exception {
        //given
        when(ownerService.save(ArgumentMatchers.any()))
                .thenReturn(owner1);

        //then
        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + owner1Id))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void testInitUpdateOwnerForm() throws Exception {
        //given
        when(ownerService.findById(anyLong()))
                .thenReturn(owner1);

        //then
        mockMvc.perform(get("/owners/" + owner1Id + "/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));

        verify(ownerService, times(0)).save(ArgumentMatchers.any(Owner.class));

    }

    @Test
    void testProcessUpdateOwnerForm() throws Exception {
        //given
        when(ownerService.save(ArgumentMatchers.any(Owner.class)))
                .thenReturn(owner1);

        //then
        mockMvc.perform(post("/owners/" + owner1Id + "/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("redirect:/owners/" + owner1Id));
    }








    //endregion



    @Test
    void testGetOwner() throws Exception {
        //given
        when(ownerService.findById(anyLong())).thenReturn(owner1);

        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", hasProperty("id", is(owner1Id))));

    }


    //region FIND by LastName TESTS
    @Test
    void findOwners() throws Exception {

        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/find-owners"))
                .andExpect(model().attributeExists("owner"));


        verifyNoInteractions(ownerService);

    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findAllByLastName(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).build(),
                        Owner.builder().id(2L).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owner-list"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        when(ownerService.findAllByLastName(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void processFindFormEmptyReturnMany() throws Exception {
        when(ownerService.findAllByLastName(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).build(),
                        Owner.builder().id(2L).build()));

        mockMvc.perform(get("/owners")
                        .param("lastName",""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owner-list"))
                .andExpect(model().attribute("selections", hasSize(2)));;
    }




    //endregion
}
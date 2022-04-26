package fun.madeby.gdpetclinic.controllers;

import fun.madeby.gdpetclinic.model.Owner;
import fun.madeby.gdpetclinic.model.Pet;
import fun.madeby.gdpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    void ownerList_Owners() throws Exception {
        //given ownerSet exists

        when(ownerService.findAll())
                .thenReturn(ownerSet);

        //..then
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void ownerList_Index() throws Exception {
        //given ownerSet exists

        when(ownerService.findAll())
                .thenReturn(ownerSet);

        //..then
        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        // given endpoint not yet implemented

        // when endpoint call || then
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("not-implemented"));

        verifyNoInteractions(ownerService);

    }

    @Test
    void testGetOwner() throws Exception {
        //given
        when(ownerService.findById(anyLong())).thenReturn(owner1);

        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", hasProperty("id", is(owner1Id))));

    }
}
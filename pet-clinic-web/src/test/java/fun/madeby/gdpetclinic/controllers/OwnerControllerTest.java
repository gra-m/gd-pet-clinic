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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController controllerUnderTest;
    // for comparison if poss to get to returned objs
    Owner owner1;
    Set<Owner> ownerSet;
    // My business logic insists on petSet, can be empty
    Set<Pet> petSet;
    // Test endpoints, standalone == less resource
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ownerSet = new HashSet<>();
        //(same petset poss issue if H2)
        owner1 = Owner.builder().id(1L).pets(petSet).build();
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
        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/index"))
                .andExpect(MockMvcResultMatchers.model().attribute("owners", hasSize(2)));
    }

    @Test
    void ownerList_Index() throws Exception {
        //given ownerSet exists

        when(ownerService.findAll())
                .thenReturn(ownerSet);

        //..then
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/index"))
                .andExpect(MockMvcResultMatchers.model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        // given endpoint not yet implemented

        // when endpoint call || then
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("not-implemented"));

        verifyNoInteractions(ownerService);

    }
}
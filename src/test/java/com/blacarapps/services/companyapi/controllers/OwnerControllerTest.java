package com.blacarapps.services.companyapi.controllers;

import com.blacarapps.services.companyapi.entities.Owner;
import com.blacarapps.services.companyapi.repositories.OwnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.Arrays;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(OwnerController.class)
public final class OwnerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OwnerRepository owners;

    @Test
    public void whenSaveThenReturnJson() throws Exception {
        // given
        final String name = "owner1";
        final Owner owner = this.mockOwner(name);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(owner);
        //when
        this.mockSave(owner);
        //then
        mvc.perform(
            MockMvcRequestBuilders.post("/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.jsonPath(
                    "$.name", Matchers.is(name)
                )
            );
    }

    @Test
    public void whenGetAllThenReturnJsonArray() throws Exception {
        // given
        final String name = "owner1";
        //when
        this.mockFindAll(name);
        //then
        mvc.perform(MockMvcRequestBuilders.get("/owner")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1))
            )
            .andExpect(
                MockMvcResultMatchers.jsonPath(
                    "$[0].name", Matchers.is(name)
                )
            );
    }

    @Test
    public void whenGetOneThenReturnJson() throws Exception {
        // given
        final String name = "owner1";
        //when
        this.mockFindOne(name);
        //then
        mvc.perform(MockMvcRequestBuilders.get("/owner/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.jsonPath(
                    "$.name", Matchers.is(name)
                )
            );
    }

    private void mockFindOne(final String name) {
        final Owner owner = this.mockOwner(name);
        owner.setId(1L);
        given(owners.findById(Mockito.anyLong()))
            .willReturn(Optional.of(owner));
    }

    private void mockSave(final Owner owner) {
        given(owners.save(Mockito.any(Owner.class)))
            .willReturn(owner);
    }

    private void mockFindAll(final String name) {
        given(owners.findAll())
            .willReturn(Arrays.asList(this.mockOwner(name)));
    }

    private Owner mockOwner(final String name) {
        return new Owner(name);
    }
}

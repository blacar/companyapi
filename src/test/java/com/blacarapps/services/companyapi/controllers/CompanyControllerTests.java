package com.blacarapps.services.companyapi.controllers;

import com.blacarapps.services.companyapi.entities.Company;
import com.blacarapps.services.companyapi.repositories.CompanyRepository;
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
@WebMvcTest(CompanyController.class)
public final class CompanyControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyRepository companies;

    @Test
    public void whenGetAllThenReturnJsonArray() throws Exception {
        // given
        final String name = "Company1";
        //when
        this.mockFindAll(name);
        //then
        mvc.perform(MockMvcRequestBuilders.get("/company")
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
        final String name = "Company1";
        //when
        this.mockFindOne(name);
        //then
        mvc.perform(MockMvcRequestBuilders.get("/company")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.jsonPath(
                    "$.name", Matchers.is(name)
                )
            );
    }

    private void mockFindOne(final String name) {
        given(companies.findById(Mockito.anyLong()))
            .willReturn(Optional.of(this.mockCompany(name)));
    }

    private void mockFindAll(final String name) {
        given(companies.findAll())
            .willReturn(Arrays.asList(this.mockCompany(name)));
    }

    private Company mockCompany(final String name) {
        return new Company(
            name, "Madrid", "Spain", "company@test.com", "0034567567"
        );
    }
}

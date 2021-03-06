package com.blacarapps.services.companyapi.controllers;

import com.blacarapps.services.companyapi.entities.Company;
import com.blacarapps.services.companyapi.repositories.CompanyRepository;
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
@WebMvcTest(CompanyController.class)
public final class CompanyControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyRepository companies;

    @MockBean
    private OwnerRepository owners;

    @Test
    public void whenSaveThenReturnJson() throws Exception {
        // given
        final String name = "Company1";
        final Company company = this.mockCompany(name);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(company);
        //when
        this.mockSave(company);
        //then
        mvc.perform(
            MockMvcRequestBuilders.post("/company")
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
        mvc.perform(MockMvcRequestBuilders.get("/company/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.jsonPath(
                    "$.name", Matchers.is(name)
                )
            );
    }

    private void mockFindOne(final String name) {
        final Company company = this.mockCompany(name);
        company.setId(1L);
        given(companies.findById(Mockito.anyLong()))
            .willReturn(Optional.of(company));
    }

    private void mockSave(final Company company) {
        given(companies.save(Mockito.any(Company.class)))
            .willReturn(company);
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

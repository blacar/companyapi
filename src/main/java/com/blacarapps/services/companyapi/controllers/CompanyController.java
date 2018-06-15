package com.blacarapps.services.companyapi.controllers;

import com.blacarapps.services.companyapi.entities.Company;
import com.blacarapps.services.companyapi.entities.Owner;
import com.blacarapps.services.companyapi.exceptions.ResourceNotFoundException;
import com.blacarapps.services.companyapi.repositories.CompanyRepository;
import com.blacarapps.services.companyapi.repositories.OwnerRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CompanyController {

    @Autowired
    private CompanyRepository companies;

    @Autowired
    private OwnerRepository owners;

    @RequestMapping(
        value = "/company",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Company save(@Valid @RequestBody final Company company) {
        return this.companies.save(company);
    }

    @RequestMapping(
        value = "/company",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Company> getAll() {
        return this.companies.findAll();
    }

    @RequestMapping(
        value = "/company/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Company getOne(@PathVariable("id") final Long id) {
        return this.companies.findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Company", "id", id)
            );
    }

    @RequestMapping(
        value = "/company/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Company update(
        @PathVariable("id") final Long id,
        @Valid @RequestBody final Company company
    ) {
        return this.companies.findById(id)
            .map(actual -> this.companies.save(actual.update(company)))
            .orElseThrow(
                () -> new ResourceNotFoundException("Company", "id", id)
            );
    }

    @RequestMapping(
        value = "/company/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public void delete(@PathVariable("id") final Long id) {
        final Company actual = this.companies.findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Company", "id", id)
            );
        this.companies.delete(actual);
    }

    @RequestMapping(
        value = "/company/{id}/owner/{ownerid}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Company addOwner(
        @PathVariable("id") final Long id,
        @PathVariable("ownerid") final Long ownerid
    ) {
        final Owner owner = this.owners.findById(ownerid).orElseThrow(
            () -> new ResourceNotFoundException("Owner", "id", ownerid)
        );
        final Company actual = this.companies.findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Company", "id", id)
            );
        actual.getOwners().add(owner);
        return this.companies.save(actual);
    }
}

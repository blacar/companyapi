package com.blacarapps.services.companyapi.controllers;

import com.blacarapps.services.companyapi.entities.Owner;
import com.blacarapps.services.companyapi.exceptions.ResourceNotFoundException;
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
public final class OwnerController {

    @Autowired
    private OwnerRepository owners;

    @RequestMapping(
        value = "/owner",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Owner save(@Valid @RequestBody final Owner owner) {
        return this.owners.save(owner);
    }

    @RequestMapping(
        value = "/owner",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Owner> getAll() {
        return this.owners.findAll();
    }

    @RequestMapping(
        value = "/owner/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Owner getOne(@PathVariable("id") final Long id) {
        return this.owners.findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Owner", "id", id)
            );
    }

    @RequestMapping(
        value = "/owner/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Owner update(
        @PathVariable("id") final Long id,
        @Valid @RequestBody final Owner owner
    ) {
        return this.owners.findById(id)
            .map(actual -> this.owners.save(actual.update(owner)))
            .orElseThrow(
                () -> new ResourceNotFoundException("Owner", "id", id)
            );
    }

    @RequestMapping(
        value = "/owner/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public void delete(@PathVariable("id") final Long id) {
        final Owner actual = this.owners.findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Owner", "id", id)
            );
        this.owners.delete(actual);
    }
}

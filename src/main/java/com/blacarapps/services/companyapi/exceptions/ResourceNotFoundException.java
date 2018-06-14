package com.blacarapps.services.companyapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resource;
    private String field;
    private Object value;

    public ResourceNotFoundException(
        final String resource,
        final String field,
        final Object value
    ) {
        super(
            String.format(
                "%s not found with %s : '%s'",
                resource,
                field,
                value
            )
        );
        this.resource = resource;
        this.field = field;
        this.value = value;
    }

    public String getResource() {
        return resource;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}

package com.blacarapps.services.companyapi.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Company Owner entity.
 */
@Entity
public final class Owner implements Serializable {

    private static final long serialVersionUID = 8136948412307273470L;

    private Long id;

    private String name;

    public Owner() {   }

    public Owner(final String nameparam) {
        this.name = nameparam;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(final Long idparam) {
        this.id = idparam;
    }

    public String getName() {
        return name;
    }

    public void setName(final String nameparam) {
        this.name = nameparam;
    }

    public Owner update(final Owner ownerparam) {
        this.name = ownerparam.name;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Owner{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

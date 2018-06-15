package com.blacarapps.services.companyapi.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Company Entity
 * @a
 */
@Entity
public final class Company implements Serializable {

    private static final long serialVersionUID = 346676342697864281L;

    private Long id;

    private String name;

    private String city;

    private String country;

    private String mail;

    private String phone;

    private Set<Owner> owners = new HashSet<>(0);

    public Company() {
    }

    public Company(
        final String nameparam,
        final String cityparam,
        final String countryparam,
        final String mailparam,
        final String phoneparam
    ) {
        this.name = nameparam;
        this.city = cityparam;
        this.country = countryparam;
        this.mail = mailparam;
        this.phone = phoneparam;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(final Long idparam) {
        this.id = idparam;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(final String nameparam) {
        this.name = nameparam;
    }

    @NotBlank
    public String getCity() {
        return city;
    }

    public void setCity(final String cityparam) {
        this.city = cityparam;
    }

    @NotBlank
    public String getCountry() {
        return country;
    }

    public void setCountry(final String countryparam) {
        this.country = countryparam;
    }

    @Email
    public String getMail() {
        return mail;
    }

    public void setMail(final String mailparam) {
        this.mail = mailparam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phoneparam) {
        this.phone = phoneparam;
    }

    @OneToMany(
        fetch = FetchType.EAGER, cascade = CascadeType.ALL
    )
    @JoinColumn(referencedColumnName = "id", name = "company_id")
    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(final Set<Owner> ownersparam) {
        this.owners = ownersparam;
    }

    public Company update(final Company company) {
        this.name = company.name;
        this.city = company.city;
        this.country = company.country;
        this.mail = company.mail;
        this.phone = company.phone;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Company{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", owners=").append(owners);
        sb.append('}');
        return sb.toString();
    }
}

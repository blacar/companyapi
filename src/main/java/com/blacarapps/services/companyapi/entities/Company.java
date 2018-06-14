package com.blacarapps.services.companyapi.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public final class Company implements Serializable {

    private Long id;

    private String name;

    private String city;

    private String country;

    private String mail;

    private String phone;

    private Set<Owner> owners = new HashSet<>(0);

    public Company(
        final String name,
        final String city,
        final String country,
        final String mail,
        final String phone
    ) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.mail = mail;
        this.phone = phone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @NotBlank
    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    @NotBlank
    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    @Email
    public String getMail() {
        return mail;
    }

    public void setMail(final String mail) {
        this.mail = mail;
    }

    @NotBlank
    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(final Set<Owner> owners) {
        this.owners = owners;
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

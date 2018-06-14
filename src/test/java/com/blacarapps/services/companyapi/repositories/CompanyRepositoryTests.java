package com.blacarapps.services.companyapi.repositories;

import com.blacarapps.services.companyapi.entities.Company;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void whenFindByNameThenReturnCompany() {
        final String name = "Company1";
        // given
        Company company = new Company(
            name, "Madrid", "Spain", "company@test.com", "0034567567"
        );
        entityManager.persist(company);
        entityManager.flush();

        // when
        Company found = companyRepository.findByName(company.getName());

        // then
        Assert.assertTrue(found.getName().equals(name));
    }

    @Test(expected=ConstraintViolationException.class)
    public void whenWrongEmailThenReturnError() {
        final String name = "Company1";
        // given
        Company company = new Company(
            name, "Madrid", "Spain", "company", "0034567567"
        );
        entityManager.persist(company);
        entityManager.flush();

        // when
        Company found = companyRepository.findByName(company.getName());

        // then
        Assert.assertTrue(found.getName().equals(name));
    }

    @Test(expected=ConstraintViolationException.class)
    public void whenMissingMandatoryAttributeThenReturnError() {
        final String name = "Company1";
        // given
        Company company = new Company(
            name, "Madrid", "Spain", "company", "0034567567"
        );
        company.setCountry(null);
        entityManager.persist(company);
        entityManager.flush();

        // when
        Company found = companyRepository.findByName(company.getName());

        // then
        Assert.assertTrue(found.getName().equals(name));
    }

}

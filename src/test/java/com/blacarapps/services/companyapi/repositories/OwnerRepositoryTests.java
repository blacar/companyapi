package com.blacarapps.services.companyapi.repositories;

import com.blacarapps.services.companyapi.entities.Owner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OwnerRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OwnerRepository owners;

    @Autowired
    private CompanyRepository companies;

    @Test
    public void whenFindByNameThenReturnOwner() {
        // given
        final String name = "Owner1";
        Owner owner = new Owner(name);
        entityManager.persist(owner);
        entityManager.flush();

        // when
        Owner found = owners.findByName(owner.getName());

        // then
        Assert.assertTrue(found.getName().equals(name));
    }
}

package com.blacarapps.services.companyapi.repositories;

import com.blacarapps.services.companyapi.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(final String name);
}

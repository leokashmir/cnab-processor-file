package com.cnab.processor.repository;

import com.cnab.processor.model.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface  CompanyRepository extends JpaRepository<Company,Long> {
    Company findByCompanyId(String companyId);
}

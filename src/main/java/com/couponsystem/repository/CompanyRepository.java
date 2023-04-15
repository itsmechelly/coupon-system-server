package com.couponsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findCompanyById(int id);

    Company findCompanyByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    boolean existsByNameIgnoreCase(String name);
}

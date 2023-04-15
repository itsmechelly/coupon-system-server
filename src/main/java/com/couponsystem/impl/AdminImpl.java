package com.couponsystem.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Customer;
import com.couponsystem.repository.CompanyRepository;
import com.couponsystem.repository.CustomerRepository;

@Repository
public class AdminImpl {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AdminImpl(CompanyRepository companyRepository, CustomerRepository customerRepository) {
        super();
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
    }

//	------------------------------------------------------------------------------------------------------------

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Company company) {
        return companyRepository.saveAndFlush(company);
    }

    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }

    public Company findCompanyById(int id) {
        return companyRepository.findCompanyById(id);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public boolean companyExistsById(int id) {
        return companyRepository.existsById(id);
    }

    public boolean companyExistsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }

    public boolean companyExistsByNameIgnoreCase(String name) {
        return companyRepository.existsByNameIgnoreCase(name);
    }

//	------------------------------------------------------------------------------------------------------------

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    public Customer findCustomerById(int id) {
        return customerRepository.findCustomerById(id);
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public boolean customerExistsById(int id) {
        return customerRepository.existsById(id);
    }

    public boolean customerExistsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}

package com.couponsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Customer;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.impl.AdminImpl;

@Service
public class AdminService extends ClientService {
    private AdminImpl adminImpl;

    @Autowired
    public AdminService(AdminImpl adminImpl) {
        super();
        this.adminImpl = adminImpl;
    }

//	------------------------------------------------------------------------------------------------------------

    @Override
    public boolean login(String email, String password) {
        if (email.equalsIgnoreCase("admin@admin.com") && password.equals("admin")) {
            return true;
        }
        return false;
    }

//	------------------------------------------------------------------------------------------------------------

    public Company addCompany(Company company) throws AlreadyExistException, LogException {

        if (adminImpl.companyExistsByEmail(company.getEmail()))
            throw new AlreadyExistException("Company email ", company.getEmail());
        if (adminImpl.companyExistsByNameIgnoreCase(company.getName()))
            throw new AlreadyExistException("Company name ", company.getName());

        return adminImpl.addCompany(company);
    }

    public Company updateCompany(Company company) throws NotFoundException, NotAllowedException, LogException {

        Company compFromDb = adminImpl.findCompanyById(company.getId());

        if (compFromDb == null)
            throw new NotFoundException("company details.");
        if (!company.getName().equalsIgnoreCase(compFromDb.getName()) && company.getId() != compFromDb.getId())
            throw new NotAllowedException("company name to", company.getName());

        return adminImpl.updateCompany(company);
    }

    public String deleteCompany(int companyId) throws NotFoundException, LogException {

        if (!adminImpl.companyExistsById(companyId))
            throw new NotFoundException("company details.");

        adminImpl.deleteCompany(companyId);
        return "Request accomplished, company with id number " + companyId + " has been deleted from the system.";
    }

    public Company getOneCompanyById(int companyId) throws NotFoundException, LogException {

        if (!adminImpl.companyExistsById(companyId))
            throw new NotFoundException("company details.");

        return adminImpl.findCompanyById(companyId);
    }

    public List<Company> getAllCompanies() throws NotFoundException, LogException {

        Optional<List<Company>> compFromDb = Optional.of(adminImpl.findAllCompanies());

        if (compFromDb.isEmpty())
            throw new NotFoundException("companies details.");

        return adminImpl.findAllCompanies();
    }

    public Customer addCustomer(Customer customer) throws AlreadyExistException, LogException {

        if (adminImpl.customerExistsByEmail(customer.getEmail()))
            throw new AlreadyExistException("Customer email ", customer.getEmail());

        return adminImpl.addCustomer(customer);
    }

    public Customer updateCustomer(Customer customer) throws NotFoundException, LogException {

        Customer custFromDb = adminImpl.findCustomerById(customer.getId());

        if (custFromDb == null)
            throw new NotFoundException("customer details.");

        return adminImpl.updateCustomer(customer);
    }

    public String deleteCustomer(int customerId) throws NotFoundException, LogException {

        if (!adminImpl.customerExistsById(customerId))
            throw new NotFoundException("customer details.");

        adminImpl.deleteCustomer(customerId);
        return "Request accomplished, customer with id number " + customerId + " has been deleted from the system.";
    }

    public Customer getOneCustomerById(int customerId) throws NotFoundException, LogException {

        if (!adminImpl.customerExistsById(customerId))
            throw new NotFoundException("customer details.");

        return adminImpl.findCustomerById(customerId);
    }

    public List<Customer> getAllCustomers() throws NotFoundException, LogException {

        Optional<List<Customer>> custListFromDb = Optional.of(adminImpl.findAllCustomers());

        if (custListFromDb.isEmpty())
            throw new NotFoundException("customers details.");

        return adminImpl.findAllCustomers();
    }
}

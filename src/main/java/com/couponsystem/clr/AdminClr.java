package com.couponsystem.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Customer;
import com.couponsystem.beans.LoginForm;
import com.couponsystem.enums.ClientType;
import com.couponsystem.controller.AdminController;
import com.couponsystem.security.LoginController;
import com.couponsystem.service.LoginService;
import com.couponsystem.utils.ClrUtils;

@Component
@Order(1)
public class AdminClr implements CommandLineRunner {
    private LoginController loginController;
    private LoginService loginService;
    private AdminController adminController;

    @Autowired
    public AdminClr(LoginController loginController, LoginService loginService, AdminController adminController) {
        super();
        this.loginController = loginController;
        this.loginService = loginService;
        this.adminController = adminController;
    }

    @Override
    public void run(String... args) throws Exception {

//	  _  
//  _( )_
// (_ o _)
//  (_,_)
//	     ___      _           _        ______          _     _____         _       
//	    / _ \    | |         (_)       | ___ \        | |   |_   _|       | |      
//	   / /_\ \ __| |_ __ ___  _ _ __   | |_/ /___  ___| |_    | | ___  ___| |_ ___ 
//	   |  _  |/ _` | '_ ` _ \| | '_ \  |    // _ \/ __| __|   | |/ _ \/ __| __/ __|
//	   | | | | (_| | | | | | | | | | | | |\ \  __/\__ \ |_    | |  __/\__ \ |_\__ \
//	   \_| |_/\__,_|_| |_| |_|_|_| |_| \_| \_\___||___/\__|   \_/\___||___/\__|___/

        ClrUtils.adminRestTests();

//		------------------------------------------------------------------------------------------------------------

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Testing Admin Login:");

        System.out.println("Going to test login exception - *WRONG* *Email*:");
        LoginForm badEmailLoginForm = new LoginForm("BADadmin@BADadmin.com", "admin", ClientType.ADMIN);
        System.out.println(loginController.login(badEmailLoginForm));

        System.out.println();
        System.out.println("Going to test login exception - *WRONG* *Password*:");
        LoginForm badPasswordLoginForm = new LoginForm("admin@admin.com", "nimda", ClientType.ADMIN);
        System.out.println(loginController.login(badPasswordLoginForm));

        System.out.println();
        System.out.println("Going to test GOOD admin login:");
        LoginForm goodLoginForm = new LoginForm("admin@admin.com", "admin", ClientType.ADMIN);
        System.out.println(loginController.login(goodLoginForm));
        String token = loginService.getTokenForClr();


//		ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test Admin Logout:");

//		------------------------------------------------------------------------------------------------------------

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test adminController.addCompany:");

        Company comp1 = new Company();
        comp1.setName("Zoot Allures");
        comp1.setEmail("zootAllures@company.com");
        comp1.setPassword("zootAllures");

        Company comp2 = new Company();
        comp2.setName("KSP");
        comp2.setEmail("KSP@company.com");
        comp2.setPassword("KSPP");

        Company comp3 = new Company();
        comp3.setName("Issta");
        comp3.setEmail("Issta333@company.com");
        comp3.setPassword("Issta333");

        Company comp4 = new Company();
        comp4.setName("compName4");
        comp4.setEmail("comp4Email@comp.com");
        comp4.setPassword("4444");

        Company comp5 = new Company();
        comp5.setName("compName5");
        comp5.setEmail("comp5Email@comp.com");
        comp5.setPassword("5555");

        System.out.println(adminController.addCompany(comp1, token));
        System.out.println(adminController.addCompany(comp2, token));
        System.out.println(adminController.addCompany(comp3, token));
        System.out.println(adminController.addCompany(comp4, token));
        System.out.println(adminController.addCompany(comp5, token));

        ClrUtils.testSeparatedLine(
                " --------->>>>>>>> Going to test adminController.addCompany *BAD REQUEST* (cannot add if companyEmail already exist):");

        Company comp55 = new Company();
        comp55.setName("compName55");
        comp55.setEmail("comp5Email@comp.com");
        comp55.setPassword("55555555");

        System.out.println(adminController.addCompany(comp55, token));

        ClrUtils.testSeparatedLine(
                " --------->>>>>>>> Going to test adminController.addCompany *BAD REQUEST* (cannot add companyName if exist):");

        Company comp44 = new Company();
        comp44.setName("compName4");
        comp44.setEmail("comp44Email@comp.com");
        comp44.setPassword("44444444");

        System.out.println(adminController.addCompany(comp44, token));

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test adminController.updateCompany:");

        comp3.setEmail("Issta@company.com");
        comp3.setPassword("Issta");

        System.out.println(adminController.updateCompany(comp3, token));

        ClrUtils.testSeparatedLine(
                " --------->>>>>>>> Going to test adminController.updateCompany *BAD REQUEST*(cannot update companyName):");

        comp3.setName("Issta");
        System.out.println(adminController.updateCompany(comp3, token));

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test adminController.deleteCompany:");

        System.out.println(adminController.deleteCompany(5, token));

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test *bad request* for adminController.deleteCompany:");

        System.out.println(adminController.deleteCompany(55, token));

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test adminController.getOneCompanyById:");

        System.out.println(adminController.getOneCompanyById(1, token));

        ClrUtils.testSeparatedLine(
                " --------->>>>>>>> Going to test *BAD REQUEST* for adminController.getOneCompanyById: (company not exist)");

        System.out.println(adminController.getOneCompanyById(8, token));

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test adminController.getAllCompanies:");

        System.out.println(adminController.getAllCompanies(token));

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test adminController.addCustomer:");

        Customer cust1 = new Customer();
        cust1.setFirstName("cust1FirstName");
        cust1.setLastName("cust1LastName");
        cust1.setEmail("cust1@cust.com");
        cust1.setPassword("1111");

        Customer cust2 = new Customer();
        cust2.setFirstName("cust2FirstName");
        cust2.setLastName("cust2LastName");
        cust2.setEmail("cust2@cust.com");
        cust2.setPassword("2222");

        Customer cust3 = new Customer();
        cust3.setFirstName("cust33FirstName");
        cust3.setLastName("cust33LastName");
        cust3.setEmail("cust33@cust.com");
        cust3.setPassword("33333333");

        Customer cust4 = new Customer();
        cust4.setFirstName("cust4FirstName");
        cust4.setLastName("cust4LastName");
        cust4.setEmail("cust4@cust.com");
        cust4.setPassword("4444");

        Customer cust5 = new Customer();
        cust5.setFirstName("cust5FirstName");
        cust5.setLastName("cust5LastName");
        cust5.setEmail("cust5@cust.com");
        cust5.setPassword("5555");

        System.out.println(adminController.addCustomer(cust1, token));
        System.out.println(adminController.addCustomer(cust2, token));
        System.out.println(adminController.addCustomer(cust3, token));
        System.out.println(adminController.addCustomer(cust4, token));
        System.out.println(adminController.addCustomer(cust5, token));

        ClrUtils.testSeparatedLine(
                " --------->>>>>>>> Going to test adminController.addCustomer *BAD REQUEST*(cannot add if customerEmail allready exist):");

        Customer cust55 = new Customer();
        cust55.setFirstName("cust55FirstName");
        cust55.setLastName("cust55LastName");
        cust55.setEmail("cust5@cust.com");
        cust55.setPassword("55555555");

        System.out.println(adminController.addCustomer(cust55, token));

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test adminController.updateCustomer:");

        cust3.setFirstName("cust3FirstName");
        cust3.setLastName("cust3LastName");
        cust3.setEmail("cust3@cust.com");
        cust3.setPassword("3333");

        System.out.println(adminController.updateCustomer(cust3, token));

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test adminController.deleteCustomer:");

        System.out.println(adminController.deleteCustomer(5, token));

        ClrUtils.testSeparatedLine(
                " --------->>>>>>>> Going to test *bad request* for adminController.deleteCustomer: (customer not exsist)");

        System.out.println(adminController.deleteCustomer(55, token));

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test adminController.getOneCustomerById:");

        System.out.println(adminController.getOneCustomerById(1, token));

        ClrUtils.testSeparatedLine(" --------->>>>>>>> Going to test adminController.getAllCustomers:");

        System.out.println(adminController.getAllCustomers(token));
    }
}

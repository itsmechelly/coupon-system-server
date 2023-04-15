package com.couponsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.couponsystem.beans.LoginForm;
import com.couponsystem.beans.LoginResponse;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.security.JwtUtil;

@Service
@Lazy
public class LoginService {
    public ApplicationContext ctx;
    //TODO - the token variable below is only for CLR testing, not for production!
    public String tokenForClr;
    private JwtUtil jwtUtil;
    private AdminService adminService;
    private CompanyService companyService;
    private CustomerService customerService;

    @Autowired
    public LoginService(ApplicationContext ctx, String tokenForClr, JwtUtil jwtUtil, AdminService adminService, CompanyService companyService,
                        CustomerService customerService) {
        super();
        this.ctx = ctx;
        this.tokenForClr = tokenForClr;
        this.jwtUtil = jwtUtil;
        this.adminService = adminService;
        this.companyService = companyService;
        this.customerService = customerService;
    }

    public String getTokenForClr() {
        return tokenForClr;
    }

    public LoginResponse login(LoginForm loginForm) throws LogException, NotFoundException {

        switch (loginForm.getClientType()) {

            case ADMIN:

                if (adminService.login(loginForm.getEmail(), loginForm.getPassword())) {

                    String jwtToken = jwtUtil.generateToken(loginForm.getClientType().toString(), 0, "Admin", "admin@admin.com");

                    LoginResponse loginResponse = new LoginResponse();
                    loginResponse.setClientType(loginForm.getClientType().toString());
                    loginResponse.setClientName("Admin");
                    loginResponse.setToken(jwtToken);

                    //TODO - the token variable below is only for CLR testing, not for production!
                    tokenForClr = jwtToken;

                    return loginResponse;
                }

            case COMPANY:

                companyService = ctx.getBean(CompanyService.class);
                if (companyService.login(loginForm.getEmail(), loginForm.getPassword())) {

                    int companyId = companyService.findCompanyIdByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
                    String clientName = companyService.findCompanyNameByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());

                    String jwtToken = jwtUtil.generateToken(loginForm.getClientType().toString(),
                            companyId, clientName, loginForm.getEmail());

                    LoginResponse loginResponse = new LoginResponse();
                    loginResponse.setClientType(loginForm.getClientType().toString());
                    loginResponse.setClientName(clientName);
                    loginResponse.setToken(jwtToken);

                    //TODO - the token variable below is only for CLR testing, not for production!
                    tokenForClr = jwtToken;

                    return loginResponse;
                }

            case CUSTOMER:

                customerService = ctx.getBean(CustomerService.class);
                if (customerService.login(loginForm.getEmail(), loginForm.getPassword())) {

                    int customerId = customerService.findCustomerIdByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
                    String clientName = customerService.findCustomerFirstNameByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());

                    String jwtToken = jwtUtil.generateToken(loginForm.getClientType().toString(), customerId, clientName, loginForm.getEmail());

                    LoginResponse loginResponse = new LoginResponse();
                    loginResponse.setClientType(loginForm.getClientType().toString());
                    loginResponse.setClientName(clientName);
                    loginResponse.setToken(jwtToken);

                    //TODO - the token variable below is only for CLR testing, not for production!
                    tokenForClr = jwtToken;

                    return loginResponse;
                }

            default:
                throw new LogException();
        }
    }
}

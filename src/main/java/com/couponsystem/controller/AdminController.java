package com.couponsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Customer;
import com.couponsystem.enums.ClientType;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.CouponSystemException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.service.AdminService;
import com.couponsystem.security.JwtUtil;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AdminController(AdminService adminService, JwtUtil jwtUtil) {
        super();
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
    }

//	------------------------------------------------------------------------------------------------------------

    @PostMapping("/addCompany")
    public ResponseEntity<?> addCompany(@RequestBody Company company, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.ADMIN.toString());
            return ResponseEntity.ok(adminService.addCompany(company));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (AlreadyExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCompany")
    public ResponseEntity<?> updateCompany(@RequestBody Company company, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.ADMIN.toString());
            return ResponseEntity.ok(adminService.updateCompany(company));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NotAllowedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteCompany/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable int companyId, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.ADMIN.toString());
            return ResponseEntity.ok(adminService.deleteCompany(companyId));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getOneCompanyById/{companyId}")
    public ResponseEntity<?> getOneCompanyById(@PathVariable int companyId, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.ADMIN.toString());
            return ResponseEntity.ok(adminService.getOneCompanyById(companyId));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.ADMIN.toString());
            return ResponseEntity.ok(adminService.getAllCompanies());
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.ADMIN.toString());
            return ResponseEntity.ok(adminService.addCustomer(customer));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (AlreadyExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.ADMIN.toString());
            return ResponseEntity.ok(adminService.updateCustomer(customer));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int customerId, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.ADMIN.toString());
            return ResponseEntity.ok(adminService.deleteCustomer(customerId));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getOneCustomerById/{customerId}")
    public ResponseEntity<?> getOneCustomerById(@PathVariable int customerId, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.ADMIN.toString());
            return ResponseEntity.ok(adminService.getOneCustomerById(customerId));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "CouponSystem_Header") String token)
            throws CouponSystemException {
        try {
            jwtUtil.validateService(token, ClientType.ADMIN.toString());
            return ResponseEntity.ok(adminService.getAllCustomers());
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

package com.couponsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.enums.ClientType;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.exceptions.PurchaseCouponException;
import com.couponsystem.service.CustomerService;
import com.couponsystem.security.JwtUtil;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {
    private final CustomerService customerService;
    private final JwtUtil jwtUtil;

    @Autowired
    public CustomerController(CustomerService customerService, JwtUtil jwtUtil) {
        super();
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

//	------------------------------------------------------------------------------------------------------------

    @PostMapping("/purchaseCoupon/{couponId}")
    public ResponseEntity<?> purchaseCoupon(@PathVariable int couponId, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.CUSTOMER.toString());
            customerService.setCustomerId(jwtUtil.extractId(token));
            return ResponseEntity.ok(customerService.purchaseCoupon(couponId));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (PurchaseCouponException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getOneCouponById/{couponId}")
    public ResponseEntity<?> getOneCouponById(@PathVariable int couponId, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.CUSTOMER.toString());
            return ResponseEntity.ok(customerService.getOneCouponById(couponId));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCoupons")
    public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.CUSTOMER.toString());
            customerService.setCustomerId(jwtUtil.extractId(token));
            return ResponseEntity.ok(customerService.getAllCoupons());
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCustomerCoupons")
    public ResponseEntity<?> getAllCustomerCoupons(@RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.CUSTOMER.toString());
            customerService.setCustomerId(jwtUtil.extractId(token));
            return ResponseEntity.ok(customerService.getAllCouponsByCustomerId());
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCouponsByCategory/{couponCategory}")
    public ResponseEntity<?> getAllCouponsByCategory(@PathVariable CouponCategory couponCategory, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.CUSTOMER.toString());
            customerService.setCustomerId(jwtUtil.extractId(token));
            return ResponseEntity.ok(customerService.getAllCouponsByCategory(couponCategory));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCouponsUnderMaxPrice/{maxPrice}")
    public ResponseEntity<?> getAllCouponsUnderMaxPrice(@PathVariable double maxPrice, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.CUSTOMER.toString());
            customerService.setCustomerId(jwtUtil.extractId(token));
            return ResponseEntity.ok(customerService.getAllCouponsUnderMaxPrice(maxPrice));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCustomerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.CUSTOMER.toString());
            customerService.setCustomerId(jwtUtil.extractId(token));
            return ResponseEntity.ok(customerService.getCustomerDetails());
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

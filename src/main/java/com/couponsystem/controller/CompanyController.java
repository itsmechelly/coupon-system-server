package com.couponsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.couponsystem.beans.Coupon;
import com.couponsystem.enums.ClientType;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.service.CompanyService;
import com.couponsystem.security.JwtUtil;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyController {
    private final CompanyService companyService;
    private final JwtUtil jwtUtil;

    @Autowired
    public CompanyController(CompanyService companyService, JwtUtil jwtUtil) {
        super();
        this.companyService = companyService;
        this.jwtUtil = jwtUtil;
    }

//	------------------------------------------------------------------------------------------------------------

    @PostMapping("/addCompanyCoupon")
    public ResponseEntity<?> addCompanyCoupon(@ModelAttribute Coupon coupon, @RequestParam MultipartFile imageFile, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.COMPANY.toString());
            companyService.setCompanyId(jwtUtil.extractId(token));
            return ResponseEntity.ok(companyService.addCoupon(coupon, imageFile));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (AlreadyExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCompanyCoupon")
    public ResponseEntity<?> updateCompanyCoupon(@ModelAttribute Coupon coupon, @RequestParam(required = false) MultipartFile imageFile, @RequestHeader(name = "CouponSystem_Header") String token) throws AlreadyExistException {

        try {
            jwtUtil.validateService(token, ClientType.COMPANY.toString());
            companyService.setCompanyId(jwtUtil.extractId(token));
            return ResponseEntity.ok(companyService.updateCoupon(coupon, imageFile));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NotAllowedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/deleteCompanyCoupon/{couponId}")
    public ResponseEntity<?> deleteCompanyCoupon(@PathVariable int couponId, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.COMPANY.toString());
            companyService.setCompanyId(jwtUtil.extractId(token));
            return ResponseEntity.ok(companyService.deleteCoupon(couponId));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCompaniesCoupons")
    public ResponseEntity<?> getAllCompaniesCoupons(@RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.COMPANY.toString());
            companyService.setCompanyId(jwtUtil.extractId(token));
            return ResponseEntity.ok(companyService.getAllCoupons());
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCouponsByCategory/{couponCategory}")
    public ResponseEntity<?> getAllCouponsByCategory(@PathVariable CouponCategory couponCategory, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.COMPANY.toString());
            companyService.setCompanyId(jwtUtil.extractId(token));
            return ResponseEntity.ok(companyService.getAllCouponsByCategory(couponCategory));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCouponsUnderMaxPrice/{maxPrice}")
    public ResponseEntity<?> getAllCouponsUnderMaxPrice(@PathVariable double maxPrice, @RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.COMPANY.toString());
            companyService.setCompanyId(jwtUtil.extractId(token));
            return ResponseEntity.ok(companyService.getAllCouponsUnderMaxPrice(maxPrice));
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCompanyDetails")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "CouponSystem_Header") String token) {

        try {
            jwtUtil.validateService(token, ClientType.COMPANY.toString());
            companyService.setCompanyId(jwtUtil.extractId(token));
            return ResponseEntity.ok(companyService.getCompanyDetails());
        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

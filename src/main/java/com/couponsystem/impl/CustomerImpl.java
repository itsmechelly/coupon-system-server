package com.couponsystem.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.Customer;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.repository.CouponRepository;
import com.couponsystem.repository.CustomerRepository;

@Repository
public class CustomerImpl {
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;

    @Autowired
    public CustomerImpl(CustomerRepository customerRepository, CouponRepository couponRepository) {
        super();
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
    }

//	------------------------------------------------------------------------------------------------------------

    public Customer updateCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    public Customer findCustomerById(int id) {
        return customerRepository.findCustomerById(id);
    }

    public Customer findCustomerByEmailAndPassword(String email, String password) {
        return customerRepository.findCustomerByEmailAndPassword(email, password);
    }

//	------------------------------------------------------------------------------------------------------------


    public Coupon findCouponById(int id) {
        return couponRepository.findById(id);
    }

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public List<Coupon> getCouponsByCustomersId(int id) {
        return couponRepository.getCouponsByCustomersId(id);
    }

    public List<Coupon> getCouponsByCustomersIdAndCategory(int id, CouponCategory category) {
        return couponRepository.getCouponsByCustomersIdAndCategory(id, category);
    }

    public List<Coupon> getCouponsByCustomersIdAndPriceLessThan(int id, double price) {
        return couponRepository.getCouponsByCustomersIdAndPriceLessThan(id, price);
    }

    public boolean couponExistsById(int id) {
        return couponRepository.existsById(id);
    }

    public boolean couponExistsByCustomersIdAndTitle(int id, String title) {
        return couponRepository.existsByCustomersIdAndTitle(id, title);
    }
}

package com.couponsystem.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Coupon;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.repository.CompanyRepository;
import com.couponsystem.repository.CouponRepository;

@Repository
public class CompanyImpl {
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;

    @Autowired
    public CompanyImpl(CompanyRepository companyRepository, CouponRepository couponRepository) {
        super();
        this.companyRepository = companyRepository;
        this.couponRepository = couponRepository;
    }

//		------------------------------------------------------------------------------------------------------------

    public Company findCompanyById(int id) {
        return companyRepository.findCompanyById(id);
    }

    public Company findCompanyByEmailAndPassword(String email, String password) {
        return companyRepository.findCompanyByEmailAndPassword(email, password);
    }

//	------------------------------------------------------------------------------------------------------------

    public Coupon addCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Coupon updateCoupon(Coupon coupon) {
        return couponRepository.saveAndFlush(coupon);
    }

    public void deleteCoupon(int id) {
        couponRepository.deleteById(id);
    }

    public Coupon findCouponById(int id) {
        return couponRepository.findById(id);
    }

    public List<Coupon> findCouponsByCompanyId(int id) {
        return couponRepository.findByCompanyId(id);
    }

    public Coupon findCouponByCompanyIdAndTitle(int id, String title) {
        return couponRepository.findByCompanyIdAndTitle(id, title);
    }

    public List<Coupon> findCouponsByCompanyIdAndCategory(int id, CouponCategory category) {
        return couponRepository.findByCompanyIdAndCategory(id, category);
    }

    public List<Coupon> findCouponsByCompanyIdAndPriceLessThan(int id, double price) {
        return couponRepository.findByCompanyIdAndPriceLessThan(id, price);
    }

    public boolean couponExistsById(int id) {
        return couponRepository.existsById(id);
    }

    public boolean couponExistsByCompanyIdAndTitle(int id, String title) {
        return couponRepository.existsByCompanyIdAndTitle(id, title);
    }

    public boolean couponExistsByTitleAndIdNot(String title, int id) {
        return couponRepository.existsByTitleAndIdNot(title, id);
    }
}

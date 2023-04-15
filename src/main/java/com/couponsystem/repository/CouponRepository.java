package com.couponsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Coupon;
import com.couponsystem.enums.CouponCategory;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    int deleteAllByEndDateBefore(LocalDate endDate);

    Coupon findById(int id);

    Coupon findByCompanyIdAndTitle(int id, String title);

    List<Coupon> findAll();

    List<Coupon> findByCompanyId(int Id);

    List<Coupon> findByCompanyIdAndCategory(int id, CouponCategory category);

    List<Coupon> findByCompanyIdAndPriceLessThan(int id, double price);

    List<Coupon> getCouponsByCustomersId(int id);

    List<Coupon> getCouponsByCustomersIdAndCategory(int id, CouponCategory category);

    List<Coupon> getCouponsByCustomersIdAndPriceLessThan(int id, double price);

    boolean existsByTitleAndIdNot(String title, int id);

    boolean existsByCompanyIdAndTitle(int id, String title);

    boolean existsByCustomersIdAndTitle(int id, String title);
}

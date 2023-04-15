package com.couponsystem.schedule;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.repository.CouponRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CouponExpirationDailyJobService {
    private CouponRepository couponRepository;

    @Autowired
    public CouponExpirationDailyJobService(CouponRepository couponRepository) {
        super();
        this.couponRepository = couponRepository;
    }

    public int deleteExpiredCoupons(LocalDate now) {
        return couponRepository.deleteAllByEndDateBefore(now);
    }
}

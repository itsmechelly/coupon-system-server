package com.couponsystem.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class CouponExpirationDailyJob {
    private static final String PREFIX = ">>> ";
    private static final String TITLE = " - Daily job: ";

    private CouponExpirationDailyJobService couponExpirationDailyJobService;

    @Autowired
    public CouponExpirationDailyJob(CouponExpirationDailyJobService couponExpirationDailyJobService) {
        super();
        this.couponExpirationDailyJobService = couponExpirationDailyJobService;
    }

    @Scheduled(cron = "${cron.expression}")
    public void deleteExpiredCoupons() {

        System.out.println(">>> Expired Coupons Purge Started");

        try {
            // Delete all expired coupons and get the count of the deleted records
            int deletedCoupons = couponExpirationDailyJobService.deleteExpiredCoupons(LocalDate.now());

            // Build message
            String msg = TITLE + deletedCoupons;
            if (deletedCoupons == 1)
                msg += " coupon was deleted";
            else
                msg += " coupons were deleted";
            System.out.println(PREFIX + LocalDateTime.now().format(getFormat()) + msg);
            System.out.println(">>> Expired Coupons Purge Ended");

        } catch (Exception e) {
            System.out.println(">>> Expired Coupons Purge Caught an Error:\n" + e);
        }
    }

    private DateTimeFormatter getFormat() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
    }
}

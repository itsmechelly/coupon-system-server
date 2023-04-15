package com.couponsystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.Customer;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.exceptions.PurchaseCouponException;
import com.couponsystem.impl.CustomerImpl;

import lombok.Setter;

@Service
@Scope("prototype")
@Setter
public class CustomerService extends ClientService {
    public int customerId;
    private final CustomerImpl customerImpl;

    private CustomerService(CustomerImpl customerImpl) {
        super();
        this.customerImpl = customerImpl;
    }

//	------------------------------------------------------------------------------------------------------------

    @Override
    public boolean login(String email, String password) {
        Customer customer = customerImpl.findCustomerByEmailAndPassword(email, password);
        if (customer != null)
            return true;
        return false;
    }

    public int findCustomerIdByEmailAndPassword(String email, String password) {
        Customer cForCustomerId = customerImpl.findCustomerByEmailAndPassword(email, password);
        return cForCustomerId.getId();
    }

    public String findCustomerFirstNameByEmailAndPassword(String email, String password) {
        Customer cForCustomerId = customerImpl.findCustomerByEmailAndPassword(email, password);
        return cForCustomerId.getFirstName();
    }

//	------------------------------------------------------------------------------------------------------------

    public Coupon purchaseCoupon(int couponId) throws PurchaseCouponException, LogException, NotFoundException {

        Coupon coupFromDb = customerImpl.findCouponById(couponId);
        Customer custFromDb = customerImpl.findCustomerById(this.customerId);
        List<Coupon> coupListFromDb = customerImpl.getCouponsByCustomersId(this.customerId);


        if (customerImpl.couponExistsByCustomersIdAndTitle(this.customerId, coupFromDb.getTitle()))
            throw new PurchaseCouponException("Purchasing this type of coupon is limited to one use only. you are welcome to choose another coupon.");
        if (coupFromDb.getAmount() < 1)
            throw new PurchaseCouponException("Coupon out of stock, you are welcome to choose another coupon.");
        if (coupFromDb.getEndDate().before(java.sql.Date.valueOf(LocalDate.now())))
            throw new PurchaseCouponException("This coupon has expired, you are welcome to choose another coupon.");

        coupFromDb.setAmount(coupFromDb.getAmount() - 1);
        coupListFromDb.add(coupFromDb);
        custFromDb.setCoupons(coupListFromDb);
        customerImpl.updateCustomer(custFromDb);

        return coupFromDb;
    }

    public Coupon getOneCouponById(int couponId) throws NotFoundException, LogException {

        if (!customerImpl.couponExistsById(couponId))
            throw new NotFoundException("coupon details.");

        return customerImpl.findCouponById(couponId);
    }

    public List<Coupon> getAllCoupons() throws NotFoundException, LogException {

        List<Coupon> couponsFromDb = customerImpl.getAllCoupons();

        if (couponsFromDb.isEmpty())
            throw new NotFoundException("coupons details.");

        return couponsFromDb;
    }

    public List<Coupon> getAllCouponsByCustomerId() throws NotFoundException, LogException {

        List<Coupon> customerFromDb = customerImpl.getCouponsByCustomersId(this.customerId);

        if (customerFromDb.isEmpty())
            throw new NotFoundException("coupons details.");

        return customerFromDb;
    }

    public List<Coupon> getAllCouponsByCategory(CouponCategory category) throws NotFoundException, LogException {

        List<Coupon> coupFromDb = customerImpl.getCouponsByCustomersIdAndCategory(this.customerId, category);

        if (coupFromDb.isEmpty())
            throw new NotFoundException("coupons from category type " + category + ".");

        return coupFromDb;
    }

    public List<Coupon> getAllCouponsUnderMaxPrice(double maxPrice) throws LogException, NotFoundException {

        List<Coupon> coupFromDb = customerImpl.getCouponsByCustomersIdAndPriceLessThan(this.customerId, maxPrice);

        if (coupFromDb.isEmpty())
            throw new NotFoundException("coupons under price ", maxPrice);

        return coupFromDb;
    }

    public Customer getCustomerDetails() throws NotFoundException, LogException {

        Optional<Customer> customerFromDb = Optional.of(customerImpl.findCustomerById(this.customerId));

        if (customerFromDb.isEmpty())
            throw new NotFoundException("customer details.");

        return customerImpl.findCustomerById(this.customerId);
    }
}

package com.couponsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Coupon;
import com.couponsystem.enums.CouponCategory;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.impl.CompanyImpl;

import lombok.Setter;

@Service
@Scope("prototype")
@Setter
public class CompanyService extends ClientService {
    public int companyId;
    private CompanyImpl companyImpl;
    private FileStorageService storageService;

    public CompanyService(CompanyImpl companyImpl, FileStorageService storageService) {
        super();
        this.companyImpl = companyImpl;
        this.storageService = storageService;
    }

//	------------------------------------------------------------------------------------------------------------

    @Override
    public boolean login(String email, String password) {
        Company company = companyImpl.findCompanyByEmailAndPassword(email, password);
        if (company != null)
            return true;
        return false;
    }

    public int findCompanyIdByEmailAndPassword(String email, String password) {
        Company cForCompanyId = companyImpl.findCompanyByEmailAndPassword(email, password);
        return cForCompanyId.getId();
    }

    public String findCompanyNameByEmailAndPassword(String email, String password) {
        Company cForCompanyId = companyImpl.findCompanyByEmailAndPassword(email, password);
        return cForCompanyId.getName();
    }

//	------------------------------------------------------------------------------------------------------------

    public Coupon addCoupon(Coupon coupon, MultipartFile imageFile) throws AlreadyExistException, LogException {

        if (companyImpl.couponExistsByCompanyIdAndTitle(this.companyId, coupon.getTitle()))
            throw new AlreadyExistException("Company title ", coupon.getTitle());

        String imagePath = this.storageService.storeFile(imageFile);
        coupon.setImage(imagePath);

        coupon.setCompanyId(this.companyId);
        companyImpl.addCoupon(coupon);
        return coupon;
    }

    public Coupon updateCoupon(Coupon coupon, MultipartFile imageFile)
            throws LogException, NotFoundException, NotAllowedException, AlreadyExistException {

        Optional<Coupon> coupFromDb1 = Optional
                .of(companyImpl.findCouponByCompanyIdAndTitle(this.companyId, coupon.getTitle()));
        Optional<Coupon> coupFromDb2 = Optional.of(companyImpl.findCouponById(coupon.getId()));

        if (coupon.getId() != coupFromDb1.get().getId())
            throw new NotAllowedException("coupon id number", coupon.getId());
        if (coupon.getCompanyId() != coupFromDb2.get().getCompanyId())
            throw new NotAllowedException("company id number", this.companyId);
        if (companyImpl.couponExistsByTitleAndIdNot(coupon.getTitle(), coupon.getId()))
            throw new AlreadyExistException("Company title ", coupon.getTitle());

        if (imageFile != null) {
            this.storageService.deleteFile(coupon.getImage());
            String imagePath = this.storageService.storeFile(imageFile);
            coupon.setImage(imagePath);
        }

        companyImpl.updateCoupon(coupon);
        return coupon;
    }

    public String deleteCoupon(int id) throws NotFoundException, LogException {

        if (!companyImpl.couponExistsById(id))
            throw new NotFoundException("coupons details.");

        companyImpl.deleteCoupon(id);
        return "Coupon with id number " + id + " deleted successfully.";
    }

    public List<Coupon> getAllCoupons() throws LogException, NotFoundException {

        List<Coupon> coupFromDb = companyImpl.findCouponsByCompanyId(this.companyId);

        if (coupFromDb.isEmpty())
            throw new NotFoundException("coupons details.");

        return coupFromDb;
    }

    public List<Coupon> getAllCouponsByCategory(CouponCategory category) throws NotFoundException, LogException {

        List<Coupon> coupFromDb = companyImpl.findCouponsByCompanyIdAndCategory(this.companyId, category);

        if (coupFromDb.isEmpty())
            throw new NotFoundException("coupons from category type " + category + ".");

        return coupFromDb;
    }

    public List<Coupon> getAllCouponsUnderMaxPrice(double maxPrice) throws LogException, NotFoundException {

        List<Coupon> coupFromDb = companyImpl.findCouponsByCompanyIdAndPriceLessThan(this.companyId, maxPrice);

        if (coupFromDb.isEmpty())
            throw new NotFoundException("coupons under price ", maxPrice);

        return coupFromDb;
    }

    public Company getCompanyDetails() throws NotFoundException, LogException {

        Optional<Company> companyFromDb = Optional.of(companyImpl.findCompanyById(this.companyId));

        if (companyFromDb.isEmpty())
            throw new NotFoundException("company details.");

        return companyImpl.findCompanyById(this.companyId);
    }
}

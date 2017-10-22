package com.evgen.coupons.logic;

import java.util.List;

import com.evgen.coupons.beans.Coupon;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.dao.CompanyDao;
import com.evgen.coupons.dao.CouponsDao;
import com.evgen.coupons.dao.CustomerDao;
import com.evgen.coupons.enums.CouponType;
import com.evgen.coupons.enums.ErrorType;
import com.evgen.coupons.exceptions.ApplicationException;

public class CouponController {

	CustomerDao customerDao = new CustomerDao();
	CouponsDao couponsDao = new CouponsDao();
	CompanyDao companyDao = new CompanyDao();

	private Coupon coupon;
	private Customer customer;

	public CouponController(Coupon coupon) {
		this.coupon = coupon;
	}

	public CouponController(Customer customer) {
		this.customer = customer;
	}

	public void createCoupon(Coupon coupon) throws ApplicationException {

		if (couponsDao.isCouponExistById(coupon.getId())) {
			throw new ApplicationException("Coupon with ID " + coupon.getId() + "already exist",
					ErrorType.COUPON_ALREADY_EXIST);
		}
		couponsDao.createCoupon(coupon);
	}

	List<Coupon> getAllCoupons() throws ApplicationException {
		return couponsDao.getAllCoupons();
	}

	public Coupon getCouponByID(long couponID) throws ApplicationException {
		if (couponsDao.isCouponExistById(coupon.getId())) {

		}
		return couponsDao.getCouponById(couponID);
	}

	public void updateCoupon(Coupon coupon) throws ApplicationException {
		if (couponsDao.isCouponExistById(coupon.getId())) {
			couponsDao.updateCoupon(coupon);
		}
	}

	public void deleteCouponById(long couponID) throws ApplicationException {
		if (couponsDao.isCouponExistById(couponID))
			coupon.setId(couponID);
		couponsDao.deleteCouponById(coupon);
	}
	
	 List<Coupon> getCouponByType(CouponType couponType) throws ApplicationException {
			return couponsDao.getCouponsByType(couponType);

	 }

	 List<Coupon> getCouponByCompany(long companyId) throws ApplicationException {
			return couponsDao.getCouponsByCompany(companyId);

	 }
	 
	 List<Coupon> getCouponByCustomer(long customerId) throws ApplicationException {
			return couponsDao.getCouponsByCustomer(customerId);

	 }

	public void PurchaseCoupon(long userId, long couponId) throws ApplicationException {

		if (couponsDao.getCouponById(couponId) != null && couponsDao.getCouponsByCustomer(customer.getId()) == null
				&& couponsDao.isCouponExistById(couponId)) {

			couponsDao.createCouponInJoinedTable(couponId, userId);

		}

	}
}

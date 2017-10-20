package com.evgen.coupons.logic;

import com.evgen.coupons.beans.Coupon;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.dao.CompanyDao;
import com.evgen.coupons.dao.CouponsDao;
import com.evgen.coupons.dao.CustomerDao;
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
	
	
	public void PurchaseCoupon(long userId, long couponId) throws ApplicationException {

		if (couponsDao.getCouponById(couponId) != null
				&& couponsDao.getCouponsByCustomer(customer.getId()) == null && couponsDao.isCouponExistById(couponId)) {

			 couponsDao.createCouponInJoinedTable(couponId, userId);
			
		} 
		
	}
}

package com.evgen.coupons.interfaces.dao;

import java.util.List;
import com.evgen.coupons.beans.Coupon;
import com.evgen.coupons.enums.CouponType;
import com.evgen.coupons.exceptions.ApplicationException;

public interface ICouponsDao {

	 //create
	 void createCoupon(Coupon Coupon) throws ApplicationException;
	 //delete
	 void deleteCouponById(Coupon coupon) throws ApplicationException;
	 //update
	 void updateCoupon(Coupon coupon) throws ApplicationException;

	//collection get
	 List<Coupon> getAllCoupons()throws ApplicationException;
	 
	 Coupon getCouponById(Long id)throws ApplicationException;
	 
	 List<Coupon> getCouponsByType(CouponType couponType) throws ApplicationException;
	 
	 List<Coupon> getCouponsByCompany(long companyId) throws ApplicationException;
	 
	 List<Coupon> getCouponsByCustomer(long customerId) throws ApplicationException;
	 
	 void deleteExpiredCoupons(String date) throws ApplicationException;
	
}

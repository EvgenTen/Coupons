/*
 * The class represents all methods that
 * must be used in classes thats implements this one
*/
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
	 
	 //delete by company ID
	 public void deleteCouponByCompanyId(Coupon coupon) throws ApplicationException;
	
	 //update
	 void updateCoupon(Coupon coupon) throws ApplicationException;

	 // get coupon by ID
	 public Coupon getCouponById(Long id)throws ApplicationException;
	
	 //collection get
     List<Coupon> getAllCoupons()throws ApplicationException;
	 
     // get list of coupons by Type 
	 List<Coupon> getCouponsByType(CouponType couponType) throws ApplicationException;
	 
  	 // get list of coupons by Company ID
	 List<Coupon> getCouponsByCompany(long companyId) throws ApplicationException;
	
	 // get list of coupons by customer ID
	 List<Coupon> getCouponsByCustomer(long customerId) throws ApplicationException;
	
	 // delete expired coupons
	 void deleteExpiredCoupons(String date) throws ApplicationException;
	 
	 // create coupon in joined table
	 public void createCouponInJoinedTable(Long customerId, Long couponId) throws ApplicationException;
	
	 //delete coupons from joined table
	 public void deleteCouponInJoinedTable(Long couponId) throws ApplicationException;
	 
	 // check if coupon exist by ID
	 public boolean isCouponExistById(Long id) throws ApplicationException;
	 
	 //delete coupons from joined table by customer ID
	 public void deleteCouponInJoinedTableByCustomerId(Long customerId) throws ApplicationException;
}

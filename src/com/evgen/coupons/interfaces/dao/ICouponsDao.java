package com.evgen.coupons.interfaces.dao;

import java.util.List;
import com.evgen.coupons.beans.Coupon;
import com.evgen.coupons.exceptions.ApplicationException;

public interface ICouponsDao {

	 //create
	 void couponCreate(Coupon Coupon) throws ApplicationException;
	 //delete
	 void couponDeleteById(Long id) throws ApplicationException;
	 //update
	 void couponUpdate(Coupon coupon) throws ApplicationException;

	//collection read
	 List<Coupon> getAllCoupons()throws ApplicationException;
	 
	 Coupon couponGetById(Long id)throws ApplicationException;
	 
	
}
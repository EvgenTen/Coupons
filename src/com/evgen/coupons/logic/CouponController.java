/*This class is controller layer 
 * of Coupons DAO 
 * */
package com.evgen.coupons.logic;

import java.util.List;
import com.evgen.coupons.beans.Coupon;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.dao.CouponsDao;
import com.evgen.coupons.enums.CouponType;
import com.evgen.coupons.exceptions.ApplicationException;

public class CouponController {

	CouponsDao couponsDao = new CouponsDao();

	private Coupon coupon;
	private Customer customer;
	public CouponController(Coupon coupon) {
		this.coupon = coupon;
	}
	public CouponController(Customer customer) {
		this.customer = customer;
	}

	// Create Coupon
	public void createCoupon(Coupon coupon) throws ApplicationException {

		if (couponsDao.isCouponExistById(coupon.getId())) // check if coupon exist by ID
		
		couponsDao.createCoupon(coupon);
	}
	//get List of all coupons
	List<Coupon> getAllCoupons() throws ApplicationException {
		return couponsDao.getAllCoupons();
	}
	// get coupon by ID
	public Coupon getCouponByID(long couponID) throws ApplicationException {
		if (couponsDao.isCouponExistById(coupon.getId())) { // check if coupon exist by ID

		}
		return couponsDao.getCouponById(couponID);
	}
	// update coupon
	public void updateCoupon(Coupon coupon) throws ApplicationException {
		if (couponsDao.isCouponExistById(coupon.getId())) { // check if coupon exist by ID
			couponsDao.updateCoupon(coupon);
		}
	}
	// delete coupon
	public void deleteCouponById(long couponID) throws ApplicationException {
		if (couponsDao.isCouponExistById(couponID))
			coupon.setId(couponID);
		couponsDao.deleteCouponById(coupon);
	}
	// get coupon by TYPE
	 List<Coupon> getCouponByType(CouponType couponType) throws ApplicationException {
			return couponsDao.getCouponsByType(couponType);

	 }
	// get coupon by Company ID
	 List<Coupon> getCouponByCompany(long companyId) throws ApplicationException {
			return couponsDao.getCouponsByCompany(companyId);

	 }
	// get coupon by Customer ID
	 List<Coupon> getCouponByCustomer(long customerId) throws ApplicationException {
			return couponsDao.getCouponsByCustomer(customerId);

	 }
	 // Purchase coupon
	 // Create coupon in joined table with user ID and coupon ID
	public void PurchaseCoupon(long userId, long couponId) throws ApplicationException {

		if (couponsDao.getCouponById(couponId) != null && couponsDao.getCouponsByCustomer(customer.getId()) == null // check if coupon and customer are exist in DB
				&& couponsDao.isCouponExistById(couponId)) {

			couponsDao.createCouponInJoinedTable(couponId, userId);

		}

	}
}

package com.evgen.coupons.logic;

import com.evgen.coupons.beans.Coupon;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.dao.CompanyDao;
import com.evgen.coupons.dao.CouponsDao;
import com.evgen.coupons.dao.CustomerDao;
import com.evgen.coupons.dao.JoinedCustomerCoupon;
import com.evgen.coupons.enums.ClientType;
import com.evgen.coupons.enums.ErrorType;
import com.evgen.coupons.exceptions.ApplicationException;

public class CustomerController implements IClientController {
	CustomerDao customerDao = new CustomerDao();
	CouponsDao couponsDao = new CouponsDao();
	CompanyDao companyDao = new CompanyDao();
	JoinedCustomerCoupon joinTable = new JoinedCustomerCoupon();

	private Customer customer;

	public CustomerController(Customer customer) {
		this.customer = customer;
	}

	public boolean login(String custName, String password, ClientType clientType) throws ApplicationException {
		
		return customerDao.login(custName, password);

	}

	public boolean purchaseCoupon(long userId, long couponId) throws ApplicationException {

		if (couponsDao.couponGetById(couponId) != null
				&& couponsDao.couponGetByCustomer(customer.getId()) == null) {

			joinTable.JoinCouponCreate(coupon.getId(), customer.getId());
			return true;

		}
		return false;
	}
}
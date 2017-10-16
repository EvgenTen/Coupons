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

		if (clientType.equals(clientType.CUSTOMER)) {

			boolean isCustomerExist = customerDao.login(custName, password);
			if (isCustomerExist) {
				return true;
			}
		}
		return false;
	}

	public boolean purchaseCoupon(Coupon coupon) throws ApplicationException {

		if (couponsDao.couponGetById(coupon.getId()) != null
				&& couponsDao.couponGetByCustomer(customer.getId()) == null) {

			joinTable.JoinCouponCreate(coupon.getId(), customer.getId());
			return true;

		}
		return false;
	}
}
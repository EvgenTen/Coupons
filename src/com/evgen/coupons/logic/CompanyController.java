package com.evgen.coupons.logic;

import com.evgen.coupons.beans.Company;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.dao.CompanyDao;
import com.evgen.coupons.dao.CouponsDao;
import com.evgen.coupons.dao.CustomerDao;
import com.evgen.coupons.enums.ClientType;
import com.evgen.coupons.exceptions.ApplicationException;

public class CompanyController {

	CustomerDao customerDao = new CustomerDao();
	CouponsDao couponsDao = new CouponsDao();
	CompanyDao companyDao = new CompanyDao();
	
	private Company company;

	public CompanyController(Company company) {
		this.company = company;
	}

	public boolean login(String custName, String password, ClientType clientType) throws ApplicationException {
		
		return customerDao.login(custName, password);

	}

	public boolean isAvailablePurchaseCoupon(long userId, long couponId) throws ApplicationException {

		if (couponsDao.getCouponById(couponId) != null
				&& couponsDao.getCouponsByCustomer(company.getId()) == null) {

			couponsDao.createCouponInJoinedTable(couponId, userId);
			return true;

		}
		return false;
	}
	
	
}

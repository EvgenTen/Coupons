package com.evgen.coupons.logic;

import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.dao.CustomerDao;
import com.evgen.coupons.exceptions.ApplicationException;

public class CustomerController {

	private CustomerDao customerDao;
	
	private CustomerController(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	public void customerCreate(Customer customer) throws ApplicationException {
		this.customerDao.customerCreate(customer);
	}
	String activationToken = generateActivationToken();
	customerDao.
}

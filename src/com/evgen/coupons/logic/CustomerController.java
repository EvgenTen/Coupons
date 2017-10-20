package com.evgen.coupons.logic;

import java.util.List;

import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.dao.CustomerDao;
import com.evgen.coupons.exceptions.ApplicationException;

public class CustomerController  {
	CustomerDao customerDao = new CustomerDao();

	private Customer customer;

	public CustomerController(Customer customer) {
		this.customer = customer;
	}

	public boolean login(String custName, String password) throws ApplicationException {
		
		return customerDao.login(custName, password);

	}

	public void createCustomer(Customer customer) throws ApplicationException{

		if (customerDao.isCustomerExistByName(customer.getCustomerName()))
		customerDao.createCustomer(customer);
	}
	
	public void deleteCustomer (long customerID) throws ApplicationException{
		if (customerDao.isCustomerExistById(customerID))
		customer.setId(customerID);
		customerDao.deleteCustomerById(customer);
	}
	
	public void updateCustomer(Customer customer) throws ApplicationException{
		if (customerDao.isCustomerExistById(customer.getId()))
		customerDao.updateCustomer(customer);
	}
	
	public Customer getCustomerbyID(long customerID) throws ApplicationException {
		if (customerDao.isCustomerExistById(customer.getId())) {
			
		}return customerDao.getCustomerById(customerID);
	}
		
	public Customer getCustomerbyName(String customerName) throws ApplicationException {
		if (customerDao.isCustomerExistByName(customerName)) {
			
		}return customerDao.getCustomerByName(customerName);
	}
	
	public List<Customer> getAllCustomers() throws ApplicationException {
		return customerDao.getAllCustomers();
	}
	

	
	
	
	
}
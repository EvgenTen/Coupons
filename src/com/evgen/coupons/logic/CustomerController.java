/*This class is controller layer 
 * of Customer DAO 
 * */
package com.evgen.coupons.logic;

import java.util.List;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.dao.CouponsDao;
import com.evgen.coupons.dao.CustomerDao;
import com.evgen.coupons.exceptions.ApplicationException;

public class CustomerController  {
	
	CouponsDao couponsDao = new CouponsDao();
	CustomerDao customerDao = new CustomerDao();

	// creating constructor
	private Customer customer;
	
	public CustomerController(Customer customer) {
		this.customer = customer;
	}
	
	// Login as Customer
	public boolean login(String custName, String password) throws ApplicationException {
		
		return customerDao.login(custName, password);

	}
	// Create customer
	public void createCustomer(Customer customer) throws ApplicationException{

		if (customerDao.isCustomerExistByName(customer.getCustomerName())) //check if customer exist by name
		customerDao.createCustomer(customer);
	}
	// delete customer 
	public void deleteCustomer (long customerID) throws ApplicationException{
		if (customerDao.isCustomerExistById(customerID))//check if customer exist by ID
		customer.setId(customerID);
		couponsDao.deleteCouponInJoinedTableByCustomerId(customerID); // delete customer`s coupon
		customerDao.deleteCustomerById(customer);
	}
	
	// update customer
	public void updateCustomer(Customer customer) throws ApplicationException{
		if (customerDao.isCustomerExistById(customer.getId()))//check if customer exist
		customerDao.updateCustomer(customer);
	}
	// get customer by ID
	public Customer getCustomerbyID(long customerID) throws ApplicationException {
		if (customerDao.isCustomerExistById(customer.getId())) {//check if customer exist
			
		}return customerDao.getCustomerById(customerID);
	}
	// get customer by Name
	public Customer getCustomerbyName(String customerName) throws ApplicationException {
		if (customerDao.isCustomerExistByName(customerName)) {//check if customer exist
			
		}return customerDao.getCustomerByName(customerName);
	}
	/* Retrieve customers list
	 * */
	public List<Customer> getAllCustomers() throws ApplicationException {
		return customerDao.getAllCustomers();
	}
	

	
	
	
	
}
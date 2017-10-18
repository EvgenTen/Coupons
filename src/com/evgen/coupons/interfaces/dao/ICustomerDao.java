package com.evgen.coupons.interfaces.dao;

import java.util.List;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.exceptions.ApplicationException;

public interface ICustomerDao {

	 //create
	 void createCustomer(Customer customer) throws ApplicationException;
	 //delete
	 abstract void deleteCustomerById(Customer customer) throws ApplicationException;
	 //update
	 void updateCustomer(Customer customer) throws ApplicationException;

	//collection read
	 List<Customer> getAllCustomers() throws ApplicationException;
	 
	Customer getCustomerById(Long id) throws ApplicationException;
	
	boolean login(String customerName, String customerPassword) throws ApplicationException;
	
	
	
	 
}

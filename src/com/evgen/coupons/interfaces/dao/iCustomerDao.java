package com.evgen.coupons.interfaces.dao;

import java.util.List;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.exceptions.ApplicationException;

public interface iCustomerDao {

	 //create
	 void customerCreate(Customer customer) throws ApplicationException;
	 //delete
	 abstract void customerDeleteById(Customer customer) throws ApplicationException;
	 //update
	 void customerUpdate(Customer customer) throws ApplicationException;

	//collection read
	 List<Customer> getAllCustomers() throws ApplicationException;
	 
	Customer getById(Long id) throws ApplicationException;
	
	
	
	 
}

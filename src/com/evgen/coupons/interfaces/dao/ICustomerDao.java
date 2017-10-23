/*
 * The class represents all methods that
 * must be used in classes thats implements this one
*/
package com.evgen.coupons.interfaces.dao;

import java.util.List;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.exceptions.ApplicationException;

public interface ICustomerDao {

	 //create
	 void createCustomer(Customer customer) throws ApplicationException;
	 
	 //delete
	 abstract void deleteCustomerById(Customer customer) throws ApplicationException;
	 
	 // get customer by ID
	 public Customer getCustomerById(Long id) throws ApplicationException;
	
	 // get customer by Name
	 public Customer getCustomerByName(String name) throws ApplicationException;
	 
	 //update
	 void updateCustomer(Customer customer) throws ApplicationException;

   	 //collection read
	 List<Customer> getAllCustomers() throws ApplicationException;
	 
	 // is exist by name
	 public boolean isCustomerExistByName(String customerName) throws ApplicationException;
	 
	 // is exist by ID
	 public boolean isCustomerExistById(Long id) throws ApplicationException;
	
	 // login check
	 boolean login(String customerName, String customerPassword) throws ApplicationException;
	
	
	
	 
}

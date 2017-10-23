/*
 * The class represents all methods that
 * must be used in classes thats implements this one
*/
package com.evgen.coupons.interfaces.dao;

import java.util.List;
import com.evgen.coupons.beans.Company;
import com.evgen.coupons.exceptions.ApplicationException;

public interface ICompanyDao {
	
	 //create
	 void createCompany(Company company) throws ApplicationException;
	
	 //delete
	 void deleteCompanyById(Company company) throws ApplicationException;
	
	 //update
	 void updateCompany(Company company) throws ApplicationException;

	//collection read
	 List<Company> getAllCompanies() throws ApplicationException;

	 // get company by ID
	 Company getCompanyById(Long id) throws ApplicationException;

	 // get company by name
	 public Company getCompanyByName(String companyName) throws ApplicationException;
	
	 // is exist by name
	 public boolean isCompanyExistByName(String companyName) throws ApplicationException;

	 //is exist by ID
	 public boolean isCompanyExistById(Long id) throws ApplicationException;
	 
	 // login check
	 boolean login(String companyName, String companyPassword) throws ApplicationException;
} 
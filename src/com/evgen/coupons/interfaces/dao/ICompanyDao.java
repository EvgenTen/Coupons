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
	 
	 Company getCompanyById(Long id) throws ApplicationException;

	 boolean login(String companyName, String companyPassword) throws ApplicationException;
} 
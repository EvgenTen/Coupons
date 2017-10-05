package com.evgen.coupons.interfaces.dao;

import java.util.List;
import com.evgen.coupons.beans.Company;
import com.evgen.coupons.exceptions.ApplicationException;

public interface ICompanyDao {
	 //create
	 void companyCreate(Company company) throws ApplicationException;
	 //delete
	 void companyDeleteById(Company company) throws ApplicationException;
	 //update
	 void companyUpdate(Company company) throws ApplicationException;

	//collection read
	 List<Company> getAllCompanies() throws ApplicationException;
	 
	 Company companyGetById(Long id) throws ApplicationException;

} 
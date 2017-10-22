package com.evgen.coupons.logic;

import java.util.List;

import com.evgen.coupons.beans.Company;
import com.evgen.coupons.beans.Coupon;
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
	private Coupon coupon;
	public CompanyController(Company company) {
		this.company = company;
	}
	public CompanyController(Coupon coupon) {
		this.coupon = coupon;
	}

	public boolean login(String custName, String password) throws ApplicationException {
		
		return companyDao.login(custName, password);

	}
	
	public void createCompany(Company company) throws ApplicationException{

		if (companyDao.isCompanyExistByName(company.getCompanyName()) != true && companyDao.isCompanyExistById(company.getId()) != true)
			companyDao.createCompany(company);
	}
	
	public void deleteCompany(long companyID) throws ApplicationException{
		if (companyDao.isCompanyExistById(companyID))
			company.setId(companyID);
		    coupon.setCompanyId(companyID);
			couponsDao.deleteCouponByCompanyId(coupon);
		companyDao.deleteCompanyById(company);
	}
	
	public void updateCompany(Company company) throws ApplicationException{
		if (companyDao.isCompanyExistById(company.getId()))
			companyDao.updateCompany(company);
	}
	
	public Company getCompanybyID(long companyID) throws ApplicationException {
		if (companyDao.isCompanyExistById(company.getId())) {
			
		}return companyDao.getCompanyById(companyID);
	}
		
	public Company getCompanybyName(String companyName) throws ApplicationException {
		if (companyDao.isCompanyExistByName(companyName)) {
			
		}return companyDao.getCompanyByName(companyName);
	}

	public List<Company> getAllCompanies() throws ApplicationException {
		return companyDao.getAllCompanies();
	}
	
	
}

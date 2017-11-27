/*This class is controller layer 
 * of Company DAO 
 * */
package com.evgen.coupons.logic;

import java.util.List;
import com.evgen.coupons.beans.Company;
import com.evgen.coupons.beans.Coupon;
import com.evgen.coupons.dao.CompanyDao;
import com.evgen.coupons.dao.CouponsDao;
import com.evgen.coupons.exceptions.ApplicationException;

public class CompanyController {

	CouponsDao couponsDao = new CouponsDao();
	CompanyDao companyDao = new CompanyDao();
	
	// creating constructor
	private Company company;
	private Coupon coupon;
	public CompanyController(){
	}
	public CompanyController(Company company) {
		this.company = company;
	}
	public CompanyController(Coupon coupon) {
		this.coupon = coupon;
	}

	//Login as company owner
	public boolean login(String custName, String password) throws ApplicationException {
		
		return companyDao.login(custName, password);

	}
	/*Creating company 
	 * */
	public void createCompany(Company company) throws ApplicationException{

		if (companyDao.isCompanyExistByName(company.getCompanyName()) != true && companyDao.isCompanyExistById(company.getId()) != true)
			//check if company with this name and ID doesn`t exist
			companyDao.createCompany(company);
	}
	/*Delete company 
	 * */
	public void deleteCompany(long companyID) throws ApplicationException{
		if (companyDao.isCompanyExistById(companyID)) // check if company with this ID exist in DB
			company.setId(companyID);
		    coupon.setCompanyId(companyID);
			couponsDao.deleteCouponByCompanyId(coupon); // deleting company with all its coupons
		companyDao.deleteCompanyById(company);
	}
	/*retrieve company 
	 * */
	public void updateCompany(Company company) throws ApplicationException{
		if (companyDao.isCompanyExistById(company.getId()))// check if company with this ID exist in DB
			companyDao.updateCompany(company);
	}
	/*get company by ID
	 * */
	public Company getCompanybyID(long companyID) throws ApplicationException {
		if (companyDao.isCompanyExistById(company.getId())) {// check if company with this ID exist in DB
			
		}return companyDao.getCompanyById(companyID);
	}
	/*get company by Name
	 * */
	public Company getCompanybyName(String companyName) throws ApplicationException {
		if (companyDao.isCompanyExistByName(companyName)) {// check if company with this ID exist in DB
			
		}return companyDao.getCompanyByName(companyName);
	}
	/* Retrieve companies list
	 * */
	public List<Company> getAllCompanies() throws ApplicationException {
		return companyDao.getAllCompanies();
	}
	
	
}

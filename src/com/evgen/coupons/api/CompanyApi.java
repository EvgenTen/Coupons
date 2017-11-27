package com.evgen.coupons.api;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.evgen.coupons.beans.Company;
import com.evgen.coupons.dao.CompanyDao;
import com.evgen.coupons.exceptions.ApplicationException;
import com.evgen.coupons.logic.CompanyController;


@Path("company")
public class CompanyApi 
{
	//CompanyDao companyDao = new CompanyDao();
	CompanyController cc = new CompanyController();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	 List<Company> companyList() throws ApplicationException
	 {
			return cc.getAllCompanies();
	 }
  }


package com.evgen.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.evgen.coupons.beans.Company;
import com.evgen.coupons.enums.ErrorType;
import com.evgen.coupons.exceptions.ApplicationException;
import com.evgen.coupons.interfaces.dao.ICompanyDao;
import com.evgen.coupons.utils.JdbcUtils;

public class CompanyDao extends JdbcUtils implements ICompanyDao {
	PreparedStatement statement = null;
	Connection connection = null;
	ResultSet resultSet = null;

	@Override
	public void companyCreate(Company company) throws ApplicationException {

		String query = "INSERT INTO COMPANY (ID, COMP_NAME, PASSWORD, EMAIL) " + "VALUES(?, ?, ?, ?)";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			
			statement.setLong  (1, company.getId());
			statement.setString(2, company.getCompanyName());
			statement.setString(3, company.getPassword());
			statement.setString(4, company.getEmail());

			statement.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}

	}

	@Override
	public List<Company> getAllCompanies() throws ApplicationException {
		List<Company> companyList = new ArrayList<>();

		String query = "SELECT * FROM COMPANY";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Company company = new Company();
				company.setId(resultSet.getLong("ID"));
				company.setCompanyName(resultSet.getString("COMP_NAME"));
				company.setPassword(resultSet.getString("PASSWORD"));
				company.setEmail(resultSet.getString("EMAIL"));
				
				companyList.add(company);
			}
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return companyList;
	}

	@Override
	public Company companyGetById(Long id) throws ApplicationException {

		String query = "SELECT * FROM COMPANY WHERE ID=" + id;

		Company company = new Company();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			resultSet.next();
			
			company.setId(resultSet.getLong("ID"));
			company.setCompanyName(resultSet.getString("COMP_NAME"));
			company.setPassword(resultSet.getString("PASSWORD"));
			company.setEmail(resultSet.getString("EMAIL"));

			
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return company;
	}

	@Override
	public void companyUpdate(Company company) throws ApplicationException {

		String query = "UPDATE COMPANY SET COMP_NAME=?, PASSWORD=?, EMAIL=?  WHERE ID=?";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(4, company.getId());
			
			statement.setString(1, company.getCompanyName());
			statement.setString(2, company.getPassword());
			statement.setString(3, company.getEmail());
			
			statement.executeUpdate();

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}

	@Override
	public void companyDeleteById(Company company) throws ApplicationException {
		
		String query = "DELETE FROM COMPANY WHERE ID=?";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(1, company.getId());
			statement.executeUpdate();
			
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}
}

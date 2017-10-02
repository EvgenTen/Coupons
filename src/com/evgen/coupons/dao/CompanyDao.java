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
	PreparedStatement statement;
	Connection connection;
	ResultSet resultSet;

	@Override
	public void companyCreate(Company company) throws ApplicationException {

		String query = "INSERT INTO COMPANY (COMP_NAME, PASSWORD, EMAIL) " + "VALUES(?, ?, ?)";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);

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
	public List<Company> getAllCompanies() throws ApplicationException {
		List<Company> companyList = new ArrayList<>();

		String query = "SELECT ID, COMP_NAME, PASSWORD, EMAIL " + "FROM COMPANY";

		try {
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

		String query = "SELECT ID, COMP_NAME, PASSWORD, EMAIL WHERE ID=?";

		Company company = new Company();
		try {
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);

			resultSet = statement.executeQuery();

			company.setId(resultSet.getLong("ID"));
			company.setCompanyName(resultSet.getString("CUST_NAME"));
			company.setPassword(resultSet.getString("PASSWORD"));

			statement.executeUpdate();
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
			statement = connection.prepareStatement(query);

			statement.setNString(1, company.getCompanyName());
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
	public void companyDelete(Long id) throws ApplicationException {
		Company company = new Company();
		String query = "DELETE FROM COMPANY WHERE ID=?";

		try {
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

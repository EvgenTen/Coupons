/*
 * This class is DAO Company class
 * works with company table in DB
 * receive parameters from controller layer
*/
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

	PreparedStatement statement = null; // Must be equal to NULL because resources
	Connection connection = null; // will be closed in JDBC after check.
	ResultSet resultSet = null; //

	/*
	 * This method creates company with given values in Company table
	 */
	@Override
	public void createCompany(Company company) throws ApplicationException {

		String query = "INSERT INTO COMPANY (ID, COMP_NAME, PASSWORD, EMAIL) " + "VALUES(?, ?, ?, ?)";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);

			statement.setLong(1, company.getId());
			statement.setString(2, company.getCompanyName());
			statement.setString(3, company.getPassword());
			statement.setString(4, company.getEmail());

			statement.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COMPANY_CREATE_ERROR); // Must be in try/catch then throw custom exception
		} finally {
			JdbcUtils.closeResources(connection, statement); // Closing resources
		}

	}

	/*
	 * This method receive List of companies from DB returns company List
	 */
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
			throw new ApplicationException(e, ErrorType.UNABLE_TO_RETREIVE_ALL_COMPANIES);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return companyList;
	}

	/*
	 * This method receive companies by ID from DB returns company
	 */
	@Override
	public Company getCompanyById(Long id) throws ApplicationException {

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
			throw new ApplicationException(e, ErrorType.COMPANY_RETREIVE_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return company;
	}

	/*
	 * This method receive companies by Name from DB returns company
	 */
	@Override
	public Company getCompanyByName(String companyName) throws ApplicationException {

		String query = "SELECT * FROM COMPANY WHERE COMP_NAME=" + companyName;

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
			throw new ApplicationException(e, ErrorType.COMPANY_RETREIVE_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return company;
	}

	/*
	 * This method updates companies in DB with given values
	 */
	@Override
	public void updateCompany(Company company) throws ApplicationException {

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
			throw new ApplicationException(e, ErrorType.COMPANY_UPDATE_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}

	/*
	 * This method delete companies from DB by ID
	 * 
	 */
	@Override
	public void deleteCompanyById(Company company) throws ApplicationException {

		String query = "DELETE FROM COMPANY WHERE ID=?";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(1, company.getId());
			statement.executeUpdate();

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COMPANY_DELETE_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}

	/*
	 * This method check if company exist in DB by NAME
	 */
	@Override
	public boolean isCompanyExistByName(String companyName) throws ApplicationException {

		String query = "SELECT COMP_NAME FROM COMPANY WHERE COMP_NAME=" + companyName;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			resultSet.next();

			if (!resultSet.next()) {
				return false;
			}
			return true;

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.WRONG_COMPANY_NAME_OR_DOESNT_EXIST);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}

	}
	/*
	 * This method check if company exist in DB by ID
	 */
	@Override
	public boolean isCompanyExistById(Long id) throws ApplicationException {

		String query = "SELECT ID FROM COMPANY WHERE ID=" + id;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			resultSet.next();
			statement.executeQuery();

			if (!resultSet.next()) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.WRONG_COMPANY_NAME_OR_DOESNT_EXIST);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
	}
	/*This method provide login for companies 
	 * using company name and password
	 * return true or false
	 * */
	
	@Override
	public boolean login(String companyName, String companyPassword) throws ApplicationException {

		String query = "SELECT COMP_NAME, PASSWORD FROM COMPANY WHERE COMP_NAME = ?";

		Company company = new Company();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, companyName);
			resultSet = statement.executeQuery();
			resultSet.next();

			company.setCompanyName(resultSet.getString("COMP_NAME"));
			company.setPassword(resultSet.getString("PASSWORD"));

			if (!companyName.equals(company.getCompanyName()) && !companyPassword.equals(company.getPassword())) {
				System.out.println("Login or password incorrect");
				return false;
			} else
				return true;

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.WRONG_COMPANY_NAME_OR_DOESNT_EXIST);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}

	}
}

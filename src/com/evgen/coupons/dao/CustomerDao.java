/*
 * This class is DAO Customer class
 * works with customer table in DB
 * receive parameters from controller layer
*/
package com.evgen.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.enums.ErrorType;
import com.evgen.coupons.exceptions.ApplicationException;
import com.evgen.coupons.interfaces.dao.ICustomerDao;
import com.evgen.coupons.utils.JdbcUtils;

public class CustomerDao extends JdbcUtils implements ICustomerDao {
	PreparedStatement statement = null; // Must be equal to NULL because resources
	Connection connection = null; // will be closed in JDBC after check.
	ResultSet resultSet = null;//
	
	/*
	 * This method creates customer with given values in Customer table
	 */
	@Override
	public void createCustomer(Customer customer) throws ApplicationException {

		String query = "INSERT INTO CUSTOMER (ID, CUST_NAME, PASSWORD) " + "VALUES(?, ?, ?)";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);

			statement.setLong(1, customer.getId());
			statement.setString(2, customer.getCustomerName());
			statement.setString(3, customer.getPassword());

			statement.executeUpdate();
			
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.CUSTOMER_CREATE_ERROR); // Must be in try/catch then throw custom exception

		} finally {
			JdbcUtils.closeResources(connection, statement); // Closing resources
		}
	}
	/*
	 * This method receive List of customers from DB returns customers List
	 */
	@Override
	public List<Customer> getAllCustomers() throws ApplicationException {
		List<Customer> customerList = new ArrayList<>();

		String query = "SELECT * FROM CUSTOMER";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getLong("ID"));
				customer.setCustomerName(resultSet.getString("CUST_NAME"));
				customer.setPassword(resultSet.getString("PASSWORD"));

				customerList.add(customer);
			}
		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.UNABLE_TO_RETREIVE_ALL_CUSTOMERS); 
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return customerList;
	}
	/*
	 * This method receive customer by ID from DB returns customer
	 */
	@Override
	public Customer getCustomerById(Long id) throws ApplicationException {

		String query = "SELECT * FROM CUSTOMER WHERE ID=" + id;

		Customer customer = new Customer();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			resultSet.next();

			customer.setId(resultSet.getLong("ID"));
			customer.setCustomerName(resultSet.getString("CUST_NAME"));
			customer.setPassword(resultSet.getString("PASSWORD"));

		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.CUSTOMER_RETREIVE_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return customer;
	}
	/*
	 * This method receive customer by Name from DB returns customer
	 */
	@Override
	public Customer getCustomerByName(String name) throws ApplicationException {

		String query = "SELECT * FROM CUSTOMER WHERE CUST_NAME=" + name;

		Customer customer = new Customer();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			resultSet.next();

			customer.setId(resultSet.getLong("ID"));
			customer.setCustomerName(resultSet.getString("CUST_NAME"));
			customer.setPassword(resultSet.getString("PASSWORD"));

		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.CUSTOMER_RETREIVE_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return customer;
	}
	/*
	 * This method updates customer in DB with given values
	 */
	@Override
	public void updateCustomer(Customer customer) throws ApplicationException {

		String query = "UPDATE CUSTOMER SET CUST_NAME=?, PASSWORD=? WHERE ID=?";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(3, customer.getId());
			
			statement.setString(1, customer.getCustomerName());
			statement.setString(2, customer.getPassword());
		
			statement.executeUpdate();
			
		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.CUSTOMER_UPDATE_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}
	/*
	 * This method delete customer from DB by ID
	 */
	@Override
	public void deleteCustomerById(Customer customer) throws ApplicationException {
		String query = "DELETE FROM CUSTOMER WHERE ID=?";
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(1, customer.getId());
			statement.executeUpdate();
			
		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.CUSTOMER_DELETE_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}
	/*This method provide login for customers 
	 * using customer name and password
	 * return true or false
	 * */
	@Override
	public boolean login(String customerName, String customerPassword) throws ApplicationException {
		String query = "SELECT CUST_NAME, PASSWORD FROM CUSTOMER WHERE CUST_NAME = ?";

		Customer customer = new Customer();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, customerName);
			resultSet = statement.executeQuery();
			resultSet.next();
			
			customer.setCustomerName(resultSet.getString("CUST_NAME"));
			customer.setPassword(resultSet.getString("PASSWORD"));

			if (!customerName.equals(customer.getCustomerName())  && !customerPassword.equals(customer.getPassword())) {
				
				return false;
			} else return true;
			
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.WRONG_CUSTOMER_NAME_OR_DOESNT_EXIST);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
	}
	/*
	 * This method check if customer exist in DB by NAME
	 */
	@Override
	public boolean isCustomerExistByName(String customerName) throws ApplicationException {

		String query = "SELECT CUST_NAME FROM CUSTOMER WHERE CUST_NAME=" + customerName;


		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			resultSet.next();


			if(!resultSet.next()) {
				return false;
			}
			return true;
			
		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.WRONG_CUSTOMER_NAME_OR_DOESNT_EXIST); 
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
	
	}
	/*
	 * This method check if customer exist in DB by ID
	 */
	@Override
	public boolean isCustomerExistById(Long id) throws ApplicationException {

		String query = "SELECT ID FROM CUSTOMER WHERE ID=" + id;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			resultSet.next();
			statement.executeQuery();

			if(!resultSet.next()) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.WRONG_CUSTOMER_NAME_OR_DOESNT_EXIST);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
}
	
}

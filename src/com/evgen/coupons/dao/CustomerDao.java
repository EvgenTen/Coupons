package com.evgen.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.evgen.coupons.beans.Company;
import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.enums.ErrorType;
import com.evgen.coupons.exceptions.ApplicationException;
import com.evgen.coupons.interfaces.dao.ICustomerDao;
import com.evgen.coupons.utils.JdbcUtils;

public class CustomerDao extends JdbcUtils implements ICustomerDao {
	PreparedStatement statement = null;
	Connection connection = null;
	ResultSet resultSet = null;
	
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
			throw new ApplicationException(e, ErrorType.CUSTOMER_CREATE_ERROR);

		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}

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
				System.out.println("Login or password incorrect");
				return false;
			} else return true;
			
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.WRONG_CUSTOMER_NAME_OR_DOESNT_EXIST);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
	}
}

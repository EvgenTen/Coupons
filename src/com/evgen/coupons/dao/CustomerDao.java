package com.evgen.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.evgen.coupons.beans.Customer;
import com.evgen.coupons.enums.ErrorType;
import com.evgen.coupons.exceptions.ApplicationException;
import com.evgen.coupons.interfaces.dao.iCustomerDao;
import com.evgen.coupons.utils.JdbcUtils;

public class CustomerDao extends JdbcUtils implements iCustomerDao {
	PreparedStatement statement;
	Connection connection;
	ResultSet resultSet;
	
	@Override
	public void customerCreate(Customer customer) throws ApplicationException {

		String query = "INSERT INTO CUSTOMER (ID, CUST_NAME, PASSWORD) " + "VALUES(?, ?, ?)";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);

			statement.setLong(1, customer.getId());
			statement.setString(2, customer.getCustomerName());
			statement.setString(3, customer.getPassword());

			statement.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR);

		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}

	@Override
	public List<Customer> getAllCustomers() throws ApplicationException {
		List<Customer> customerList = new ArrayList<>();

		String query = "SELECT ID, CUST_NAME, PASSWORD " + "FROM CUSTOMER";

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
			throw  new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return customerList;
	}

	@Override
	public Customer getById(Long id) throws ApplicationException {

		String query = "SELECT ID, CUST_NAME, PASSWORD WHERE ID=?";

		Customer customer = new Customer();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			customer.setId(resultSet.getLong("ID"));
			customer.setCustomerName(resultSet.getString("CUST_NAME"));
			customer.setPassword(resultSet.getString("PASSWORD"));

			statement.executeUpdate();
		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return customer;
	}

	@Override
	public void customerUpdate(Customer customer) throws ApplicationException {

		String query = "UPDATE CUSTOMER SET CUST_NAME=?, PASSWORD=? WHERE ID=?";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			
			statement.setString(1, customer.getCustomerName());
			statement.setString(2, customer.getPassword());
			statement.setLong(3, customer.getId());

			statement.executeUpdate();
			
		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}

	@Override
	public void customerDeleteById(Customer customer) throws ApplicationException {
		String query = "DELETE FROM CUSTOMER WHERE ID=?";
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(1, customer.getId());
			statement.executeUpdate();
			
		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}

}

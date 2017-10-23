/*
 * This class is DAO Coupons class
 * works with coupons table in DB
 * receive parameters from controller layer
*/
package com.evgen.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.evgen.coupons.beans.Coupon;
import com.evgen.coupons.enums.CouponType;
import com.evgen.coupons.enums.ErrorType;
import com.evgen.coupons.exceptions.ApplicationException;
import com.evgen.coupons.interfaces.dao.ICouponsDao;
import com.evgen.coupons.utils.JdbcUtils;

public class CouponsDao extends JdbcUtils implements ICouponsDao {

	PreparedStatement statement = null; // Must be equal to NULL because resources
	Connection connection = null; // will be closed in JDBC after check.
	ResultSet resultSet = null;//
	
	/*
	 * This method creates coupon with given values in Coupons table
	 */
	@Override
	public void createCoupon(Coupon coupon) throws ApplicationException {

		String query = "INSERT INTO COUPON (ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE, COMP_ID) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);

			statement.setLong(1, coupon.getId());
			statement.setString(2, coupon.getTitle());
			statement.setString(3, coupon.getStartDate());
			statement.setString(4, coupon.getEndDate());
			statement.setDouble(5, coupon.getAmount());
			statement.setString(6, coupon.getType().name());
			statement.setString(7, coupon.getMessage());
			statement.setFloat(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.setLong(10, coupon.getCompanyId());

			statement.executeUpdate();

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_CREATE_ERROR); // Must be in try/catch then throw custom exception

		} finally {
			JdbcUtils.closeResources(connection, statement); // Closing resources
		}
	}
	/*
	 * This method receive List of coupons from DB returns coupons List
	 */
	@Override
	public List<Coupon> getAllCoupons() throws ApplicationException {
		List<Coupon> couponList = new ArrayList<>();

		String query = "SELECT * FROM COUPON";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getLong("ID"));
				coupon.setTitle(resultSet.getString("TITLE"));
				coupon.setStartDate(resultSet.getString("START_DATE"));
				coupon.setEndDate(resultSet.getString("END_DATE"));
				coupon.setAmount(resultSet.getInt("AMOUNT"));
				coupon.setType(CouponType.valueOf(resultSet.getString("TYPE")));
				coupon.setMessage(resultSet.getString("MESSAGE"));
				coupon.setPrice(resultSet.getFloat("PRICE"));
				coupon.setImage(resultSet.getString("IMAGE"));
				coupon.setCompanyId(resultSet.getLong("COMP_ID"));

				couponList.add(coupon);
			}
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.UNABLE_TO_RETREIVE_ALL_COUPONS);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return couponList;
	}
	/*
	 * This method receive coupon by ID from DB returns coupon
	 */
	@Override
	public Coupon getCouponById(Long id) throws ApplicationException {

		String query = "SELECT * FROM COUPON WHERE ID=" + id;

		Coupon coupon = new Coupon();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			resultSet.next();
			coupon.setId(resultSet.getLong("ID"));
			coupon.setTitle(resultSet.getString("TITLE"));
			coupon.setStartDate(resultSet.getString("START_DATE"));
			coupon.setEndDate(resultSet.getString("END_DATE"));
			coupon.setAmount(resultSet.getInt("AMOUNT"));
			coupon.setType(CouponType.valueOf(resultSet.getString("TYPE")));
			coupon.setMessage(resultSet.getString("MESSAGE"));
			coupon.setPrice(resultSet.getFloat("PRICE"));
			coupon.setImage(resultSet.getString("IMAGE"));
			coupon.setCompanyId(resultSet.getLong("COMP_ID"));
			statement.executeQuery();

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_RETREIVE_BY_ID_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return coupon;
	}
	/*
	 * This method updates coupon in DB with given values
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws ApplicationException {

		String query = "UPDATE COUPON SET TITLE=?, START_DATE=?, END_DATE=?, AMOUNT=?, TYPE=?, MESSAGE=?, PRICE=?, IMAGE=?, COMP_ID=? WHERE ID=?";
		   
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(10, coupon.getId());
			
			statement.setString(1, coupon.getTitle());
			statement.setString(2, coupon.getStartDate());
			statement.setString(3, coupon.getEndDate());
			statement.setInt(4, coupon.getAmount());
			statement.setString(5, coupon.getType().name());
			statement.setString(6, coupon.getMessage());
			statement.setFloat(7, coupon.getPrice());
			statement.setString(8, coupon.getImage());
			statement.setLong(9, coupon.getCompanyId());
			
			statement.executeUpdate();

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_UPDATE_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}

	}
	/*
	 * This method delete coupon from DB by ID
	 */
	@Override
	public void deleteCouponById(Coupon coupon) throws ApplicationException {

		String query = "DELETE FROM COUPON WHERE ID=?";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(1, coupon.getId());
			statement.executeUpdate();

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_DELETE_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}
	/*
	 * This method delete customer from DB by ID
	 */
	@Override
	public void deleteCouponByCompanyId(Coupon coupon) throws ApplicationException {

		String query = "DELETE FROM COUPON WHERE COMP_ID=";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(1, coupon.getCompanyId());
			statement.executeUpdate();

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_DELETE_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}
	/*
	 * This method receive list of coupons by TYPE from DB returns coupons
	 */
	@Override
	public List<Coupon> getCouponsByType(CouponType couponType) throws ApplicationException {
		List<Coupon> couponGetByType = new ArrayList<>();
	
		String query = "SELECT * FROM COUPON WHERE TYPE = ?";
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1,  couponType.name());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
			Coupon coupon = new Coupon();
			coupon.setId(resultSet.getLong("ID"));
			coupon.setTitle(resultSet.getString("TITLE"));
			coupon.setStartDate(resultSet.getString("START_DATE"));
			coupon.setEndDate(resultSet.getString("END_DATE"));
			coupon.setAmount(resultSet.getInt("AMOUNT"));
			coupon.setType(CouponType.valueOf(resultSet.getString("TYPE")));
			coupon.setMessage(resultSet.getString("MESSAGE"));
			coupon.setImage(resultSet.getString("IMAGE"));
			coupon.setCompanyId(resultSet.getLong("COMP_ID"));
			couponGetByType.add(coupon);
			}
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.UNABLE_TO_RETREIVE_COUPONS_BY_TYPE);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return couponGetByType;
	}
	/*
	 * This method receive list of coupons by COMPANY from DB returns coupons
	 */
	@Override
	public List<Coupon> getCouponsByCompany(long companyId) throws ApplicationException {
		
		List<Coupon> couponGetByCompany = new ArrayList<>();
		String query = "SELECT * FROM COUPON WHERE COMP_ID=" + companyId;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
			Coupon coupon = new Coupon();
			coupon.setId(resultSet.getLong("ID"));
			coupon.setTitle(resultSet.getString("TITLE"));
			coupon.setStartDate(resultSet.getString("START_DATE"));
			coupon.setEndDate(resultSet.getString("END_DATE"));
			coupon.setAmount(resultSet.getInt("AMOUNT"));
			coupon.setType(CouponType.valueOf(resultSet.getString("TYPE")));
			coupon.setMessage(resultSet.getString("MESSAGE"));
			coupon.setImage(resultSet.getString("IMAGE"));
			coupon.setCompanyId(resultSet.getLong("COMP_ID"));
			couponGetByCompany.add(coupon);
			}
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.UNABLE_TO_RETREIVE_COUPONS_BY_COMPANY);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return couponGetByCompany;
	}
	/*
	 * This method receive list of coupons by CUSTOMER from DB returns coupons
	 */
	@Override
	public List<Coupon> getCouponsByCustomer(long customerId) throws ApplicationException {
		List<Coupon> couponGetByCustomer = new ArrayList<>();
		String query = "SELECT * FROM COUPON WHERE ID IN (SELECT COUPON_ID FROM CUSTOMER_COUPON WHERE CUST_ID =?)" ;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(1, customerId);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getLong("ID"));
				coupon.setTitle(resultSet.getString("TITLE"));
				coupon.setStartDate(resultSet.getString("START_DATE"));
				coupon.setEndDate(resultSet.getString("END_DATE"));
				coupon.setAmount(resultSet.getInt("AMOUNT"));
				coupon.setType(CouponType.valueOf(resultSet.getString("TYPE")));
				coupon.setMessage(resultSet.getString("MESSAGE"));
				coupon.setImage(resultSet.getString("IMAGE"));
				coupon.setCompanyId(resultSet.getLong("COMP_ID"));
				couponGetByCustomer.add(coupon);

			}
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.UNABLE_TO_RETREIVE_COUPONS_BY_CUSTOMER);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return couponGetByCustomer;
	}
	/*
	 * This method delete expired coupons from DB
	 * using external scheduled task
	 */
	@Override
	public void deleteExpiredCoupons(String date) throws ApplicationException {
		
		String query = "DELETE FROM coupon WHERE (STR_TO_DATE (END_DATE,'%d.%m.%Y') <= STR_TO_DATE (?,'%d.%m.%Y'))";
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, date);
			statement.executeUpdate();

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_DELETE_ERROR);
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}
	/*
	 * This method creates coupons in joined table
	 * with two parameters
	 */
	@Override
	public void createCouponInJoinedTable(Long customerId, Long couponId) throws ApplicationException {

		String query = "INSERT INTO customer_coupon (COUPON_ID, CUST_ID) " + "VALUES(?, ?)";

		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);

			statement.setLong(1, customerId);
			statement.setLong(2, couponId);
			
			statement.executeUpdate();
			
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.JOIN_COUPON_CREATE_ERROR);

		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}
	/*
	 * This method delete coupons from joined table
	 * by customer ID
	 */
	@Override
	public void deleteCouponInJoinedTableByCustomerId(Long customerId) throws ApplicationException {

		String query = "DELETE FROM customer_coupon WHERE CUST_ID=?";
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(1, customerId);
			statement.executeUpdate();
			
		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.JOIN_COUPON_DELETE_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}
	/*
	 * This method delete coupons from joined table
	 * by customer ID
	 */
	@Override
	public void deleteCouponInJoinedTable(Long couponId) throws ApplicationException {
		String query = "DELETE FROM customer_coupon WHERE COUPON_ID=?";
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setLong(1, couponId);
			statement.executeUpdate();
			
		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.JOIN_COUPON_DELETE_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}
	/*
	 *This method check if coupon exist by ID in DB
	 */
	@Override
	public boolean isCouponExistById(Long id) throws ApplicationException {

		String query = "SELECT ID FROM COUPON WHERE ID=" + id;

		
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
			throw new ApplicationException(e, ErrorType.WRONG_COUPON_OR_DOESNT_EXIST);
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		
	}


}

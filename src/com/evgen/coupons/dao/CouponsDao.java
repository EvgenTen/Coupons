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

	PreparedStatement statement;
	Connection connection;
	ResultSet resultSet;
	
	@Override
	public void couponCreate(Coupon coupon) throws ApplicationException {

		String query = "INSERT INTO COUPON (ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE, COMP_ID) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			
			statement.setLong(1, coupon.getId());
			statement.setString(2, coupon.getTitle());
			statement.setString(3, coupon.getStartDate());
			statement.setString(4, coupon.getEndDate());
			statement.setInt(5, coupon.getAmount());
			statement.setString(6, coupon.getType().name().trim());
			statement.setString(7, coupon.getMessage());
			statement.setFloat(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.setLong(10, coupon.getCompanyId());

			statement.executeUpdate();

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR);

		} finally {
			JdbcUtils.closeResources(connection, statement);
		}
	}

	@Override
	public List<Coupon> getAllCoupons() throws ApplicationException{
		List<Coupon> couponList = new ArrayList<>();

		String query = "SELECT ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE, COMP_ID "
				+ "FROM COUPON";

	//	PreparedStatement statement = null;

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
				coupon.setId(resultSet.getLong("COMP_ID"));

				couponList.add(coupon);
			}
		} catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return couponList;
	}

	@Override
	public Coupon couponGetById(Long id) throws ApplicationException {


		String query = "ID, TITLE, START_DATE, END_DATE, AMOUNT, MESSAGE, PRICE, IMAGE, COMP_ID WHERE ID=?";

		Coupon coupon = new Coupon();
		try {
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);

			ResultSet resultSet = statement.executeQuery();

			coupon.setId(resultSet.getLong("ID"));
			coupon.setTitle(resultSet.getString("TITLE"));
			coupon.setStartDate(resultSet.getString("START_DATE"));
			coupon.setEndDate(resultSet.getString("END_DATE"));
			coupon.setAmount(resultSet.getInt("AMOUNT"));
			coupon.setMessage(resultSet.getString("MESSAGE"));
			coupon.setImage(resultSet.getString("IMAGE"));
			coupon.setId(resultSet.getLong("COMP_ID"));
			statement.executeUpdate();
			
		}  catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
		return coupon;
	}

	@Override
	public void couponUpdate(Coupon coupon) throws ApplicationException {

		String query = "UPDATE COUPON SET TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE, COMP_ID WHERE ID=0";

		try {
			statement = connection.prepareStatement(query);

			statement.setString(1, coupon.getTitle());
			statement.setString(2, coupon.getStartDate());
			statement.setString(3, coupon.getEndDate());
			statement.setInt(4, coupon.getAmount());
			statement.setString(5, coupon.getMessage());
			statement.setFloat(6, coupon.getPrice());
			statement.setString(7, coupon.getImage());
			statement.setLong(8, coupon.getCompanyId());
			statement.executeUpdate();

		}  catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement );
		}
	
	}

	@Override
	public void couponDeleteById(Long id) throws ApplicationException {
		
		Coupon coupon = new Coupon();
		String query = "DELETE FROM COUPON WHERE ID=?";

		try {
			statement = connection.prepareStatement(query);
			statement.setLong(1, coupon.getId());
			statement.executeUpdate();

		}   catch (Exception e) {
			throw  new ApplicationException(e, ErrorType.COUPON_CREATION_ERROR); 
		} finally {
			JdbcUtils.closeResources(connection, statement, resultSet);
		}
	}
}

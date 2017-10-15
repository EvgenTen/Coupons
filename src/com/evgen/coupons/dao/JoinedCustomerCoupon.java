package com.evgen.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.evgen.coupons.enums.ErrorType;
import com.evgen.coupons.exceptions.ApplicationException;
import com.evgen.coupons.utils.JdbcUtils;

public class JoinedCustomerCoupon extends JdbcUtils {

		PreparedStatement statement = null;
		Connection connection = null;		
		
		public void JoinCouponCreate(Long customerId, Long couponId) throws ApplicationException {

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
		
		public void JoinCouponDelete(Long couponId) throws ApplicationException {
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
		
		
}

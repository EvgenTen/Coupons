
import java.sql.SQLException;

import com.evgen.coupons.utils.JdbcUtils;

public class Tester {
	
	
	public static void main(String[] args) {
		
		JdbcUtils util = new JdbcUtils();
	
	
			try {
				util.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(util.getConnection() !=null) {
				System.out.println("Connection OK");
				}else
				System.out.println("Connection ERROR");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



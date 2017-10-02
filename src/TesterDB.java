import java.sql.*;

public class TesterDB {

	public static void main(String[] args) {
		System.out.println("MySQL Insert PreparedStatement Example.");
		Connection conn = null;
		PreparedStatement ptmt = null;
		// 3306 is the default port number of MySQL
		// 192.168.10.13 is host address of the MySQL database
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "couponsprojectdb?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "12345";
		try {
			// Load the driver
			Class.forName(driver);
			// Get a connection
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
			System.out.println("Connected to the database");
			// Create a query String
			String query = "INSERT INTO COMPANY(ID,COMP_NAME,PASSWORD,EMAIL) VALUES(?,?,?,?)";
			// Create a PreparedStatement
			ptmt = conn.prepareStatement(query);
			ptmt.setInt(1, 8);
			ptmt.setString(2, "Dragon");
			ptmt.setString(3, "1234567");
			ptmt.setString(4, "dfd@fgf.ru");
			//ptmt.executeUpdate();

			
			// Closing the connection
			ptmt.close();
			conn.close();
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not found Exception cought");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			System.out.println("Database Updated Successfully");
			System.out.println("Disconnected from database");
		}

	}

	}


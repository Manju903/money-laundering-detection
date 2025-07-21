package auth;
import db.DBConnection;
import java.sql.*;

public class Register {
	public static boolean registerUser(String username, String password){
		
		 try {
	            Connection conn = DBConnection.getConnection();
	            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
	            ps.setString(1, username);
	            ps.setString(2, password);
	            int result = ps.executeUpdate();
	            return result > 0;
	        } catch (SQLException e) {
	            System.out.println("Registration failed: " + e.getMessage());
	            return false;
	        }

	}

}

package auth;
import db.DBConnection;
import java.sql.*;

public class Login {
	public static int authenticateUser(String username, String password) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return -1;
    }

	
}

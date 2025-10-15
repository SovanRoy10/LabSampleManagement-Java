package dao;

import model.User;

import java.sql.*;

import config.DBConfig;

public class UserDao {

	Connection con = DBConfig.getCon();

	static final String insert_sql = "INSERT INTO users (username, email, u_password, phone_number) VALUES (?, ?, ?, ?)";
	static final String user_count_by_email_id = "SELECT COUNT(*) FROM users WHERE email=?";
	static final String get_username_by_email = "SELECT username FROM users WHERE email=?";
	static final String get_password_by_email_for_login = "SELECT u_password FROM users WHERE email=?";

	//--- Insert new user ---
	// --- Used in UserRegistration process ---
	
	public void insertUser(User user) throws SQLException {

		try {

			PreparedStatement ps = con.prepareStatement(insert_sql);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getu_password());
			ps.setString(4, user.getPhoneNumber());

			ps.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	
	
	// --- Check if user exists by email ---
	// --- Used in UserRegistration process to check same email id already exist or not ---
	
	public boolean existsByEmail(String email) throws SQLException {

		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(user_count_by_email_id);
			ps.setString(1, email);
			rs = ps.executeQuery();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		if(rs.next())
		{
			int count = rs.getInt(1);
			return count > 0;		// True if Email is already exist
		}
		
		return false;
	}

	
	
	
	// --- Fetch user_name by email ---
	// --- Used in UserLogin to fetch the User_name by email id if Login successful ---
	
	public String getUserNameByEmail(String email) throws SQLException {

		try {

			PreparedStatement ps = con.prepareStatement(get_username_by_email);

			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getString("username"); // fetching user_name
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	// --- Used in UserLogin check Email Id and Password are matching or not
	
	public boolean login(User user) throws SQLException {
		try {
			PreparedStatement ps = con.prepareStatement(get_password_by_email_for_login);
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String storedPassword = rs.getString("u_password");
				return storedPassword.equals(user.getu_password());
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}

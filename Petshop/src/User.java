import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
	private int UserID;
	private String Username;
	private String UserPassword;
	private String UserRole;
	private String UserEmail;
	private String UserGender;
	
	public User(int userID, String username, String password, String role, String email, String gender) {
		super();
		UserID = userID;
		Username = username;
		UserPassword = password;
		UserRole = role;
		UserEmail = email;
		UserGender = gender;
	}
	
	
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return UserPassword;
	}
	public void setPassword(String password) {
		UserPassword = password;
	}
	public String getRole() {
		return UserRole;
	}
	public void setRole(String role) {
		UserRole = role;
	}
	public String getEmail() {
		return UserEmail;
	}
	public void setEmail(String email) {
		UserEmail = email;
	}
	public String getGender() {
		return UserGender;
	}
	public void setGender(String gender) {
		UserGender = gender;
	}
	
	public boolean insert() {
		String query = "INSERT INTO user (Username, UserPassword, UserRole, UserEmail, UserGender) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = Connector.preparedStatement(query);		
		int count = 0;
		try {
			ps.setString(1, this.Username);
			ps.setString(2, this.UserPassword);
			ps.setString(3, this.UserRole);
			ps.setString(4, this.UserEmail);
			ps.setString(5, this.UserGender);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count > 0;
	}
	
	public static ArrayList<User> getUser(){
		ArrayList<User> users = new ArrayList<>();
		
		try {
			ResultSet rs = Connector.statement.executeQuery("SELECT userID, username, userPassword, userRole, userEmail, userGender FROM user");
			
			while(rs.next()) {
				int retrievedId = rs.getInt("userID");
				String retrievedUsername = rs.getString("username");
				String retrievedPassword = rs.getString("userPassword");
				String retrievedRole = rs.getString("userRole");
				String retrievedEmail = rs.getString("userEmail");
				String retrievedGender = rs.getString("userGender");
				
				User u = new User(retrievedId, retrievedUsername, retrievedPassword, retrievedRole, retrievedEmail, retrievedGender);
				
				users.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
}

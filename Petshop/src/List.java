import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class List {
	private int userID;
	private String username;
	private String userPassword;
	private String userRole;
	private String userEmail;
	private String userGender;
	public List(int userID, String username, String userPassword, String userRole, String userEmail,
			String userGender) {
		super();
		this.userID = userID;
		this.username = username;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userEmail = userEmail;
		this.userGender = userGender;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	
	public static ArrayList<List> getUser(){
		ArrayList<List> user = new ArrayList<>();
		
		try {
			ResultSet rs = Connector.statement.executeQuery("SELECT userID, username, userPassword, userRole, userEmail, userGender FROM user WHERE userRole = 'Staff'");
			
			while(rs.next()) {
				int uID = rs.getInt("userID");
				String uname = rs.getString("username");
				String uPassword = rs.getString("userPassword");
				String uRole = rs.getString("userRole");
				String uEmail = rs.getString("userEmail");
				String uGender = rs.getString("userGender");
				
				List u = new List(uID, uname, uPassword, uRole, uEmail, uGender);
				
				user.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
}

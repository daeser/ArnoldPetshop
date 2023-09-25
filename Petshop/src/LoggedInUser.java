import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoggedInUser {
	
	private static int UserID = 0;
	
	public LoggedInUser() {
		// untuk mancing
	}
	
	
	
	public LoggedInUser(int userID) {
		super();
		UserID = userID;
	}
	
	public static int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}
	
	public static String getUsername(){
		String Username = "";
		
		LoggedInUser s = new LoggedInUser();

		int c = s.getUserID();
		
		try {
			ResultSet rs = Connector.statement.executeQuery("SELECT username FROM user WHERE userID = " + s.getUserID());
			
			while(rs.next()) {
				String Retrievedusername = rs.getString("username");
				Username = Retrievedusername;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Username;
	}
	
}


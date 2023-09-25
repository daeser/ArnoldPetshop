import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Action {
	private int actionID;
	private String StaffName;
	private String animalType;
	private String petName;
	private int PetPrice;
	private String description;
	private String Date;
	public Action(int actionID, String staffName, String animalType, String petName, int petPrice, String description,
			String date) {
		super();
		this.actionID = actionID;
		StaffName = staffName;
		this.animalType = animalType;
		this.petName = petName;
		PetPrice = petPrice;
		this.description = description;
		Date = date;
	}
	
	public int getActionID() {
		return actionID;
	}
	public void setActionID(int actionID) {
		this.actionID = actionID;
	}
	public String getStaffName() {
		return StaffName;
	}
	public void setStaffName(String staffName) {
		StaffName = staffName;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public int getPetPrice() {
		return PetPrice;
	}
	public void setPetPrice(int petPrice) {
		PetPrice = petPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	public static ArrayList<Action> getActionMenu(){
		ArrayList<Action> actions = new ArrayList<>();
		
		try {
			ResultSet rs = Connector.statement.executeQuery("SELECT ac.ActionID, us.username, ac.animal, ac.PetName, ac.PetPrice, ac.description, ac.Date FROM action ac "
					+ "JOIN user us ON ac.userID = us.userID");
			
			while(rs.next()) {
				int RetrieveActionID= rs.getInt("ActionID");
				String RetrievedstaffName = rs.getString("username");
				String RetrievedAnimalType = rs.getString("animal");
				String RetrievedPetName = rs.getString("PetName");
				int RetrievedPetPrice = rs.getInt("PetPrice");
				String RetrievedDescription = rs.getString("description");
				String RetrievedDate = rs.getString("Date");
				
				Action a = new Action(RetrieveActionID, RetrievedstaffName, RetrievedAnimalType, RetrievedPetName, RetrievedPetPrice, RetrievedDescription, RetrievedDate);
				
				actions.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return actions;
	}
	
}

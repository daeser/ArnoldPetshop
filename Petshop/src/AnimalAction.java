import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalAction {
	private int actionID;
	private String StaffName;
	private int AnimalID;
	private String animal;
	private String description;
	private String Date;
	public AnimalAction(int actionID, String staffName, int animalID, String animal, String description, String date) {
		super();
		this.actionID = actionID;
		StaffName = staffName;
		AnimalID = animalID;
		this.animal = animal;
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

	public int getAnimalID() {
		return AnimalID;
	}

	public void setAnimalID(int animalID) {
		AnimalID = animalID;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
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

	public static ArrayList<AnimalAction> getAnimalActionMenu(){
		ArrayList<AnimalAction> Animalactions = new ArrayList<>();
		
		try {
			ResultSet rs = Connector.statement.executeQuery("SELECT aa.AnimalActionID, us.username, aa.AnimalID, aa.Animal, aa.description, aa.Date FROM animalaction aa "
					+ "JOIN user us ON aa.userID = us.userID");
			
			while(rs.next()) {
				int RetrieveActionID= rs.getInt("AnimalActionID");
				String RetrievedstaffName = rs.getString("username");
				int RetrievedanimalID = rs.getInt("AnimalID");
				String RetrievedAnimalType = rs.getString("Animal");
				String RetrievedDescription = rs.getString("description");
				String RetrievedDate = rs.getString("Date");
				
				AnimalAction a = new AnimalAction(RetrieveActionID, RetrievedstaffName, RetrievedanimalID, RetrievedAnimalType, RetrievedDescription, RetrievedDate);
				
				Animalactions.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Animalactions;
	}
	
}

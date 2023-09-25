import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Animal {
	private int AnimalID;
	private String AnimalType;
	
	
	
	public Animal(int animalID, String animalType) {
		super();
		AnimalID = animalID;
		AnimalType = animalType;
	}
	
	public int getAnimalID() {
		return AnimalID;
	}
	public void setAnimalID(int animalID) {
		AnimalID = animalID;
	}
	public String getAnimalType() {
		return AnimalType;
	}
	public void setAnimalType(String animalType) {
		AnimalType = animalType;
	}
	
	public static ArrayList<Animal> getAnimalTypeMenu(){
		ArrayList<Animal> animals = new ArrayList<>();
		
		try {
			ResultSet rs = Connector.statement.executeQuery("SELECT AnimalID, Animal FROM animal");
			
			while(rs.next()) {
				int RetrievedAnimalID = rs.getInt("AnimalID");
				String RetrievedAnimalName = rs.getString("Animal");
				
				Animal a = new Animal(RetrievedAnimalID, RetrievedAnimalName);
				
				animals.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return animals;
	}

}

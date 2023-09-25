import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pet {
	private int PetID;
	private int AnimalID;
	private String AnimalType;
	private String PetName;
	private int PetPrice;
	
	
	public Pet(int petID, int animalID, String animalType, String petName, int petPrice) {
		super();
		PetID = petID;
		AnimalID = animalID;
		AnimalType = animalType;
		PetName = petName;
		PetPrice = petPrice;
	}
	public int getPetID() {
		return PetID;
	}
	public void setPetID(int petID) {
		PetID = petID;
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
	public String getPetName() {
		return PetName;
	}
	public void setPetName(String petName) {
		PetName = petName;
	}
	public int getPetPrice() {
		return PetPrice;
	}
	public void setPetPrice(int petPrice) {
		PetPrice = petPrice;
	}
	
	public void delete() {
		String query1 = "DELETE FROM pet WHERE PetID = ?";
		PreparedStatement ps1 = Connector.preparedStatement(query1);
	
		try {
			ps1.setString(1, this.PetID +"");
			ps1.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		
	}
	
	public void insert() {
		String query = "INSERT INTO pet (PetID, AnimalID , PetName, PetPrice) VALUES (?,?,?,?)";
		PreparedStatement ps = Connector.preparedStatement(query);	
		try {
			ps.setString(1, this.PetID + "");
			ps.setString(2, this.AnimalID + "");
			ps.setString(3, this.PetName);
			ps.setString(4, this.PetPrice + "");
			ps.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void update() {
		
		String query = "UPDATE pet SET PetName  = ?, AnimalID = ?, PetPrice = ? WHERE PetID = ?";
		PreparedStatement ps = Connector.preparedStatement(query);	
		try {
			ps.setString(1, this.PetName);
			ps.setString(2, this.AnimalID + "");
			ps.setString(3, this.PetPrice +"");
			ps.setString(4, this.PetID + "");
			ps.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		
	}
	
	
	public static ArrayList<Pet> getPetMenu(){
		ArrayList<Pet> pets = new ArrayList<>();
		try {
			ResultSet rs = Connector.statement.executeQuery
					("SELECT p.PetID, an.AnimalID, an.Animal, p.PetName, p.PetPrice FROM pet p JOIN animal an ON p.AnimalID = an.AnimalID");
			
			while(rs.next()) {
				int retrievedPetID = rs.getInt("PetID");
				int retrievedAnimalID = rs.getInt("AnimalID");
				String retrievedAnimalType = rs.getString("Animal");
				String retrievedPetName = rs.getString("PetName");
				int retrievedPetPrice = rs.getInt("PetPrice");
				
				
				
			
				Pet p = new Pet(retrievedPetID, retrievedAnimalID, retrievedAnimalType, retrievedPetName, retrievedPetPrice);
				
				pets.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pets;
	}
}

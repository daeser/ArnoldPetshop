
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionDetail {
	private int TransactionID;
	private String PetName;
	private String AnimalName;
	private int Price;
	public TransactionDetail(int transactionID, String petName, String animalName, int price) {
		super();
		TransactionID = transactionID;
		PetName = petName;
		AnimalName = animalName;
		Price = price;
	}
	public int getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}
	public String getPetName() {
		return PetName;
	}
	public void setPetName(String petName) {
		PetName = petName;
	}
	public String getAnimalName() {
		return AnimalName;
	}
	public void setAnimalName(String animalName) {
		AnimalName = animalName;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public static ArrayList<TransactionDetail> getTransDetail(){
		ArrayList<TransactionDetail> transactiondetails = new ArrayList<>();
		
		LoggedInUser s = new LoggedInUser();
		TransactionIDdata d = new TransactionIDdata();
		int z = d.getTransactionID();
		int c = s.getUserID();
		
		try {
			ResultSet rs = Connector.statement.executeQuery("SELECT TransactionID, PetName, Animal, Price FROM transactiondetail WHERE TransactionID = " + z);
			
			while(rs.next()) {
				int RetrievedTransactionID = rs.getInt("TransactionID");
				String RetrievedPetName = rs.getString("PetName");
				String RetrievedAnimalName = rs.getString("Animal");
				int RetrievedPrice = rs.getInt("Price");
				
				TransactionDetail a = new TransactionDetail(RetrievedTransactionID, RetrievedPetName, RetrievedAnimalName, RetrievedPrice);
				transactiondetails.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return transactiondetails;
	}
	
}


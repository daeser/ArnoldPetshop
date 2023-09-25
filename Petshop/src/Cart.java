
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cart {
private String petName;
private int Price;

public Cart(String petName, int price) {
	super();
	this.petName = petName;
	Price = price;
}


public String getPetName() {
	return petName;
}


public void setPetName(String petName) {
	this.petName = petName;
}


public int getPrice() {
	return Price;
}


public void setPrice(int price) {
	Price = price;
}


public static ArrayList<Cart> getMenu(){
	ArrayList<Cart> carts = new ArrayList<>();
	
	LoggedInUser s = new LoggedInUser();
	int c = s.getUserID();
	
	try {
		ResultSet rs = Connector.statement.executeQuery("SELECT pt.PetName, pt.PetPrice,c.petID FROM cart c JOIN pet pt on c.petID=pt.petID  WHERE UserID = " + c );
		
		while(rs.next()) {
			String RetrievedPetName = rs.getString("PetName");
			
			int RetrievedPrice = rs.getInt("PetPrice");
							
			Cart a = new Cart(RetrievedPetName, RetrievedPrice );
			
			carts.add(a);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return carts;
}
}

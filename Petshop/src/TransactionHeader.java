
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionHeader {
	private int TransactionID;
	private String TransactionDate;
	private int PetBought;
	public TransactionHeader(int transactionID, String transactionDate, int petBought) {
		super();
		TransactionID = transactionID;
		TransactionDate = transactionDate;
		PetBought = petBought;
	}
	public int getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}
	public String getTransactionDate() {
		return TransactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}
	public int getPetBought() {
		return PetBought;
	}
	public void setPetBought(int petBought) {
		PetBought = petBought;
	}
	public static ArrayList<TransactionHeader> getTrans(){
		ArrayList<TransactionHeader> transactionheaders = new ArrayList<>();
		
		LoggedInUser s = new LoggedInUser();
		int c = s.getUserID();
		
		try {
			ResultSet rs = Connector.statement.executeQuery("SELECT th.TransactionID, th.userID, th.TransactionDate, COUNT(td.petID) FROM transactionheader th JOIN transactiondetail td on th.TransactionID=td.TransactionID "
					+ "WHERE th.userID = " + c + " GROUP BY th.TransactionID");
			
			while(rs.next()) {
				int RetrievedTransactionID = rs.getInt("TransactionID");
				String RetrievedTransactionDate = rs.getString("TransactionDate");
				int RetrievedQuantity = rs.getInt("COUNT(td.petID)");
				
				TransactionHeader a = new TransactionHeader(RetrievedTransactionID, RetrievedTransactionDate, RetrievedQuantity);
				transactionheaders.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return transactionheaders;
	}
}


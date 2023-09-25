

public class TransactionIDdata {
	
	private static int TransactionID = 0;
	
	public TransactionIDdata() {
		//WLAPLWAPLPWLAPLAWPLAWPWAP
	}
	
	public TransactionIDdata(int transactionID) {
		super();
		TransactionID = transactionID;
	}
	
	public static int getTransactionID() {
		return TransactionID;
	}


	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}
	
	
}

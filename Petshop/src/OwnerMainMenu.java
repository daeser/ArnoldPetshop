import javafx.application.Application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;

public class OwnerMainMenu extends Application{
	StackPane parent;
	Scene scene;
	VBox IFFrame, IFFrame2, VbAction, IFFrame3, VbList;
	BorderPane frame;
	GridPane GPIFrame,GPIFrame2,chooseStaffFrame, GPList;
	
	Label lblTitle,lblPilihTrans, lblID, lblName, lblPass, lblRole, lblEmail, lblGender, lblName2, lblPass2, lblRole2, lblEmail2, lblGender2;
	TextField tfName, tfPass, tfEmail, tfName2, tfPass2, tfEmail2;
	ComboBox<String> sID;
	ComboBox<String> sRole2;
	ComboBox<String> pilihTrans;
	ComboBox<String> gender, gender2;
	
	MenuBar menubar;
	Menu menu1, menu2;
	MenuItem staffList, staffAction, logout;
	
	Image image;
	ImageView imageview;
	
	TableView<List> SList;
	TableView<Action> SAction;
	TableView<AnimalAction> SAction2;
	
	
	Window internalFrame, internalFrame2;
	
	public void init() {
		parent = new StackPane();
		scene = new Scene(parent, 1000, 900);	
		frame = new BorderPane();
		chooseStaffFrame = new GridPane();
		
		lblTitle = new Label("");
		lblName = new Label("Name");
		lblPass = new Label("Password");
		lblRole = new Label("Role");
		lblEmail = new Label("Email");
		lblGender = new Label("Gender");
		
		image = new Image("File:1.jpg");
		imageview = new ImageView(image);
		imageview.setFitHeight(875);
		imageview.setFitWidth(1000);
		
		lblID = new Label("ID");
		lblName2 = new Label("Name");
		lblPass2 = new Label("Password");
		lblRole2 = new Label("Role");
		lblEmail2 = new Label("Email");
		lblGender2 = new Label("Gender");
		
		tfName = new TextField();
		tfPass = new TextField();
		tfEmail = new TextField();
		tfName2 = new TextField();
		tfPass2 = new TextField();
		tfEmail2 = new TextField();		
		
		sID = new ComboBox<String>();
		sRole2 = new ComboBox<String>();	
		gender = new ComboBox<String>();
		gender2 = new ComboBox<String>();
		
		
		menu1 = new Menu("Manage");
		menu2 = new Menu("Logout");
		menubar = new MenuBar(menu1, menu2);
		staffList = new MenuItem("Staff List");
		staffAction = new MenuItem("Staff Action");
		logout = new MenuItem("Logout");
		
		menu1.getItems().addAll(staffList, staffAction);
		menu2.getItems().add(logout);
		
		parent.getChildren().add(frame);
	}
	
	public void logout() {
		logout.setOnAction(p ->{
			Stage nxt = (Stage) frame.getScene().getWindow();
			nxt.close();

			Stage nextPanel = new Stage();
			
		try {
			new Main().start(nextPanel);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
		});
		
	}
	
	
	public void align() {
		frame.setTop(menubar);
		frame.setCenter(imageview);
	}
	
	public void InternalFrameAlign() {
		IFFrame.setSpacing(10);
	}
	
	
	public void InternalFrameStaffListTable() {
		IFFrame = new VBox();
		Label tfList = new Label("Staff List");
		
		SList = new TableView<List>();
		SList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		SList.setMaxHeight(600);
		SList.setMinSize(650, 150);
		
		TableColumn<List, Integer> cUserId = new TableColumn<>("User ID");
		cUserId.setMinWidth(75);
		cUserId.setPrefWidth(75);
		cUserId.setMaxWidth(75);
		cUserId.setCellValueFactory(new PropertyValueFactory<>("userID"));
		cUserId.setReorderable(false);	
		
		TableColumn<List, String> cUsername = new TableColumn<>("Username");
		cUsername.setMinWidth(200);
		cUsername.setPrefWidth(200);
		cUsername.setMaxWidth(200);
		cUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
		cUsername.setReorderable(false);
		
		TableColumn<List, String> cPassword = new TableColumn<>("Password");
		cPassword.setMinWidth(200);
		cPassword.setPrefWidth(200);
		cPassword.setMaxWidth(200);
		cPassword.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
		cPassword.setReorderable(false);
		
		TableColumn<List, String> cRole = new TableColumn<>("Role");
		cRole.setMinWidth(70);
		cRole.setPrefWidth(70);
		cRole.setMaxWidth(70);
		cRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		cRole.setReorderable(false);
		
		TableColumn<List, String> cEmail = new TableColumn<>("Staff Email");
		cEmail.setMinWidth(150);
		cEmail.setPrefWidth(200);
		cEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
		cEmail.setReorderable(false);
		
		TableColumn<List, String> cGender = new TableColumn<>("Gender");
		cGender.setMinWidth(80);
		cGender.setPrefWidth(80);
		cGender.setMaxWidth(80);
		cGender.setCellValueFactory(new PropertyValueFactory<>("userGender"));
		cGender.setReorderable(false);
		
		SList.getColumns().addAll(cUserId, cUsername, cPassword, cRole, cEmail, cGender);
		SList.getItems().clear();
		SList.getItems().addAll(List.getUser());
		
		
		IFFrame.getChildren().addAll(tfList, SList);
		InternalFrameAlign();
	}
	
	
	
	public boolean isinvalidPassword () {
		//Formula ngambil password
		String password = tfPass.getText();
		
		int valid = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				valid++;
			}
		}
		
		int valid2 = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				valid2++;
			}
		}
		
		int valid3 = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i))) {
				valid3++;
			}
		}
		
		if (password.isBlank()) {
			Alert Failed = new Alert(AlertType.ERROR);
			Failed.setHeaderText("Error !");
			Failed.setContentText("Password must be not blank");
			Failed.show();
			return true;
		}else if (valid == 0) {
			System.out.println("Password gaada lowercase");
			Alert Failed = new Alert(AlertType.ERROR);
			Failed.setHeaderText("Error !");
			Failed.setContentText("Password needs a lowercase");
			Failed.show();
			return true;
		}else if (valid2 == 0){
			System.out.println("Password gaada Uppercase");
			Alert Failed = new Alert(AlertType.ERROR);
			Failed.setHeaderText("Error !");
			Failed.setContentText("Password needs an uppercase");
			Failed.show();
			return true;
		}else if (valid3 == 0) {
			System.out.println("Password gaada Nomor");
			Alert Failed = new Alert(AlertType.ERROR);
			Failed.setHeaderText("Error !");
			Failed.setContentText("Password needs a Number");
			Failed.show();
			
			return true;
		}
		return false;
	}
	
	public boolean isinvalidPassword2 () {
		//Formula ngambil password
		String password = tfPass2.getText();
		
		int valid = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				valid++;
			}
		}
		
		int valid2 = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				valid2++;
			}
		}
		
		int valid3 = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i))) {
				valid3++;
			}
		}
		
		if (password.isBlank()) {
			return true;
		}else if (valid == 0) {
			System.out.println("Password gaada lowercase");
			Alert Failed = new Alert(AlertType.ERROR);
			Failed.setHeaderText("Error !");
			Failed.setContentText("Password needs a lowercase");
			Failed.show();
			return true;
		}else if (valid2 == 0){
			System.out.println("Password gaada Uppercase");
			Alert Failed = new Alert(AlertType.ERROR);
			Failed.setHeaderText("Error !");
			Failed.setContentText("Password needs an uppercase");
			Failed.show();
			return true;
		}else if (valid3 == 0) {
			System.out.println("Password gaada Nomor");
			Alert Failed = new Alert(AlertType.ERROR);
			Failed.setHeaderText("Error !");
			Failed.setContentText("Password needs a Number");
			Failed.show();
			
			return true;
		}
		return false;
	}
	
	public boolean isinvalidEmail () {
		
		//Formula nyari double .
		String email = tfEmail.getText().substring(tfEmail.getText().indexOf('@') + 1);
		int count = 0;
		for (int i = 0; i < tfEmail.getText().substring(tfEmail.getText().indexOf('@')+ 1).length(); i++) {
			if (email.charAt(i) == '.') {
				count++;
			}
		}
		
		if (tfEmail.getText().isBlank()) {
			System.out.println("Blank");
			return true;
		}else if (tfEmail.getText().startsWith("@"))  {
			System.out.println("Starts with @");
			return true;
		}else if (tfEmail.getText().startsWith(".") ){
			System.out.println("Starts with '.' ");
			return true;
		}else if (tfEmail.getText().endsWith("@") ) {
			System.out.println("Ends with @");
			return true;
		}else if (tfEmail.getText().endsWith(".") ) {
			System.out.println("Ends with .");
			return true;
		}else if (!tfEmail.getText().contains("@")) {
			System.out.println("Doesnt contains @");
			return true;
		}else if (!tfEmail.getText().contains(".")){
			System.out.println("Doesnt contains .");
			return true;
		}else if (tfEmail.getText().substring(tfEmail.getText().indexOf('@') + 1).contains("@")) {
			System.out.println("Contains @ after @");
			return true;
		}else if ((count > 1)) {
			System.out.println("Contains multiple . after @");
			return true;
		}else if (tfEmail.getText().substring(tfEmail.getText().indexOf('@') + 1, tfEmail.getText().length()).startsWith(".")) {
			System.out.println("gaboleh dempetan @ .");
			return true;
			
		}else if (!tfEmail.getText().substring(tfEmail.getText().indexOf('@') + 1, tfEmail.getText().length()).contains(".")) {
			System.out.println("Domain email ngilang");
			return true;
		}
			
		return false;
	}

	public boolean isinvalidEmail2 () {
	
	//Formula nyari double .
	String email = tfEmail2.getText().substring(tfEmail2.getText().indexOf('@') + 1);
	int count = 0;
	for (int i = 0; i < tfEmail2.getText().substring(tfEmail2.getText().indexOf('@')+ 1).length(); i++) {
		if (email.charAt(i) == '.') {
			count++;
		}
	}
	
	if (tfEmail2.getText().isBlank()) {
		System.out.println("Blank");
		return true;
	}else if (tfEmail2.getText().startsWith("@"))  {
		System.out.println("Starts with @");
		return true;
	}else if (tfEmail2.getText().startsWith(".") ){
		System.out.println("Starts with '.' ");
		return true;
	}else if (tfEmail2.getText().endsWith("@") ) {
		System.out.println("Ends with @");
		return true;
	}else if (tfEmail2.getText().endsWith(".") ) {
		System.out.println("Ends with .");
		return true;
	}else if (!tfEmail2.getText().contains("@")) {
		System.out.println("Doesnt contains @");
		return true;
	}else if (!tfEmail2.getText().contains(".")){
		System.out.println("Doesnt contains .");
		return true;
	}else if (tfEmail2.getText().substring(tfEmail2.getText().indexOf('@') + 1).contains("@")) {
		System.out.println("Contains @ after @");
		return true;
	}else if ((count > 1)) {
		System.out.println("Contains multiple . after @");
		return true;
	}else if (tfEmail2.getText().substring(tfEmail2.getText().indexOf('@') + 1, tfEmail2.getText().length()).startsWith(".")) {
		System.out.println("gaboleh dempetan @ .");
		return true;
		
	}else if (!tfEmail2.getText().substring(tfEmail2.getText().indexOf('@') + 1, tfEmail2.getText().length()).contains(".")) {
		System.out.println("Domain email ngilang");
		return true;
	}
		
	return false;
}
	
	
	public void InternalFrameInitStaffList() {
		GPList = new GridPane();
		Label desc = new Label("Please select one of the Staff to delete");
		GPList.add(lblName, 0, 0);		GPList.add(tfName, 1, 0);			GPList.add(lblID, 2, 0);		GPList.add(sID, 3, 0);
		GPList.add(lblPass, 0, 1);		GPList.add(tfPass, 1, 1);			GPList.add(lblName2, 2, 1);		GPList.add(tfName2, 3, 1);
																			GPList.add(lblPass2, 2, 2);		GPList.add(tfPass2, 3, 2);
		GPList.add(lblEmail, 0, 2);		GPList.add(tfEmail, 1, 2);			GPList.add(lblRole2, 2, 3);		GPList.add(sRole2, 3, 3);
		GPList.add(lblGender, 0, 3);	GPList.add(gender, 1, 3);			GPList.add(lblEmail2, 2, 4);	GPList.add(tfEmail2, 3, 4);	GPList.add(desc, 4, 5);
																			GPList.add(lblGender2, 2, 5);	GPList.add(gender2, 3, 5);
																			
		
		
		InternalFrameInitStaffListAlign();
	}
	
	public void InternalFrameInitStaffListAlign() {
		GPList.setHgap(10);
		GPList.setVgap(5);
		
		tfName.setPromptText("Username");
		tfPass.setPromptText("Password");
		tfEmail.setPromptText("E-Mail");
		tfName2.setPromptText("Username");
		tfPass2.setPromptText("Password");
		tfEmail2.setPromptText("E-Mail");
		
		
		gender.getItems().add("Male");
		gender.getItems().add("Female");
		gender.setPromptText("Gender");
		
		sRole2.getItems().add("Staff");
		sRole2.getItems().add("Customer");
		sRole2.setPromptText("Role");
		
		gender2.getItems().add("Male");
		gender2.getItems().add("Female");
		gender2.setPromptText("Gender");
		
		
		gender.setMaxWidth(250);
		sID.setMaxWidth(250);
		sID.setPromptText("User ID");
		sRole2.setMaxWidth(250);
		gender2.setMaxWidth(250);
	}
	
	
	public void InternalFrameStaffList() {
		staffList.setOnAction(e -> {
			GPIFrame = new GridPane();
			VbList = new VBox();
			HBox HbButton = new HBox();
			
			GPIFrame.add(IFFrame, 0, 0);
			GPIFrame.setHgap(1000000000);
			
			internalFrame = new Window("Staff List");
			internalFrame.setPrefSize(1000, 900);
			
			internalFrame.setLayoutX(0);
			internalFrame.setLayoutY(25);
			internalFrame.setMovable(false);
			
			Button insert = new Button("Insert");
			insert.setPrefSize(100, 50);
			Button update = new Button("Update");
			update.setPrefSize(100, 50);
			Button delete = new Button("Delete");
			delete.setPrefSize(100, 50);
			/////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////
			insert.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			update.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			delete.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			delete.setOnMouseEntered(p ->{
				delete.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
			});
			delete.setOnMouseExited(p ->{
				delete.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			});
			
			update.setOnMouseEntered(p ->{
				update.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
			});
			update.setOnMouseExited(p ->{
				update.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			});
			
			insert.setOnMouseEntered(p ->{
				insert.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
			});
			insert.setOnMouseExited(p ->{
				insert.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			});
			/////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////
			sID.setOnMouseClicked(z -> {
				sID.getItems().clear();
				String a = "";
				try {
					ResultSet rs = Connector.statement.executeQuery("SELECT userID FROM user WHERE userRole = 'Staff'");
					while(rs.next()) {
						String AName = rs.getString("userID");
						a = AName;
						sID.getItems().addAll(a);
						}
					
					} catch (SQLException k) {
						// TODO Auto-generated catch block
						k.printStackTrace();
					}
				
			});
			
			
			insert.setOnAction(a ->{
				
				try {
				if (tfName.getText().isBlank()) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Username must be not blank");
					Failed.show();
				}else if (isinvalidPassword()) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Password must be valid");
					Failed.show();
				}else if (isinvalidEmail()){
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Email must be valid");
					Failed.show();
				}else if (gender.getSelectionModel().getSelectedItem() == null) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Must choose gender");
					Failed.show();
				}else {
					String query1 = "INSERT INTO user (userID, username, userPassword, userRole, userEmail, userGender) VALUES (?,?,?,?,?,?)";
					PreparedStatement ps1 = Connector.preparedStatement(query1);
				
					try {
						ps1.setString(1, "0");
						ps1.setString(2, tfName.getText() + "");
						ps1.setString(3, tfPass.getText() + "");
						ps1.setString(4, "Staff" + "");
						ps1.setString(5, tfEmail.getText() );
						ps1.setString(6, gender.getSelectionModel().getSelectedItem() +"" );
						ps1.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Alert Success = new Alert(AlertType.INFORMATION);
					Success.setHeaderText("Message");
					Success.setContentText("The Staff has been Inserted");
					Success.show();
					SList.getItems().clear();
					SList.getItems().addAll(List.getUser());
				}
				
				}catch (Exception f) {
					// TODO: handle exception
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Please input interger in Price");
					Failed.show();
				}
			});
			
			update.setOnAction(x ->{				
					if (sID.getSelectionModel().getSelectedItem() == null) {
						Alert Failed = new Alert(AlertType.ERROR);
						Failed.setHeaderText("Error !");
						Failed.setContentText("Please Select a user ID");
						Failed.show();
					}else if (tfName2.getText().isBlank()) {
						Alert Failed = new Alert(AlertType.ERROR);
						Failed.setHeaderText("Error !");
						Failed.setContentText("Staff must be not blank");
						Failed.show();
					}else if (isinvalidPassword2()){
						Alert Failed = new Alert(AlertType.ERROR);
						Failed.setHeaderText("Error !");
						Failed.setContentText("Password must be valid");
						Failed.show();
					}else if(tfPass2.getText().isBlank()) {						
						Alert Failed = new Alert(AlertType.ERROR);
						Failed.setHeaderText("Error !");
						Failed.setContentText("Password must be not blank");
						Failed.show();
					}else if (sRole2.getSelectionModel().getSelectedItem() == null) {
						Alert Failed = new Alert(AlertType.ERROR);
						Failed.setHeaderText("Error !");
						Failed.setContentText("Role should be picked");
						Failed.show();
					}else if (isinvalidEmail2()) {
						Alert Failed = new Alert(AlertType.ERROR);
						Failed.setHeaderText("Error !");
						Failed.setContentText("Email must be valid");
						Failed.show();
					}else if (gender2.getSelectionModel().getSelectedItem() == null) {
						Alert Failed = new Alert(AlertType.ERROR);
						Failed.setHeaderText("Error !");
						Failed.setContentText("Must choose gender");
						Failed.show();
					}else {
						
						String query1 = "UPDATE user SET username  = ?, userPassword = ?, userRole = ?, userEmail = ?, userGender = ? WHERE userID = ?";
						PreparedStatement ps1 = Connector.preparedStatement(query1);
					
						try {
							ps1.setString(1, tfName2.getText());
							ps1.setString(2, tfPass2.getText() + "");
							ps1.setString(3, sRole2.getSelectionModel().getSelectedItem() + "");
							ps1.setString(4, tfEmail2.getText() + "");
							ps1.setString(5, gender2.getSelectionModel().getSelectedItem() );
							ps1.setString(6, sID.getSelectionModel().getSelectedItem() +"" );
							ps1.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						sID.getItems().clear();
						String a = "";
						try {
							ResultSet rs = Connector.statement.executeQuery("SELECT userID FROM user WHERE userRole = 'Staff'");
							while(rs.next()) {
								String AName = rs.getString("userID");
								a = AName;
								sID.getItems().addAll(a);
								}
							
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						Alert Success = new Alert(AlertType.INFORMATION);
						Success.setHeaderText("Message");
						Success.setContentText("The Staff has been updated");
						Success.show();
						SList.getItems().clear();
						SList.getItems().addAll(List.getUser());
						
					}
				
			});
			delete.setDisable(true);
			SList.getSelectionModel().selectedItemProperty().addListener((item, oldVal, newVal) -> {
				if (item != null) {
					delete.setDisable(false);
				}else {
					delete.setDisable(true);
				}
			});
			
			delete.setOnAction(l ->{ 
				if (SList.getSelectionModel().getSelectedItem() == null) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Please Select a Staff");
					Failed.show();
				}else {
					
					String query1 = "DELETE FROM user WHERE userID = ?";
					PreparedStatement ps1 = Connector.preparedStatement(query1);
				
					try {
						ps1.setString(1, SList.getSelectionModel().getSelectedItem().getUserID() + "");
						ps1.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					sID.getItems().clear();
					String a = "";
					try {
						ResultSet rs = Connector.statement.executeQuery("SELECT userID FROM user WHERE userRole = 'Staff'");
						while(rs.next()) {
							String AName = rs.getString("userID");
							a = AName;
							sID.getItems().addAll(a);
							}
						
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					
					sID.setPromptText(" ");
					
					SList.getItems().clear();	
					SList.getItems().addAll(List.getUser());
					Alert Success = new Alert(AlertType.INFORMATION);
					Success.setHeaderText("Message");
					Success.setContentText("The Staff has been deleted");
					Success.show();
				}
				
				
				});
			
			HbButton.getChildren().addAll(insert, update, delete);
			HbButton.setSpacing(220);
			/////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////
			sID.setStyle("-fx-background-color: #000000;");
			sID.setButtonCell(new ListCell<String>() {
				  @Override
				  protected void updateItem(String item, boolean empty) {
				    super.updateItem(item, empty);
				      setText(item);
				      setStyle("-fx-text-fill: white;");
				    
				  }
				});
			sID.setOnMouseEntered(p ->{
				sID.setStyle("-fx-background-color: #808080;");
				sID.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: black;");
					    
					  }
					});
			});
			
			sID.setOnMouseExited(p ->{
				sID.setStyle("-fx-background-color: #000000;");
				sID.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: white;");
					    
					  }
					});
			});
			
			sRole2.setStyle("-fx-background-color: #000000;");
			sRole2.setButtonCell(new ListCell<String>() {
				  @Override
				  protected void updateItem(String item, boolean empty) {
				    super.updateItem(item, empty);
				      setText(item);
				      setStyle("-fx-text-fill: white;");
				    
				  }
				});
			sRole2.setOnMouseEntered(p ->{
				sRole2.setStyle("-fx-background-color: #808080;");
				sRole2.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: black;");
					    
					  }
					});
			});
			
			sRole2.setOnMouseExited(p ->{
				sRole2.setStyle("-fx-background-color: #000000;");
				sRole2.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: white;");
					    
					  }
					});
			});
			
			gender2.setStyle("-fx-background-color: #000000;");
			gender2.setButtonCell(new ListCell<String>() {
				  @Override
				  protected void updateItem(String item, boolean empty) {
				    super.updateItem(item, empty);
				      setText(item);
				      setStyle("-fx-text-fill: white;");
				    
				  }
				});
			gender2.setOnMouseEntered(p ->{
				gender2.setStyle("-fx-background-color: #808080;");
				gender2.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: black;");
					    
					  }
					});
			});
			
			gender2.setOnMouseExited(p ->{
				gender2.setStyle("-fx-background-color: #000000;");
				gender2.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: white;");
					    
					  }
					});
			});
			
			gender.setStyle("-fx-background-color: #000000;");
			gender.setButtonCell(new ListCell<String>() {
				  @Override
				  protected void updateItem(String item, boolean empty) {
				    super.updateItem(item, empty);
				      setText(item);
				      setStyle("-fx-text-fill: white;");
				    
				  }
				});
			gender.setOnMouseEntered(p ->{
				gender.setStyle("-fx-background-color: #808080;");
				gender.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: black;");
					    
					  }
					});
			});
			
			gender.setOnMouseExited(p ->{
				gender.setStyle("-fx-background-color: #000000;");
				gender.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: white;");
					    
					  }
					});
			});
			
			/////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////
			VbList.setSpacing(10);
			VbList.setPadding(new Insets(10));
			VbList.setStyle("-fx-background-color: White;");
			
			VbList.getChildren().addAll(GPIFrame, IFFrame, GPList, HbButton);
			
			internalFrame.getContentPane().getChildren().addAll(VbList);
			
			frame.setCenter(internalFrame);
			
		});
		

	}	
	
	public void InternalFrameActionAlign() {
		IFFrame2.setSpacing(10);
		IFFrame2.setPadding(new Insets(10));
	}

	

	public void InternalFrameStaffActionTable() {
		IFFrame2 = new VBox();
		
		Label tfAct = new Label("Manage Pet Listing Action");
		SAction = new TableView<Action>();
		SAction.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		SAction.setMaxHeight(600);
		SAction.setMinSize(650, 150);
		
//		TableColumn<Action, Integer> ActionId = new TableColumn<>("ActionID");
//		ActionId.setMinWidth(75);
//		ActionId.setPrefWidth(200);
//		ActionId.setCellValueFactory(new PropertyValueFactory<>("actionID"));
//		ActionId.setReorderable(false);
		
		TableColumn<Action, String> staffName = new TableColumn<>("StaffName");
		staffName.setMinWidth(100);
		staffName.setPrefWidth(200);
		staffName.setCellValueFactory(new PropertyValueFactory<>("StaffName"));
		staffName.setReorderable(false);	
		
		TableColumn<Action, String> animalType = new TableColumn<>("Animal Type");
		animalType.setMinWidth(150);
		animalType.setPrefWidth(200);
		animalType.setCellValueFactory(new PropertyValueFactory<>("animalType"));
		animalType.setReorderable(false);
		
		TableColumn<Action, String> petName = new TableColumn<>("Pet Name");
		petName.setMinWidth(150);
		petName.setPrefWidth(200);
		petName.setCellValueFactory(new PropertyValueFactory<>("petName"));
		petName.setReorderable(false);
		
		TableColumn<Action, Integer> price = new TableColumn<>("Price");
		price.setMinWidth(100);
		price.setPrefWidth(100);
		price.setCellValueFactory(new PropertyValueFactory<>("PetPrice"));
		price.setReorderable(false);
		
		TableColumn<Action, String> description = new TableColumn<>("Description");
		description.setMinWidth(100);
		description.setPrefWidth(100);
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
		description.setReorderable(false);
		
		TableColumn<Action, String> Date = new TableColumn<>("Date");
		Date.setMinWidth(75);
		Date.setPrefWidth(75);
		Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
		Date.setReorderable(false);
		
		
		SAction.getColumns().addAll(staffName, animalType,petName,price, description, Date);
		SAction.getItems().clear();
		SAction.getItems().addAll(Action.getActionMenu());
		
		
		IFFrame2.getChildren().addAll(tfAct,SAction);
	}
	
	public void InternalFrameStaffActionTable2() {
		IFFrame3 = new VBox();
		
		Label tfAct2 = new Label("Manage Animal List Action");
		SAction2 = new TableView<AnimalAction>();
		SAction2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		SAction2.setMaxHeight(600);
		SAction2.setMinSize(650, 150);
		
		TableColumn<AnimalAction, String> staffName = new TableColumn<>("StaffName");
		staffName.setMinWidth(100);
		staffName.setPrefWidth(200);
		staffName.setCellValueFactory(new PropertyValueFactory<>("StaffName"));
		staffName.setReorderable(false);	
		
		TableColumn<AnimalAction, Integer> animalID = new TableColumn<>("Animal ID");
		animalID.setMinWidth(75);
		animalID.setPrefWidth(200);
		animalID.setCellValueFactory(new PropertyValueFactory<>("AnimalID"));
		animalID.setReorderable(false);
		
		TableColumn<AnimalAction, String> animalName = new TableColumn<>("Animal Name");
		animalName.setMinWidth(75);
		animalName.setPrefWidth(200);
		animalName.setCellValueFactory(new PropertyValueFactory<>("animal"));
		animalName.setReorderable(false);
		
		TableColumn<AnimalAction, String> description = new TableColumn<>("Description");
		description.setMinWidth(150);
		description.setPrefWidth(200);
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
		description.setReorderable(false);
		
		TableColumn<AnimalAction, String> Date = new TableColumn<>("Date");
		Date.setMinWidth(75);
		Date.setPrefWidth(75);
		Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
		Date.setReorderable(false);
		
		SAction2.getColumns().addAll(staffName,animalID, animalName,description,Date);
		SAction2.getItems().clear();
		SAction2.getItems().addAll(AnimalAction.getAnimalActionMenu());
		
		IFFrame3.getChildren().addAll(tfAct2,SAction2);
	}
	
	public void InternalFrameStaffAction() {
		staffAction.setOnAction(e -> {
			if (frame.getChildren().contains(internalFrame)) {
				internalFrame.close();
			}
			GPIFrame2 = new GridPane();		
			
			VbAction= new VBox();
			VbAction.setSpacing(20);
			VbAction.setPadding(new Insets(20));
			VbAction.getChildren().addAll(IFFrame2, IFFrame3);
			VbAction.setStyle("-fx-background-color: White;");
			internalFrame2 = new Window("Staff Action");
			internalFrame2.setPrefSize(1000, 800);
			
			internalFrame2.setLayoutX(0);
			internalFrame2.setLayoutY(25);
			internalFrame2.setMovable(false);
			
			internalFrame2.getContentPane().getChildren().add(VbAction);
			
			frame.setCenter(internalFrame2);
			
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Connector.init();
		
		init();
		align();
		logout();
		InternalFrameStaffListTable();
		InternalFrameStaffActionTable();
		InternalFrameStaffActionTable2();		
		InternalFrameInitStaffList();		
		InternalFrameStaffList();
		InternalFrameStaffAction();		
		
		arg0.setScene(scene);
		arg0.show();
	}

}

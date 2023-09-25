import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.util.Util;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application{
	
		StackPane mainpane;
		VBox vbmain, vbextras, vbTitle, vbFooter;
		
		Label lblTitle, lblUsername, lblPassword, lblinformre ,lblRegister, TitleSpacing;
		
		TextField tfUsername;
		
		PasswordField pfPassword;
		
		Button btnLogin;
		
		Scene scene;
		
		Image image;
		ImageView imageview;
		
//		ArrayList<LoggedInUser> LoggedArr = new ArrayList<>();
		
		public void init() {
			mainpane = new StackPane();
			
			lblTitle = new Label("Login");
			TitleSpacing = new Label(" ");
			
			lblUsername = new Label("Username");
			lblPassword = new Label("Password");
			lblinformre = new Label("Don't have an Account ?");
			lblRegister = new Label("Register here !");
			
			image = new Image("File:1.jpg");
			imageview = new ImageView(image);
			imageview.setFitHeight(600);
			imageview.setFitWidth(800);
			
			
			tfUsername = new TextField();
			pfPassword = new PasswordField();
			
			btnLogin = new Button("Login");
			
			scene = new Scene(mainpane, 800, 600);
			
			vbmain = new VBox();
			vbTitle = new VBox();
			vbextras = new VBox();
			vbFooter = new VBox();
			
			vbFooter.getChildren().addAll(lblinformre, lblRegister);
			vbTitle.getChildren().addAll(lblTitle,TitleSpacing);
			vbextras.getChildren().addAll(lblUsername, tfUsername, lblPassword, pfPassword);
			vbmain.getChildren().addAll(vbTitle,vbextras,btnLogin, vbFooter);
			mainpane.getChildren().addAll(imageview, vbmain );
		}
		
		public void align() {
			lblTitle.setFont(Font.font("",FontWeight.BOLD,40));
			lblUsername.setFont(Font.font(16));
			lblPassword.setFont(Font.font(16));
			lblRegister.setFont(Font.font("",FontWeight.BOLD,16));
			
			tfUsername.setMaxWidth(300);
			pfPassword.setMaxWidth(300);
			
			btnLogin.setPrefWidth(100);
			btnLogin.setPrefHeight(35);
			btnLogin.setFont(Font.font(16));
			
			vbTitle.setSpacing(10);
			vbmain.setSpacing(20);
			vbextras.setSpacing(10);
			
			vbTitle.setAlignment(Pos.CENTER);
			vbextras.setAlignment(Pos.CENTER);
			vbmain.setAlignment(Pos.CENTER);
			vbFooter.setAlignment(Pos.CENTER);
			
			mainpane.setPadding(new Insets(120));
			
			vbmain.setStyle("-fx-background-color: #FFFFFF");
			btnLogin.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		}
		
		public int loginPU() {
			int UserID = 0;
			for(User u : User.getUser()) {
				if (u.getUsername().equals(tfUsername.getText()) && u.getPassword().equals(pfPassword.getText())) {
					UserID = u.getUserID();
					
				}
			}
			
			return UserID;
		}
		
		public void warnawarni() {
			lblRegister.setOnMouseEntered(p ->{
				lblRegister.setStyle("-fx-text-fill: #808080;");
			});
			lblRegister.setOnMouseExited(p ->{
				lblRegister.setStyle("-fx-text-fill: black;"); 
			});
			btnLogin.setOnMouseEntered(p ->{
				btnLogin.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
			});
			btnLogin.setOnMouseExited(p ->{
				btnLogin.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			});
			
		}
		
		public void cmd() {
			btnLogin.setOnMouseClicked(e ->{
//				boolean validator = false;
//				for(User u : User.getUser()) {
//					if (u.getUsername().equals(tfUsername.getText()) && u.getPassword().equals(pfPassword.getText())) {
//						validator = true;
//						
//						break;
//					}
//				}
				
				String username = tfUsername.getText();
				String pass = pfPassword.getText();
				ResultSet rs;
				
				String query2 = String.format("SELECT username, userPassword, userRole FROM user WHERE username = ? AND userPassword = ?");
				PreparedStatement ps2 = Connector.preparedStatement(query2);
				String Role ="";
				try {
					ps2.setString(1, username);
					ps2.setString(2, pass);
					rs = ps2.executeQuery();
					while (rs.next()) {
						Role = rs.getString("userRole");
					}
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
						if (Role.equals("Customer")){
							LoggedInUser s = new LoggedInUser(loginPU());
							System.out.println("Logged in as UserID : " + s.getUserID());
							System.out.println("Role = Customer");
							
							s.setUserID(loginPU());
							
							Stage nxt = (Stage) lblTitle.getScene().getWindow();
							nxt.close();

							Stage nextPanel = new Stage();
							
							try {
								new CustomerMainMenu().start(nextPanel);
							} catch (Exception b) {
							// TODO: handle exception
							b.printStackTrace();
							}
						} else if(Role.equals("Staff")) {
							LoggedInUser s = new LoggedInUser(loginPU());
							System.out.println("Logged in as UserID : " + s.getUserID());
							System.out.println("Role = Staff");
							
							s.setUserID(loginPU());
							
							Stage nxt = (Stage) lblTitle.getScene().getWindow();
							nxt.close();
							
							Stage nextPanel = new Stage();
							
						try {
							new StaffMainMenu().start(nextPanel);
						} catch (Exception p) {
							// TODO: handle exception
							p.printStackTrace();
						}
						}else if (Role.equals("Owner")) {
							LoggedInUser s = new LoggedInUser(loginPU());
							System.out.println("Logged in as UserID : " + s.getUserID());
							System.out.println("Role = Owner");
							
							Stage nxt = (Stage) lblTitle.getScene().getWindow();
							nxt.close();
							
							Stage nextPanel = new Stage();
							
							try {
								new OwnerMainMenu().start(nextPanel);
							} catch (Exception p) {
								// TODO: handle exception
								p.printStackTrace();
							}
						}else {
							Alert Failed = new Alert(AlertType.ERROR);
							Failed.setHeaderText("Error");
							Failed.setContentText("Please fill the username field / Please Register if you do not have any account");
							Failed.show();
						}
			});
			
//				if(validator) {
//					//Command buat ke halaman home
//					String role = "";
//					for(User u : User.getUser()) {
//						if (u.getUsername().equals(tfUsername.getText()) && u.getPassword().equals(pfPassword.getText())) {
//							role = u.getRole();
//							if (role.equals("Customer")) {
//								
//								LoggedInUser s = new LoggedInUser(loginPU());
//								System.out.println("Logged in as UserID : " + s.getUserID());
//								System.out.println("Role = Customer");
//								
//								s.setUserID(loginPU());
//								
//								
//								Stage nxt = (Stage) lblTitle.getScene().getWindow();
//								nxt.close();
//
//								Stage nextPanel = new Stage();
//								
//							try {
//								new CustomerMainMenu().start(nextPanel);
//							} catch (Exception b) {
//								// TODO: handle exception
//								b.printStackTrace();
//							}
//							
//							}else if (role.equals("Staff")) {
//								LoggedInUser s = new LoggedInUser(loginPU());
//								System.out.println("Logged in as UserID : " + s.getUserID());
//								System.out.println("Role = Staff");
//								
//								s.setUserID(loginPU());
//								
//								Stage nxt = (Stage) lblTitle.getScene().getWindow();
//								nxt.close();
//								
//								Stage nextPanel = new Stage();
//								
//							try {
//								new StaffMainMenu().start(nextPanel);
//							} catch (Exception p) {
//								// TODO: handle exception
//								p.printStackTrace();
//							}
//							
//							}else if (role.equals("Owner")) {
//								
//								LoggedInUser s = new LoggedInUser(loginPU());
//								System.out.println("Logged in as UserID : " + s.getUserID());
//								System.out.println("Role = Owner");
//								
//								Stage nxt = (Stage) lblTitle.getScene().getWindow();
//								nxt.close();
//								
//								Stage nextPanel = new Stage();
//								
//								try {
//									new OwnerMainMenu().start(nextPanel);
//								} catch (Exception p) {
//									// TODO: handle exception
//									p.printStackTrace();
//								}
//							}
//						}
//					}
//					
//					
//				}else {
//					Alert Failed = new Alert(AlertType.ERROR);
//						Failed.setHeaderText("Error");
//						Failed.setContentText("Please fill the username field / Please Register if you do not have any account");
//						Failed.show();
//					}
//		});
			///////////////////////////////////////////////////////
			
			lblRegister.setOnMouseClicked(a ->{
				Stage nxt = (Stage) lblTitle.getScene().getWindow();
				nxt.close();

				Stage nextPanel = new Stage();
				
			try {
				new RegisterPage().start(nextPanel);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
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
		warnawarni();
		cmd();
		
		
		for(User u : User.getUser()) {
			System.out.println(u.getUserID() + " | " + u.getUsername() + " | " + u.getPassword() + " | " + u.getRole() + " | " + u.getGender());
		}
		System.out.println(" ");
		
		arg0.setScene(scene);
		arg0.setTitle("Arnold's PetShop");
		arg0.show();
	}

}

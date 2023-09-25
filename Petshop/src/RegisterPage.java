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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterPage extends Application {

	StackPane mainpane;
	VBox vbmain, vbextras, vbTitle, vbQuestion;
	
	Label lblTitle, lblUsername, lblPassword, lblconPass, lblLogin, lblEmail, lblGender, TitleSpacing, lblquestion;
	
	TextField tfUsername, tfEmail;
	
	PasswordField pfPassword, pfconPass;
	
	Button btnRegister;
	
	FlowPane genderpane;
	RadioButton btnMale, btnFemale;
	ToggleGroup genderGroup;
	
	Image image;
	ImageView imageview;
	
	Scene scene;
	
	public void init() {
		
		
		mainpane = new StackPane();
		
		lblTitle = new Label("Register");
		TitleSpacing = new Label(" ");
		
		lblUsername = new Label("Username :");
		lblPassword = new Label("Password :");
		lblconPass = new Label("Confirm Password :");
		lblEmail = new Label("Email :");
		lblGender = new Label("Gender :");
		lblquestion = new Label("Already have an account ?");
		lblLogin = new Label("Login here !");
		
		image = new Image("File:1.jpg");
		imageview = new ImageView(image);
		imageview.setFitHeight(600);
		imageview.setFitWidth(800);
		
		tfUsername = new TextField();
		tfEmail = new TextField();
		
		pfconPass = new PasswordField();
		pfPassword = new PasswordField();
		
		btnRegister = new Button("Register");
		
		btnFemale = new RadioButton("Female");
		btnMale = new RadioButton("Male");
		genderpane = new FlowPane(btnFemale,btnMale);
		genderGroup = new ToggleGroup();
		genderGroup.getToggles().addAll(btnFemale,btnMale);
		
		scene = new Scene(mainpane, 800, 600);
		
		vbmain = new VBox();
		vbQuestion = new VBox();
		vbTitle = new VBox();
		vbextras = new VBox();
		
		vbTitle.getChildren().addAll(lblTitle,TitleSpacing);
		vbextras.getChildren().addAll(lblUsername, tfUsername, lblPassword, pfPassword, lblconPass, pfconPass, lblEmail, tfEmail, lblGender, genderpane);
		vbQuestion.getChildren().addAll(lblquestion, lblLogin);
		vbmain.getChildren().addAll(vbTitle,vbextras,btnRegister, vbQuestion);
		mainpane.getChildren().addAll(imageview, vbmain);
	}
	
	public void align() {
		lblTitle.setFont(Font.font("",FontWeight.MEDIUM,30));
		lblUsername.setFont(Font.font(16));
		lblPassword.setFont(Font.font(16));
		lblconPass.setFont(Font.font(16));
		lblLogin.setFont(Font.font("",FontWeight.BOLD,16));
		lblEmail.setFont(Font.font(16));
		lblGender.setFont(Font.font(16));
		lblquestion.setFont(Font.font(16));
		
		tfUsername.setMaxWidth(300);
		pfPassword.setMaxWidth(300);
		pfconPass.setMaxWidth(300);
		tfEmail.setMaxWidth(300);
		
		btnRegister.setPrefWidth(100);
		btnRegister.setPrefHeight(35);
		btnRegister.setFont(Font.font(14));
		
		vbTitle.setSpacing(0);
		vbmain.setSpacing(10);
		vbextras.setSpacing(10);
		genderpane.setHgap(15);
		
		genderpane.setAlignment(Pos.CENTER);
		vbTitle.setAlignment(Pos.CENTER);
		vbextras.setAlignment(Pos.CENTER);
		vbQuestion.setAlignment(Pos.CENTER);
		vbmain.setAlignment(Pos.CENTER);
		
		mainpane.setPadding(new Insets(35));
		
		vbmain.setStyle("-fx-background-color: #FFFFFF");
		btnRegister.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
	}
	public void warnawarni() {
		btnRegister.setOnMouseEntered(p ->{
			btnRegister.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
		});
		btnRegister.setOnMouseExited(p ->{
			btnRegister.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		});
		lblLogin.setOnMouseEntered(p ->{
			lblLogin.setStyle("-fx-text-fill: #808080;");
		});
		lblLogin.setOnMouseExited(p ->{
			lblLogin.setStyle("-fx-text-fill: black;"); 
		});
		
	}
	
	public void cmd() {
		
		
		
		//Pencet tombol regis
	
			btnRegister.setOnMouseClicked(a -> {
				
				RadioButton selected = (RadioButton) genderGroup.getSelectedToggle();
				if (tfUsername.getText().isBlank()) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Username can't be empty !");
					Failed.show();
				}else if (isinvalidPassword()) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Password is invalid or empty !");
					Failed.show();
					
				}else if ((pfconPass.getText().isBlank() )) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Password must be the same !");
					Failed.show();
						
				}else if (!pfconPass.getText().equals(pfPassword.getText())) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Password must be the same !");
					Failed.show();
					
				}else if (isinvalidEmail()){
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Please Enter a valid email");
					Failed.show();
					
				}else if (selected == null) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Please Pick a gender");
					Failed.show();
				}else {
					RadioButton genderselected = (RadioButton) genderGroup.getSelectedToggle();
					User u = new User(0 ,tfUsername.getText(), pfPassword.getText(), "Customer", tfEmail.getText(), genderselected.getText());
				boolean success = u.insert();
				
				if (success) {
					System.out.println("Data inserted successfully");
					}
				
					Alert Success = new Alert(AlertType.INFORMATION);
					Success.setHeaderText("Message");
					Success.setContentText("You have been successfully registered");
					Success.show();
					
					Success.setOnCloseRequest(g -> {
						Stage nxt = (Stage) lblTitle.getScene().getWindow();
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
				
			});
			
		//Balik ke halaman Login
		lblLogin.setOnMouseClicked(a ->{
			Stage nxt = (Stage) lblTitle.getScene().getWindow();
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
	public boolean isinvalidPassword () {
		//Formula ngambil password
		String password = pfPassword.getText();
		
		int valid = 0;
		for (int i = 0; i < pfPassword.getText().length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				valid++;
			}
		}
		
		int valid2 = 0;
		for (int i = 0; i < pfPassword.getText().length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				valid2++;
			}
		}
		
		int valid3 = 0;
		for (int i = 0; i < pfPassword.getText().length(); i++) {
			if (Character.isDigit(password.charAt(i))) {
				valid3++;
			}
		}
		
		if (pfPassword.getText().isBlank()) {
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
	
	public static void main(String[] args) {
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
		arg0.setScene(scene);
		arg0.setTitle("Arnold's PetShop (Displaying Register Page)");
		arg0.show();
	}
}

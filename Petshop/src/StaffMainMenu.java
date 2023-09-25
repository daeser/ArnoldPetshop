import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;

public class StaffMainMenu extends Application{
	StackPane mainpane, formHandler, background;
	GridPane GPForm2;
	
	Image image;
	ImageView imageview;
	
	Random rand;
	
	Label lbltitle, lblAnimalisting;
	Label insertAnimal,lblchoosewhichone;
	Label lblpetlist;
	Label lblwelcometitle;
	
	MenuBar menubar;
	Menu menu1, menu2;
	MenuItem ManagePetListing, ManageAnimal, logoud;
	
	Button btnInsertAnimaltype, btnUpdateAnimaltype, btnDeleteAnimaltype;
	Button btnInsert, btnUpdate, btnDelete;
	
	TextField tfAnimalType, tfAnimalTypeupdate;
	
	Window internalFrame, internalFrame2;
	
	FlowPane FPFrame, FPGPanimaltype;
	
	BorderPane frame;
	
	VBox Blank;
	
	VBox IFFrame, IFFrame2;
	
	Scene scene;
	
	TableView<Animal> AnimalTable;
	TableView<Pet> PetTable;
	ComboBox<String> CBAnimalName;
	
//	TableView<Concert> ConcertTable;
	public void init() {
		mainpane = new StackPane();
		formHandler = new StackPane();
		background = new StackPane();
		
		lblAnimalisting = new Label("Animal Listing");
		insertAnimal = new Label("Insert a new Animal here !");
		lblpetlist = new Label("Pet List");
		lblwelcometitle = new Label();
		lblwelcometitle.setFont(Font.font("", FontWeight.BOLD, 24));
		lblwelcometitle.setStyle("-fx-text-fill: GRAY;");
		DropShadow ds = new DropShadow();
		ds.setColor(Color.LIGHTGRAY);
		ds.setSpread(3.0);
		ds.setOffsetX(1);
		ds.setOffsetY(1);
		ds.setRadius(1);
		lblwelcometitle.setEffect(ds);
		
		image = new Image("File:1.jpg");
		imageview = new ImageView(image);
		imageview.setFitHeight(775);
		imageview.setFitWidth(1000);
		
		rand = new Random();
		
		IFFrame = new VBox();
		IFFrame2 = new VBox();
		Blank = new VBox();
		
		FPGPanimaltype = new FlowPane();
		
		GPForm2 = new GridPane();
		
		FPFrame = new FlowPane();
		
		lbltitle = new Label("");
		lblchoosewhichone = new Label("Choose Which one to update");
		
		CBAnimalName = new ComboBox<>();
		CBAnimalName.setPromptText("Animal Type");
		
		frame = new BorderPane();
		
		tfAnimalType = new TextField();
		tfAnimalType.setPromptText("Animal Type");
		tfAnimalTypeupdate = new TextField();
		tfAnimalTypeupdate.setPromptText("New Animal Type");
		
		btnInsertAnimaltype = new Button("Insert");
		btnUpdateAnimaltype = new Button("Update");
		btnDeleteAnimaltype = new Button("Delete");
		btnDeleteAnimaltype.setPrefSize(100, 100);
		
		btnInsert = new Button("Insert");
		btnUpdate = new Button("Update");
		btnDelete = new Button("Delete");
		
		menu1 = new Menu("Manage");
		menu2 = new Menu("Logout");
		menubar = new MenuBar(menu1, menu2);
		ManagePetListing = new MenuItem("Manage Pet Listing");
		ManageAnimal = new MenuItem("Manage Animal");
		logoud = new MenuItem("Logout");
		
		background.getChildren().addAll(imageview,lblwelcometitle);
		
		scene = new Scene(mainpane, 1000, 800);
	
		menu1.getItems().addAll(ManagePetListing, ManageAnimal);
		menu2.getItems().add(logoud);
		
		
		mainpane.getChildren().add(frame);
		
	}
	
	public void align() {
		
	
//		Blank.setAlignment(Pos.CENTER);
		frame.setTop(menubar);
		
		
		
		frame.setCenter(background);
//		frame.setCenter();
//		menubar.setMaxWidth(133);
	
	}
	
	public void PetTable() {
		PetTable = new TableView<>();
		
		PetTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		PetTable.setMaxHeight(350);
		PetTable.setMinSize(800, 100);
		
		TableColumn<Pet, Integer> cPetID = new TableColumn<>("Pet ID");
		cPetID.setCellValueFactory(new PropertyValueFactory<>("PetID"));
		cPetID.setMinWidth(100);
		cPetID.setPrefWidth(PetTable.getWidth() / 2.0);
		cPetID.setMaxWidth(100);
		cPetID.setReorderable(false);
		
		TableColumn<Pet, String> cAnimalType = new TableColumn<>("Animal Type");
		cAnimalType.setCellValueFactory(new PropertyValueFactory<>("AnimalType"));
		cAnimalType.setMinWidth(130);
		cAnimalType.setMaxWidth(200);
		cAnimalType.setReorderable(false);
		
		TableColumn<Pet, String> cPetName = new TableColumn<>("Pet Name");
		cPetName.setMinWidth(140);
		cPetName.setCellValueFactory(new PropertyValueFactory<>("PetName"));
		cPetName.setReorderable(false);
		
		TableColumn<Pet, Integer> cPetPrice = new TableColumn<>("Pet Price");
		cPetPrice.setMinWidth(300);
		cPetPrice.setPrefWidth(PetTable.getWidth() / 2.0);
		cPetPrice.setMaxWidth(300);
		cPetPrice.setCellValueFactory(new PropertyValueFactory<>("PetPrice"));
		cPetPrice.setReorderable(false);
		
		TableColumn<Pet, Button> SelectCoulumn = new TableColumn<>("Select");
		SelectCoulumn.setMinWidth(60);
		SelectCoulumn.setMaxWidth(630);
		SelectCoulumn.setReorderable(false);
		
		PetTable.getColumns().addAll(cPetID, cAnimalType,cPetName ,cPetPrice, SelectCoulumn);
		PetTable.getItems().clear();
		PetTable.getItems().addAll(Pet.getPetMenu());
		
	}
	
	public void AnimalListingTable() {
		AnimalTable = new TableView<>();
		
		CBAnimalName.setPromptText("Animal Type");
		
		AnimalTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		AnimalTable.setMaxHeight(350);
		AnimalTable.setMinSize(800, 100);
		AnimalTable.setMaxSize(1000, 350);
		
		TableColumn<Animal, Integer> cAnimalID = new TableColumn<>("Animal ID");
		cAnimalID.setCellValueFactory(new PropertyValueFactory<>("AnimalID"));
		cAnimalID.setMinWidth(100);
		cAnimalID.setPrefWidth(AnimalTable.getWidth() / 2.0);
		cAnimalID.setMaxWidth(100);
		cAnimalID.setReorderable(false);
		
		TableColumn<Animal, String> cAnimalType = new TableColumn<>("Animal Type");
		cAnimalType.setCellValueFactory(new PropertyValueFactory<>("AnimalType"));
		cAnimalType.setMinWidth(100);
		cAnimalType.setMaxWidth(200);
		cAnimalType.setReorderable(false);
		
		TableColumn<Animal, Button> SelectCoulumn2 = new TableColumn<>("Select");
		SelectCoulumn2.setMinWidth(60);
		SelectCoulumn2.setMaxWidth(630);
		SelectCoulumn2.setReorderable(false);
		
		AnimalTable.getColumns().addAll(cAnimalID, cAnimalType, SelectCoulumn2);
		AnimalTable.getItems().clear();
		AnimalTable.getItems().addAll(Animal.getAnimalTypeMenu());
		
	}
	public void Frame2CMD() {
		lblwelcometitle.setText("Welcome, " + LoggedInUser.getUsername());
		
		btnInsertAnimaltype.setOnAction(e -> {
			String d = "";
			int Validateduplicateanimal = 0;
			try {
				ResultSet rs = Connector.statement.executeQuery("Select Animal FROM animal");
				
				while(rs.next()) {
					String AName = rs.getString("Animal");
					if (AName.equalsIgnoreCase(tfAnimalType.getText())) {
						Validateduplicateanimal++;
					}
					
					}
				
				} catch (SQLException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
			
			if (tfAnimalType.getText().length() < 3 || tfAnimalType.getText().length() > 15) {
				Alert Failed = new Alert(AlertType.ERROR);
				Failed.setHeaderText("Error !");
				Failed.setContentText("Animal Type has to be between 3 - 15 characters!");
				Failed.show();
				
			}else if (Validateduplicateanimal > 0) {
				Alert Failed = new Alert(AlertType.ERROR);
				Failed.setHeaderText("Error !");
				Failed.setContentText("Animal Type cannot be duplicate !");
				Failed.show();
				
			}else {
				
				String query1 = "INSERT INTO animal (AnimalID, Animal) VALUES (?,?)";
				PreparedStatement ps1 = Connector.preparedStatement(query1);
			
				try {
					ps1.setString(1, "0");
					ps1.setString(2, tfAnimalType.getText());
					ps1.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				CBAnimalName.getItems().clear();
				String b = "";
				try {
					ResultSet rs = Connector.statement.executeQuery("Select Animal FROM animal");
					
					while(rs.next()) {
						String AName = rs.getString("Animal");
						b = AName;
						if (AName.equalsIgnoreCase(tfAnimalType.getText())) {
							Validateduplicateanimal++;
						}
						CBAnimalName.getItems().addAll(b);
						}
					
					} catch (SQLException a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
				
				AnimalTable.getItems().addAll(Animal.getAnimalTypeMenu());
				
				LoggedInUser s = new LoggedInUser();
				int lastValues = AnimalTable.getItems().get(AnimalTable.getItems().size() - 1).getAnimalID();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				
				String query = "INSERT INTO animalaction (animalactionID, userID, AnimalID, Animal, description, Date) VALUES (?,?,?,?,?,?)";
				PreparedStatement ps = Connector.preparedStatement(query);
				
				try {
					ps.setString(1, "0");
					ps.setString(2, s.getUserID() + "");
					ps.setString(3, (lastValues+1)+ "");
					ps.setString(4, tfAnimalType.getText());
					ps.setString(5, "Inserted Animal");
					ps.setString(6, dtf.format(now));
					ps.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				AnimalTable.getItems().clear();
				AnimalTable.getItems().addAll(Animal.getAnimalTypeMenu());
				
				Alert Success = new Alert(AlertType.INFORMATION);
				Success.setHeaderText("Message");
				Success.setContentText("The Animal Has been Added");
				Success.show();
				
			}
		});
		
		CBAnimalName.setOnMouseClicked(e ->{
			CBAnimalName.getItems().clear();
			String b = "";
			try {
				ResultSet rs = Connector.statement.executeQuery("Select Animal FROM animal");
				
				while(rs.next()) {
					String AName = rs.getString("Animal");
					b = AName;
					CBAnimalName.getItems().addAll(b);
					}
				
				} catch (SQLException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
			CBAnimalName.setPromptText("Animal Type");
		});
		
		btnUpdateAnimaltype.setOnAction(e ->{
			int ValidateUpdateDuplicateAnimal = 0;
			try {
				ResultSet rs = Connector.statement.executeQuery("Select Animal FROM animal");
				
				while(rs.next()) {
					String AName = rs.getString("Animal");
					if (AName.equalsIgnoreCase(tfAnimalTypeupdate.getText())) {
						ValidateUpdateDuplicateAnimal++;
					}
					
					}
				
				} catch (SQLException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
			
			if (CBAnimalName.getSelectionModel().getSelectedItem() == null) {
				Alert Failed = new Alert(AlertType.ERROR);
				Failed.setHeaderText("Error !");
				Failed.setContentText("Please Select an Animal Type");
				Failed.show();
			}else if (tfAnimalTypeupdate.getText().isBlank()) {
				Alert Failed = new Alert(AlertType.ERROR);
				Failed.setHeaderText("Error !");
				Failed.setContentText("Animal name must no be blank");
				Failed.show();
			}else if (tfAnimalTypeupdate.getText().length() < 3 || tfAnimalTypeupdate.getText().length() > 15 ) {
				Alert Failed = new Alert(AlertType.ERROR);
				Failed.setHeaderText("Error !");
				Failed.setContentText("Animal name must be 3 - 15 Characters");
				Failed.show();
			}else if (ValidateUpdateDuplicateAnimal > 0) {
				Alert Failed = new Alert(AlertType.ERROR);
				Failed.setHeaderText("Error !");
				Failed.setContentText("Animal Type cannot be duplicate !");
				Failed.show();
			}else {
				
				LoggedInUser s = new LoggedInUser();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				int f = 0;
				
				try {
					ResultSet rs = Connector.statement.executeQuery("SELECT AnimalID FROM animal WHERE Animal = '" + CBAnimalName.getSelectionModel().getSelectedItem() + "'");
					
					while(rs.next()) {
						int AID = rs.getInt("AnimalID");
						f = AID;
					}
					
				} catch (SQLException c) {
					// TODO Auto-generated catch block
					c.printStackTrace();
				}
				
				String query1 = "INSERT INTO animalaction (AnimalActionID, userID, AnimalID, Animal, description, Date) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement ps1 = Connector.preparedStatement(query1);
			
				try {
					ps1.setString(1, "0");
					ps1.setString(2, s.getUserID() + "");
					ps1.setString(3, f + "");
					ps1.setString(4, tfAnimalTypeupdate.getText()+ "");
					ps1.setString(5, "Updated Animal" );
					ps1.setString(6, dtf.format(now) );
					ps1.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				
				String query = "UPDATE animal SET Animal = ? WHERE Animal = ?";
				PreparedStatement ps = Connector.preparedStatement(query);
				
				try {
					ps.setString(1, tfAnimalTypeupdate.getText());
					ps.setString(2, CBAnimalName.getSelectionModel().getSelectedItem());
					ps.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				
				CBAnimalName.getItems().clear();
				String b = "";
				try {
					ResultSet rs = Connector.statement.executeQuery("Select Animal FROM animal");
					
					while(rs.next()) {
						String AName = rs.getString("Animal");
						b = AName;
						CBAnimalName.getItems().addAll(b);
						}
					
					} catch (SQLException d) {
						// TODO Auto-generated catch block
						d.printStackTrace();
					}
			
				CBAnimalName.setPromptText("Animal Type");
				
				AnimalTable.getItems().clear();	
				AnimalTable.getItems().addAll(Animal.getAnimalTypeMenu());
				Alert Success = new Alert(AlertType.INFORMATION);
				Success.setHeaderText("Message");
				Success.setContentText("The Animal Has been Updated");
				Success.show();
			}
			
		});
		btnDeleteAnimaltype.setDisable(true);
		AnimalTable.getSelectionModel().selectedItemProperty().addListener((item, oldVal, newVal) -> {
			if (item != null) {
				btnDeleteAnimaltype.setDisable(false);
			}else {
				btnDeleteAnimaltype.setDisable(true);
			}
		});
		
		btnDeleteAnimaltype.setOnAction(e ->{
			if (AnimalTable.getSelectionModel().getSelectedItem() == null) {
				Alert Failed = new Alert(AlertType.ERROR);
				Failed.setHeaderText("Error !");
				Failed.setContentText("Please Select a Pet in the table");
				Failed.show();
			}else {
			
			try {
				Connector.statement.executeUpdate("DELETE FROM animal WHERE AnimalID = '" + AnimalTable.getSelectionModel().getSelectedItem().getAnimalID() + "'");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			CBAnimalName.getItems().clear();
			String b = "";
			try {
				ResultSet rs = Connector.statement.executeQuery("SELECT Animal FROM animal");
				
				while(rs.next()) {
					String AName = rs.getString("Animal");
					b = AName;
					CBAnimalName.getItems().addAll(b);
					}
				
				} catch (SQLException c) {
					// TODO Auto-generated catch block
					c.printStackTrace();
				}
			
			CBAnimalName.setPromptText("Animal Type");
			
			LoggedInUser s = new LoggedInUser();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			
			try {	
				Connector.statement.executeUpdate("INSERT INTO animalaction (AnimalActionID, userID, AnimalID, Animal, description, Date) VALUES "
						+ "( "+ 0 +", " + s.getUserID() +", '"+ AnimalTable.getSelectionModel().getSelectedItem().getAnimalID() +"', '"
				+ AnimalTable.getSelectionModel().getSelectedItem().getAnimalType() +"' , '"
						+ "Deleted Pet' ,'"+ dtf.format(now) +"')");
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			
			AnimalTable.getItems().clear();	
			AnimalTable.getItems().addAll(Animal.getAnimalTypeMenu());
			Alert Success = new Alert(AlertType.INFORMATION);
			Success.setHeaderText("Message");
			Success.setContentText("The Animal Has been Deleted");
			Success.show();
			}});
	}
	
	public void Frame2Alignment() {
		IFFrame2.setPadding(new Insets(20));
		IFFrame2.setSpacing(10);
		IFFrame2.setStyle("-fx-background-color: White;");
		
		CBAnimalName.setMinWidth(150);
		FPGPanimaltype.setHgap(30);
	}
	
	public void Gridpane2Add() {
		GPForm2.add(insertAnimal,0,0);
		GPForm2.add(tfAnimalType, 0, 1);
		GPForm2.add(btnInsertAnimaltype, 0, 2);
		
		GPForm2.add(lblchoosewhichone, 1, 0);
		GPForm2.add(CBAnimalName, 1, 1);
		GPForm2.add(tfAnimalTypeupdate, 1, 2);
		GPForm2.add(btnUpdateAnimaltype, 1, 3);
		
		Gridpane2Alignment();
	}
	
	public void Gridpane2Alignment() {
		GPForm2.setVgap(10);
		GPForm2.setHgap(10);
	}
	public void Frame2() {
		
		FPGPanimaltype.getChildren().addAll(GPForm2,btnDeleteAnimaltype);
		IFFrame2.getChildren().addAll(lblAnimalisting, AnimalTable, FPGPanimaltype);
		
		
		if (frame.getChildren().contains(internalFrame)) {
			internalFrame.close();
		}
		if (frame.getChildren().contains(internalFrame2)) {
			internalFrame2.close();
		}
		ManageAnimal.setOnAction(e ->{
			internalFrame2 = new Window("Animal Listing Menu");
			internalFrame2.setPrefSize(1000, 800);
		
			
			internalFrame2.setLayoutX(0);
			internalFrame2.setLayoutY(25);
			internalFrame2.setMovable(false);
			
			internalFrame2.getContentPane().getChildren().add(IFFrame2);
			AnimalTable.getItems().clear();	
			AnimalTable.getItems().addAll(Animal.getAnimalTypeMenu());
			PetTable.getItems().clear();
			PetTable.getItems().addAll(Pet.getPetMenu());
			frame.setCenter(internalFrame2);
		});
	}
	
	public void warnawarni1() {
		btnInsert.setOnMouseEntered(p ->{
			btnInsert.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
		});
		btnInsert.setOnMouseExited(p ->{
			btnInsert.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		});
		
		btnUpdate.setOnMouseEntered(p ->{
			btnUpdate.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
		});
		btnUpdate.setOnMouseExited(p ->{
			btnUpdate.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		});
		
		btnDelete.setOnMouseEntered(p ->{
			btnDelete.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
		});
		btnDelete.setOnMouseExited(p ->{
			btnDelete.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		});
		
		btnInsertAnimaltype.setOnMouseEntered(p ->{
			btnInsertAnimaltype.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
		});
		btnInsertAnimaltype.setOnMouseExited(p ->{
			btnInsertAnimaltype.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		});
		
		btnUpdateAnimaltype.setOnMouseEntered(p ->{
			btnUpdateAnimaltype.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
		});
		btnUpdateAnimaltype.setOnMouseExited(p ->{
			btnUpdateAnimaltype.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		});
		
		btnDeleteAnimaltype.setOnMouseEntered(p ->{
			btnDeleteAnimaltype.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
		});
		btnDeleteAnimaltype.setOnMouseExited(p ->{
			btnDeleteAnimaltype.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		});
		
		CBAnimalName.setOnMouseEntered(p ->{
			CBAnimalName.setStyle("-fx-background-color: #808080;");
			CBAnimalName.setButtonCell(new ListCell<String>() {
				  @Override
				  protected void updateItem(String item, boolean empty) {
				    super.updateItem(item, empty);
				      setText(item);
				      setStyle("-fx-text-fill: black;");
				    
				  }
				});
		});
		
		CBAnimalName.setOnMouseExited(p ->{
			CBAnimalName.setStyle("-fx-background-color: #000000;");
			CBAnimalName.setButtonCell(new ListCell<String>() {
				  @Override
				  protected void updateItem(String item, boolean empty) {
				    super.updateItem(item, empty);
				      setText(item);
				      setStyle("-fx-text-fill: white;");
				    
				  }
				});
		});
	}
	
	public void FrameCMD() {
		/////////////////////////////////////////////////////////////
		///////////////////////INSERT BUTTON/////////////////////////
		/////////////////////////////////////////////////////////////
		
		btnInsert.setOnAction(e ->{
			StackPane Form1 = new StackPane();
			
			Form1.setStyle("-fx-border-color: gray; -fx-border-width: 2px;");
			Form1.setPadding(new Insets(10));
			
			FlowPane Fpform1 = new FlowPane();
			GridPane GpForm1 = new GridPane();
			
			Label InForm1 = new Label("Insert Form");
			
			Button InsertForm = new Button("Insert");
			InsertForm.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			InsertForm.setMinSize(100, 100);
			InsertForm.setOnMouseEntered(p ->{
				InsertForm.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
			});
			InsertForm.setOnMouseExited(p ->{
				InsertForm.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			});
			///////////////////////////////////////////////////
			ComboBox<String> CBAnimalName2 = new ComboBox<>();
			CBAnimalName2.setStyle("-fx-background-color: #000000;");
			CBAnimalName2.setButtonCell(new ListCell<String>() {
				  @Override
				  protected void updateItem(String item, boolean empty) {
				    super.updateItem(item, empty);
				      setText(item);
				      setStyle("-fx-text-fill: white;");
				    
				  }
				});
			
			CBAnimalName2.setOnMouseEntered(p ->{
				CBAnimalName2.setStyle("-fx-background-color: #808080;");
				CBAnimalName2.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: black;");
					    
					  }
					});
			});
			
			CBAnimalName2.setOnMouseExited(p ->{
				CBAnimalName2.setStyle("-fx-background-color: #000000;");
				CBAnimalName2.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: white;");
					    
					  }
					});
			});
			///////////////////////////////////////////////////////
			CBAnimalName2.setOnMouseClicked(d ->{
				CBAnimalName2.getItems().clear();
				String b = "";
				try {
					ResultSet rs = Connector.statement.executeQuery("SELECT Animal FROM animal");
					
					while(rs.next()) {
						String AName = rs.getString("Animal");
						b = AName;
						CBAnimalName2.getItems().addAll(b);
						}
					
					} catch (SQLException a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
				CBAnimalName2.setPromptText("Animal Type");
			});
			
			CBAnimalName2.setPromptText("Animal Type");
			
			TextField InsertPetname = new TextField();
			InsertPetname.setPromptText("Insert Pet Name");
			
			TextField InsertPetPrice = new TextField();
			InsertPetPrice.setPromptText("Insert Pet Price");
			
			GpForm1.add(InForm1, 0, 0);
			
			GpForm1.add(InsertPetname, 0, 1);
			GpForm1.add(InsertPetPrice, 1, 1);
			
			GpForm1.add(CBAnimalName2, 0, 2);
			
			GpForm1.setHgap(10);
			GpForm1.setVgap(10);
			//////////////////////////////////////
			////////Insert function///////////////
			//////////////////////////////////////
			InsertForm.setOnAction(b -> {
				
					try {
				if (InsertPetname.getText().length() < 3  || InsertPetname.getText().length() > 25) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Pet Name has to be between 3 - 25 characters!");
					Failed.show();
					
				}else if (InsertPetPrice.getText().isBlank()) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Pet Price must be atleast 100000");
					Failed.show();
					
				}else if (Integer.parseInt(InsertPetPrice.getText()) < 100000) {	 
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Pet Price must be atleast 100000");
					Failed.show();
				}else if (CBAnimalName2.getSelectionModel().getSelectedItem() == null) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Please Select an Animal Type");
					Failed.show();
				}else {
					String tes2 = "";
					try {
						ResultSet rs = Connector.statement.executeQuery("SELECT AnimalID FROM animal WHERE Animal = '" + CBAnimalName2.getSelectionModel().getSelectedItem() + "' " );
						while(rs.next()) {
							String tes = rs.getString("AnimalID");
							tes2 = tes;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Pet p = new Pet(0, Integer.parseInt(tes2), "", InsertPetname.getText(), Integer.parseInt(InsertPetPrice.getText()));
					p.insert();
					
					PetTable.getItems().addAll(Pet.getPetMenu());
					
					LoggedInUser s = new LoggedInUser();
					int lastValue = PetTable.getItems().get(PetTable.getItems().size() - 1).getPetID();
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDateTime now = LocalDateTime.now();
					
					String query1 = "INSERT INTO action (actionID, userID, petID, animal, PetName, PetPrice, description, Date) VALUES (?,?,?,?,?,?,?,?)";
					PreparedStatement ps1 = Connector.preparedStatement(query1);
				
					try {
						ps1.setString(1, "0");
						ps1.setString(2, s.getUserID() + "");
						ps1.setString(3, (lastValue+1) + "");
						ps1.setString(4, CBAnimalName2.getSelectionModel().getSelectedItem()+ "");
						ps1.setString(5, InsertPetname.getText() );
						ps1.setString(6, Integer.parseInt(InsertPetPrice.getText()) +"" );
						ps1.setString(7, "Inserted Pet");
						ps1.setString(8, dtf.format(now));
						ps1.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					
					
					PetTable.getItems().clear();
					PetTable.getItems().addAll(Pet.getPetMenu());
					
					Alert Success = new Alert(AlertType.INFORMATION);
					Success.setHeaderText("Message");
					Success.setContentText("The New Pet Has been Added");
					Success.show();
					
				}
					} catch (Exception e2) {
						// TODO: handle exception
						Alert Failed = new Alert(AlertType.ERROR);
						Failed.setHeaderText("Error !");
						Failed.setContentText("Please input Integer in quantity");
						Failed.show();
						
					}
	
				
			});
			Fpform1.getChildren().addAll(GpForm1, InsertForm);
			Fpform1.setHgap(10);
			
			Form1.getChildren().add(Fpform1);
			
			IFFrame.getChildren().set(3, Form1);
		});
		/////////////////////////////////////////////////////////////
		///////////////////////UPDATE BUTTON/////////////////////////
		/////////////////////////////////////////////////////////////
		btnUpdate.setOnAction(e ->{
			StackPane Form1 = new StackPane();
			Form1.setStyle("-fx-border-color: gray; -fx-border-width: 2px;");
			Form1.setPadding(new Insets(10));
			FlowPane Fpform1 = new FlowPane();
			GridPane GpForm1 = new GridPane();
			
			Label InForm1 = new Label("Update Form");
			
			Button InsertForm2 = new Button("Update");
			InsertForm2.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			InsertForm2.setMinSize(100, 100);
			InsertForm2.setOnMouseEntered(p ->{
				InsertForm2.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
			});
			InsertForm2.setOnMouseExited(p ->{
				InsertForm2.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			});
			
			ComboBox<String> CBPetID = new ComboBox<>();
			CBPetID.setStyle("-fx-background-color: #000000;");
			CBPetID.setButtonCell(new ListCell<String>() {
				  @Override
				  protected void updateItem(String item, boolean empty) {
				    super.updateItem(item, empty);
				      setText(item);
				      setStyle("-fx-text-fill: white;");
				    
				  }
				});
			CBPetID.setOnMouseEntered(p ->{
				CBPetID.setStyle("-fx-background-color: #808080;");
				CBPetID.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: black;");
					    
					  }
					});
			});
			
			CBPetID.setOnMouseExited(p ->{
				CBPetID.setStyle("-fx-background-color: #000000;");
				CBPetID.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: white;");
					    
					  }
					});
			});
			
			ComboBox<String> CBAnimalType = new ComboBox<>();
			CBAnimalType.setStyle("-fx-background-color: #000000;");
			CBAnimalType.setButtonCell(new ListCell<String>() {
				  @Override
				  protected void updateItem(String item, boolean empty) {
				    super.updateItem(item, empty);
				      setText(item);
				      setStyle("-fx-text-fill: white;");
				    
				  }
				});
			CBAnimalType.setOnMouseEntered(p ->{
				CBAnimalType.setStyle("-fx-background-color: #808080;");
				CBAnimalType.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: black;");
					    
					  }
					});
			});
			
			CBAnimalType.setOnMouseExited(p ->{
				CBAnimalType.setStyle("-fx-background-color: #000000;");
				CBAnimalType.setButtonCell(new ListCell<String>() {
					  @Override
					  protected void updateItem(String item, boolean empty) {
					    super.updateItem(item, empty);
					      setText(item);
					      setStyle("-fx-text-fill: white;");
					    
					  }
					});
			});
			
			
			CBPetID.setOnMouseClicked(d ->{
				CBPetID.getItems().clear();
				String cid = "";
				
				try {
					ResultSet rs = Connector.statement.executeQuery("SELECT PetID FROM pet ORDER BY PetID ASC");
					
					while(rs.next()) {
						String CID = rs.getString("PetID");
						cid = CID;
						CBPetID.getItems().addAll(cid);
						}
					
					} catch (SQLException a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
				
				CBPetID.setPromptText("Pet ID");
			});
			
			CBPetID.setPromptText("Pet ID");

			CBAnimalType.setOnMouseClicked(d ->{
				CBAnimalType.getItems().clear();
				String b = "";
				try {
					ResultSet rs = Connector.statement.executeQuery("SELECT Animal FROM animal");
					
					while(rs.next()) {
						String AName = rs.getString("Animal");
						b = AName;
						CBAnimalType.getItems().addAll(b);
						}
					
					} catch (SQLException a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
				CBAnimalType.setPromptText("Animal Type");
			});
			
			CBAnimalType.setPromptText("Animal Type");
			
			TextField InsertPetname1 = new TextField();
			InsertPetname1.setPromptText("Insert Pet Name");
			
			TextField InsertPetPrice1 = new TextField();
			InsertPetPrice1.setPromptText("Insert Pet Price");
			
			GpForm1.add(InForm1, 0, 0);
			GpForm1.add(CBPetID, 0, 1);
			
			GpForm1.add(InsertPetname1, 0, 2);
			GpForm1.add(InsertPetPrice1, 1, 2);
			
			GpForm1.add(CBAnimalType, 0, 3);
			
			GpForm1.setHgap(10);
			GpForm1.setVgap(10);
			//////////////////////////////////////
			////////Update function///////////////
			//////////////////////////////////////
			InsertForm2.setOnAction(d -> {
		
				try {
				if (CBPetID.getSelectionModel().getSelectedItem() == null) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Please Select an Animal ID");
					Failed.show();
					
				}else if (InsertPetname1.getText().length() < 5  || InsertPetname1.getText().length() > 25) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Pet Name has to be between 5 - 25 characters!");
					Failed.show();
					
				}else if (InsertPetPrice1.getText().isBlank()) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Pet Price must be atleast 100000");
					Failed.show();
					
				}else if (Integer.parseInt(InsertPetPrice1.getText()) < 100000) {	 
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Pet Price must be atleast 100000");
					Failed.show();
				}else if (CBAnimalType.getSelectionModel().getSelectedItem() == null) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Please Select an Animal Type");
					Failed.show();
				}else {
					String tes2 = "";
					try {
						ResultSet rs = Connector.statement.executeQuery("SELECT AnimalID FROM animal WHERE Animal = '" + CBAnimalType.getSelectionModel().getSelectedItem() + "' " );
						while(rs.next()) {
							String tes = rs.getString("AnimalID");
							tes2 = tes;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Pet p = new Pet(Integer.parseInt(CBPetID.getSelectionModel().getSelectedItem()), Integer.parseInt(tes2), CBAnimalType.getSelectionModel().getSelectedItem(), InsertPetname1.getText(), Integer.parseInt(InsertPetPrice1.getText()));
					p.update();
					
					PetTable.getItems().clear();
					PetTable.getItems().addAll(Pet.getPetMenu());
					LoggedInUser s = new LoggedInUser();
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDateTime now = LocalDateTime.now();
					
					String query1 = "INSERT INTO action (actionID, userID, petID, animal, PetName, PetPrice, description, Date) VALUES (?,?,?,?,?,?,?,?)";
					PreparedStatement ps1 = Connector.preparedStatement(query1);
				
					try {
						ps1.setString(1, "0");
						ps1.setString(2, s.getUserID() + "");
						ps1.setString(3, CBPetID.getSelectionModel().getSelectedItem() + "");
						ps1.setString(4, CBAnimalType.getSelectionModel().getSelectedItem()+ "");
						ps1.setString(5, InsertPetname1.getText() );
						ps1.setString(6, Integer.parseInt(InsertPetPrice1.getText()) +"" );
						ps1.setString(7, "Updated Pet");
						ps1.setString(8, dtf.format(now));
						ps1.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					
					
					
					Alert Success = new Alert(AlertType.INFORMATION);
					Success.setHeaderText("Message");
					Success.setContentText("The Pet Selected Has been Updated !");
					Success.show();
					
					}
				
				} catch (Exception e2) {
					// TODO: handle exception
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Please input Integer in quantity");
					Failed.show();
					
				}
			
			});
			/////////////////////////////////////
			Fpform1.getChildren().addAll(GpForm1, InsertForm2);
			Fpform1.setHgap(10);
			
			
			Form1.getChildren().add(Fpform1);
			

			IFFrame.getChildren().set(3, Form1);

		});
		/////////////////////////////////////////////////////////////
		///////////////////////DELETE BUTTON/////////////////////////
		/////////////////////////////////////////////////////////////
		btnDelete.setDisable(true);
		PetTable.getSelectionModel().selectedItemProperty().addListener((item, oldVal, newVal) -> {
			if (item != null) {
				btnDelete.setDisable(false);
			}else {
				btnDelete.setDisable(true);
			}
		});
		
		//////////////////////////////////////
		////////DELETE function///////////////
		//////////////////////////////////////
		btnDelete.setOnAction(cae -> {
			
			if (PetTable.getSelectionModel().getSelectedItem() == null) {
				Alert Failed = new Alert(AlertType.ERROR);
				Failed.setHeaderText("Error !");
				Failed.setContentText("Please Select a Pet");
				Failed.show();
			}else {
			
			Pet p = new Pet(PetTable.getSelectionModel().getSelectedItem().getPetID(), 0, "", "", 0);
			p.delete();
			
			LoggedInUser s = new LoggedInUser();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			
			try {	
				Connector.statement.executeUpdate("INSERT INTO action (actionID, userID, petID, animal, PetName, PetPrice, description, Date) VALUES "
						+ "( "+ 0 +", " + s.getUserID() +", '"+ PetTable.getSelectionModel().getSelectedItem().getPetID() +"', '"
				+ PetTable.getSelectionModel().getSelectedItem().getAnimalType() +"' , '"+  PetTable.getSelectionModel().getSelectedItem().getPetName() + "' ,'" + PetTable.getSelectionModel().getSelectedItem().getPetPrice() + "' ,'" 
						+ "Deleted Pet' ,'"+ dtf.format(now) +"')");
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			
			PetTable.getItems().clear();
			PetTable.getItems().addAll(Pet.getPetMenu());
			Alert Success = new Alert(AlertType.INFORMATION);
			Success.setHeaderText("Message");
			Success.setContentText("The Pet Has been Deleted");
			Success.show();
			}
		});
		
	}
	
	public void FrameAlignment() {
		IFFrame.setPadding(new Insets(20));
		IFFrame.setSpacing(10);
		
		FPFrame.setHgap(10);
		IFFrame.setStyle("-fx-background-color: White;");
	}
	
	public void Frame() {
		
		FPFrame.getChildren().addAll(btnInsert,btnUpdate,btnDelete);
		IFFrame.getChildren().addAll(lblpetlist,PetTable,FPFrame,formHandler);
		
		if (frame.getChildren().contains(internalFrame)) {
			internalFrame.close();
		}
		if (frame.getChildren().contains(internalFrame2)) {
			internalFrame2.close();
		}
		
		ManagePetListing.setOnAction(e ->{
			internalFrame = new Window("Pet Listing Menu");
			internalFrame.setPrefSize(1000, 800);
		
			
			internalFrame.setLayoutX(0);
			internalFrame.setLayoutY(25);
			internalFrame.setMovable(false);
			AnimalTable.getItems().clear();	
			AnimalTable.getItems().addAll(Animal.getAnimalTypeMenu());
			PetTable.getItems().clear();
			PetTable.getItems().addAll(Pet.getPetMenu());
			
			internalFrame.getContentPane().getChildren().add(IFFrame);
			
			frame.setCenter(internalFrame);
		});
	}
	
	public void logout() {
		logoud.setOnAction(p ->{
			Stage nxt = (Stage) mainpane.getScene().getWindow();
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
	public void Coloring() {
		btnDelete.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		btnDeleteAnimaltype.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		
		btnInsert.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		btnInsertAnimaltype.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		
		btnUpdate.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		btnUpdateAnimaltype.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		
		CBAnimalName.setStyle("-fx-background-color: #000000;");
		CBAnimalName.setButtonCell(new ListCell<String>() {
		  @Override
		  protected void updateItem(String item, boolean empty) {
		    super.updateItem(item, empty);
		      setText(item);
		      setStyle("-fx-text-fill: white;");
		    
		  }
		});
		
	}
	
	public void validuser() {
		LoggedInUser b = new LoggedInUser();
		if (b.getUserID() == 0) {
			Alert Failed = new Alert(AlertType.ERROR);
			Failed.setHeaderText("Error !");
			Failed.setContentText("Please Login Again !");
			Failed.show();
			Failed.setOnCloseRequest(e -> {
				Stage nxt = (Stage) menubar.getScene().getWindow();
				nxt.close();

				Stage nextPanel = new Stage();
				
			try {
				new Main().start(nextPanel);
			} catch (Exception a) {
				// TODO: handle exception
				a.printStackTrace();
			}
			
			});
		}
	}
	
	
	
	ListView<String> Dropdown;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Connector.init();
		init();
		AnimalListingTable();
		PetTable();
		align();
		logout();
		
		Frame();
		FrameAlignment();
		FrameCMD();
		
		Frame2();
		Gridpane2Add();
		Frame2Alignment();
		Frame2CMD();
		
		Coloring();
		warnawarni1();
		
		arg0.setScene(scene);
		arg0.setTitle("Arnold's PetShop [Displaying Staff Main Menu]");
		arg0.show();
		validuser();
	}

}

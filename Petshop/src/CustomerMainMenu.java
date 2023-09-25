

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;


public class CustomerMainMenu extends Application{
	StackPane mainpane, Background;
	
	Label lbltitle, lblwelcome;
	
	Button btnCheckout;
	
	MenuBar menubar;
	Menu menu1, menu2;
	MenuItem buyPet, transactionhistory, logoud;
	
	Window internalFrame, internalFrame2;
	
	BorderPane frame;
	
	VBox IFFrame, IFFrame2, IFFrame3, IFFrameTransHistoryVB;
	
	Image image;
	ImageView imageview;
	
	Scene scene;
	
	TableView<Pet> BuyPetTable;
	TableView<Cart> CartPetTable;
	
	TableView<TransactionHeader> TransactionHeaderTable;
	TableView<TransactionDetail> TransactionDetailTable;
	
	ComboBox<String> PetNameCB;
	
	
	public void init() {
		mainpane = new StackPane();
		
		btnCheckout = new Button("Checkout");
		
		lbltitle = new Label("");
		LoggedInUser s = new LoggedInUser();
		
		lblwelcome = new Label();
		lblwelcome.setFont(Font.font("", FontWeight.BOLD, 24));
		lblwelcome.setStyle("-fx-text-fill: GRAY;");
		DropShadow ds = new DropShadow();
		ds.setColor(Color.LIGHTGRAY);
		ds.setSpread(3.0);
		ds.setOffsetX(1);
		ds.setOffsetY(1);
		ds.setRadius(1);
		lblwelcome.setEffect(ds);
		
		frame = new BorderPane();
		
		menu1 = new Menu("Transaction");
		menu2 = new Menu("Logout");
		menubar = new MenuBar(menu1, menu2);
		buyPet = new MenuItem("Buy Pets");
		transactionhistory = new MenuItem("Transaction History");
		logoud = new MenuItem("Logout");
		
		image = new Image("File:1.jpg");
		imageview = new ImageView(image);
		imageview.setFitHeight(775);
		imageview.setFitWidth(1000);
		
		Background = new StackPane();
		Background.getChildren().addAll(imageview,lblwelcome);
		
		
		scene = new Scene(mainpane, 1000, 800);
	
		menu1.getItems().addAll(buyPet, transactionhistory);
		menu2.getItems().add(logoud);
		
		mainpane.getChildren().add(frame);
	}
	
	public void warnawarni() {
		btnCheckout.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		btnCheckout.setOnMouseEntered(p ->{
			btnCheckout.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
		});
		btnCheckout.setOnMouseExited(e ->{
			btnCheckout.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		});
		
		PetNameCB.setStyle("-fx-background-color: #000000;");
		PetNameCB.setButtonCell(new ListCell<String>() {
			  @Override
			  protected void updateItem(String item, boolean empty) {
			    super.updateItem(item, empty);
			      setText(item);
			      setStyle("-fx-text-fill: white;");
			    
			  }
			});
		PetNameCB.setOnMouseEntered(p ->{
			PetNameCB.setStyle("-fx-background-color: #808080;");
			PetNameCB.setButtonCell(new ListCell<String>() {
				  @Override
				  protected void updateItem(String item, boolean empty) {
				    super.updateItem(item, empty);
				      setText(item);
				      setStyle("-fx-text-fill: black;");
				    
				  }
				});
		});
		
		PetNameCB.setOnMouseExited(p ->{
			PetNameCB.setStyle("-fx-background-color: #000000;");
			PetNameCB.setButtonCell(new ListCell<String>() {
				  @Override
				  protected void updateItem(String item, boolean empty) {
				    super.updateItem(item, empty);
				      setText(item);
				      setStyle("-fx-text-fill: white;");
				    
				  }
				});
		});
	}
	
	public void align() {
		
		frame.setTop(menubar);
		frame.setCenter(Background);
	}
	
	public void cmd() {
		
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
	
	public void InternalFrameAlign2() {
		IFFrame3.setSpacing(10);
		IFFrame3.setPadding(new Insets(10));
	}
	
	public void InternalFrameAlign1() {
		IFFrame2.setSpacing(10);
		IFFrame2.setPadding(new Insets(10));
		
	}
	
	public void InternalFrameAlign() {
		IFFrame.setSpacing(10);
		IFFrame.setPadding(new Insets(10));
		lblwelcome.setText("Welcome, " + LoggedInUser.getUsername());
		
		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scroll.setContent(frame);
		scroll.setFitToWidth(true);
		scroll.setPannable(true);
	}
	
	public void InternalFrame2initPetCart() {
		IFFrame3 = new VBox();
		Label lbladdtocart = new Label("Add to Cart");
		Label lblPetName = new Label("Pet Name");
		lblPetName.setFont(Font.font("", FontWeight.MEDIUM, 24));
		
		PetNameCB = new ComboBox<>();
		PetNameCB.setPrefWidth(200);
		PetNameCB.setPromptText("Select Pet");
		PetNameCB.setOnMouseClicked(e ->{
			PetNameCB.getItems().clear();
			String cName = "";
			try {
				ResultSet rs = Connector.statement.executeQuery("Select PetName FROM pet");
				
				while(rs.next()) {
					String CNAME = rs.getString("PetName");
					cName = CNAME;
					PetNameCB.getItems().addAll(CNAME);
					}
				
				} catch (SQLException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
			
			PetNameCB.setPromptText("");
		});
			
			Button btnaddtocart = new Button("Add to Cart");
			/////////////////////////////////////////////////////
			btnaddtocart.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			btnaddtocart.setOnMouseEntered(p ->{
				btnaddtocart.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
			});
			btnaddtocart.setOnMouseExited(e ->{
				btnaddtocart.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
			});
			////////////////////////////////////////////////////
		
		btnaddtocart.setOnAction(e -> {
			LoggedInUser s = new LoggedInUser();
			
			int validate = 0;
			try {
				ResultSet rs5 = Connector.statement.executeQuery("SELECT PetName FROM pet pe JOIN cart c ON pe.PetID = c.PetID WHERE c.UserID = " + s.getUserID());
				while(rs5.next()) {
					String validate2 = rs5.getString("PetName");
					if (validate2.equalsIgnoreCase(PetNameCB.getSelectionModel().getSelectedItem())) {
						validate++;
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
				
				if (PetNameCB.getSelectionModel().getSelectedItem() == null) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("You Must Select A Pet you want to buy");
					Failed.show();
		
				}else if (validate > 0) {
					Alert Failed = new Alert(AlertType.ERROR);
					Failed.setHeaderText("Error !");
					Failed.setContentText("Item is already at cart");
					Failed.show();
					
				}else {
					
					// formula ngambil petid dari petname
				
					String tes2 = "";
					try {
						ResultSet rs5 = Connector.statement.executeQuery("SELECT petID FROM pet WHERE PetName = '" + PetNameCB.getSelectionModel().getSelectedItem() + "' " );
						while(rs5.next()) {
							String tes = rs5.getString("petID");
							tes2 = tes;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					// Formula ngambil pet id dari cart yang ada dari pet id yang udah dipilih	
									
						try {
							
							Connector.statement.executeUpdate("INSERT INTO cart (userID, petID ) VALUES "
									+ "( "+ s.getUserID() +", '"+ tes2 
							+ "')");
							
							Alert Success = new Alert(AlertType.INFORMATION);
							Success.setHeaderText("Message");
							Success.setContentText("The order Has been Added to cart");
							Success.show();
							
						} catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
				
					CartPetTable.getItems().clear();
					CartPetTable.getItems().addAll(Cart.getMenu());
					
				}
			
				
		});
		
		btnCheckout.setOnMouseClicked(e ->{
			
			
			
			if (CartPetTable.getItems().isEmpty()) {
				Alert Failed = new Alert(AlertType.ERROR);
				Failed.setHeaderText("Error !");
				Failed.setContentText("Cart is empty");
				
			}else {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				LoggedInUser p = new LoggedInUser();
				
				
				try {
					Connector.statement.executeUpdate("INSERT INTO transactionheader (TransactionID, userID, TransactionDate) VALUES " + "(" + 0 + ", "+ p.getUserID() + 
							", '" + dtf.format(now) + "')");
					
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				int transid = 0;
				try {
					ResultSet rs10 = Connector.statement.executeQuery("SELECT MAX(TransactionID) FROM transactionheader");
					while(rs10.next()) {
						int ZZZ = rs10.getInt("MAX(TransactionID)");
						transid = ZZZ;
						
					}
					System.out.println(transid);
				} catch (SQLException e8) {
					// TODO Auto-generated catch block
					e8.printStackTrace();
				}
				
				try {
					Connector.statement.executeUpdate("INSERT INTO transactiondetail (TransactionID, petID, PetName, Animal, Price) SELECT th.TransactionID, c.petID, pe.PetName, a.Animal, pe.PetPrice FROM transactionheader th JOIN cart c ON th.userID = c.userID JOIN pet pe ON c.PetID = pe.PetID JOIN animal a ON a.AnimalID = pe.AnimalID "
							+ "WHERE c.userID = " + p.getUserID() + " AND th.TransactionID = " + transid);
				} catch (SQLException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				
				try {
					Connector.statement.executeUpdate("DELETE FROM pet WHERE petID IN (SELECT pt.petID FROM pet pt join cart ct on pt.petID = ct.petID "
							+ "WHERE ct.userID = " + p.getUserID() + " )");
					
				} catch (SQLException e10) {
					// TODO Auto-generated catch block
					e10.printStackTrace();
				}
				
				try {
						Connector.statement.executeUpdate("DELETE FROM cart WHERE UserID = " + p.getUserID());
				} catch (SQLException e11) {
						// TODO Auto-generated catch block
						e11.printStackTrace();
				}
				
				CartPetTable.getItems().clear();
				CartPetTable.getItems().addAll(Cart.getMenu());
				BuyPetTable.getItems().clear();
				BuyPetTable.getItems().addAll(Pet.getPetMenu());
	
				
//				/////////////////////////////////////////////////
				
				Alert Success = new Alert(AlertType.INFORMATION);
				Success.setHeaderText("Message");
				Success.setContentText("You have checked out your order");
				Success.show();
			
			}
			
		});
		
		
		IFFrame3.getChildren().addAll(lbladdtocart, lblPetName, PetNameCB, btnaddtocart);
		InternalFrameAlign2();
	}
	
	public void InternalFrame1initPetCart() {
		IFFrame2 = new VBox();
		Label lblCart = new Label("Cart");
		
		CartPetTable = new TableView<Cart>();
		CartPetTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		CartPetTable.setMaxHeight(280);
		CartPetTable.setMinSize(650, 150);
		
		TableColumn<Cart, String> cPetName = new TableColumn<>("Pet Name");
		cPetName.setMinWidth(140);
		cPetName.setCellValueFactory(new PropertyValueFactory<>("petName"));
		cPetName.setReorderable(false);
		
		
		
		TableColumn<Cart, Integer> cTotalPrice = new TableColumn<>("Price");
		cTotalPrice.setMinWidth(140);
		cTotalPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		cTotalPrice.setReorderable(false);
		
		CartPetTable.getColumns().addAll(cPetName, cTotalPrice);
		CartPetTable.getItems().clear();
		
		CartPetTable.getItems().addAll(Cart.getMenu());
		
		IFFrame2.getChildren().addAll(lblCart, CartPetTable, btnCheckout);
		
		InternalFrameAlign1();
	}
	
	public void InternalFrame1initPetTable() {
		
		IFFrame = new VBox();
		Label AvailablePet = new Label("Available Pet");
		
		BuyPetTable = new TableView<Pet>();
		BuyPetTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		BuyPetTable.setMaxHeight(280);
		BuyPetTable.setMinSize(650, 150);
		
		TableColumn<Pet, String> cPetName = new TableColumn<>("Pet Name");
		cPetName.setMinWidth(150);
		cPetName.setPrefWidth(200);
		cPetName.setCellValueFactory(new PropertyValueFactory<>("PetName"));
		cPetName.setReorderable(false);
		
		TableColumn<Pet, String> cAnimalName = new TableColumn<>("Animal Name");
		cAnimalName.setMinWidth(125);
		cAnimalName.setCellValueFactory(new PropertyValueFactory<>("AnimalType"));
		cAnimalName.setReorderable(false);
		
		TableColumn<Pet, Integer> cPetPrice = new TableColumn<>("Pet Price");
		cPetPrice.setMinWidth(125);
		cPetPrice.setCellValueFactory(new PropertyValueFactory<>("PetPrice"));
		cPetPrice.setReorderable(false);
		
				
		TableColumn<Pet, Button> SelectCoulumn = new TableColumn<>(" ");
		SelectCoulumn.setMinWidth(60);
		SelectCoulumn.setReorderable(false);
		
		BuyPetTable.getColumns().addAll(cPetName, cAnimalName, cPetPrice,SelectCoulumn);
		BuyPetTable.getItems().addAll(Pet.getPetMenu());
		IFFrame.getChildren().addAll(AvailablePet ,BuyPetTable);
		InternalFrameAlign();
	}
	
	
	public void InternalFrame1() {
		
		buyPet.setOnAction(e ->{
			if (frame.getChildren().contains(internalFrame2)) {
				internalFrame2.close();
			}
			
			
			GridPane GPIFrame = new GridPane();
			
			GPIFrame.add(IFFrame, 0, 0);
			GPIFrame.add(IFFrame2, 0, 1);
			GPIFrame.add(IFFrame3, 1, 0);
			
			
			internalFrame = new Window("Buy Pet");
			internalFrame.setPrefSize(1000, 800);
			
			internalFrame.setLayoutX(0);
			internalFrame.setLayoutY(25);
			internalFrame.setMovable(false);
			
			internalFrame.getContentPane().getChildren().add(GPIFrame);
			GPIFrame.setStyle("-fx-background-color: White;");
			frame.setCenter(internalFrame);
			
			if (!CartPetTable.getItems().isEmpty()) {
				Alert Notice = new Alert(AlertType.INFORMATION);
				Notice.setHeaderText("Message");
				Notice.setContentText("The order Cart has been loaded");
				Notice.show();
			}
			
		});
	}
	
	
	public void InternalFrameinitTransHistory() {
		IFFrameTransHistoryVB = new VBox();
		Label lblTranshis = new Label("Transaction History");
		Label lblTransDet = new Label("Transaction Detail");
		Button btnDetail = new Button("Detail");
		////////////////////////////////////////////////////
		btnDetail.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		btnDetail.setOnMouseEntered(p ->{
			btnDetail.setStyle("-fx-background-color: #808080; -fx-text-fill: black;");
		});
		btnDetail.setOnMouseExited(e ->{
			btnDetail.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
		});
		///////////////////////////////////////////////////
		btnDetail.setPrefWidth(250);
		
		FlowPane fpTrans = new FlowPane();
		
		TransactionHeaderTable = new TableView<TransactionHeader>();
		TransactionHeaderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TransactionHeaderTable.setMaxHeight(280);
		TransactionHeaderTable.setMinSize(800, 150);
		
		TableColumn<TransactionHeader, Integer> cTransactionID = new TableColumn<>("Transaction ID");
		cTransactionID.setCellValueFactory(new PropertyValueFactory<>("TransactionID"));
		cTransactionID.setReorderable(false);
		
		TableColumn<TransactionHeader, String> cTransactionDate = new TableColumn<>("Transaction Date");
		cTransactionDate.setCellValueFactory(new PropertyValueFactory<>("TransactionDate"));
		cTransactionDate.setReorderable(false);
		
		TableColumn<TransactionHeader, Integer> cPetsBought = new TableColumn<>("Pets Bought");
		cPetsBought.setCellValueFactory(new PropertyValueFactory<>("PetBought"));
		cPetsBought.setReorderable(false);
		
		
		TransactionHeaderTable.getColumns().addAll(cTransactionID,cTransactionDate,cPetsBought);
		TransactionHeaderTable.getItems().clear();
		TransactionHeaderTable.getItems().addAll(TransactionHeader.getTrans());
		
		TransactionDetailTable = new TableView<TransactionDetail>();
		TransactionDetailTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TransactionDetailTable.setMaxHeight(280);
		TransactionDetailTable.setMinSize(800, 150);
		
		TableColumn<TransactionDetail, Integer> cTransactionIDdet = new TableColumn<>("Transaction ID");
		cTransactionIDdet.setCellValueFactory(new PropertyValueFactory<>("TransactionID"));
		cTransactionIDdet.setReorderable(false);
		
		TableColumn<TransactionDetail, String> cTransactionPetname = new TableColumn<>("Pet Name");
		cTransactionPetname.setCellValueFactory(new PropertyValueFactory<>("PetName"));
		cTransactionPetname.setReorderable(false);
		
		TableColumn<TransactionDetail, String> cTransactionPetType = new TableColumn<>("Animal Name");
		cTransactionPetType.setCellValueFactory(new PropertyValueFactory<>("AnimalName"));
		cTransactionPetType.setReorderable(false);
		
		TableColumn<TransactionDetail, Integer> cTransactionQuantity = new TableColumn<>("Price");
		cTransactionQuantity.setCellValueFactory(new PropertyValueFactory<>("Price"));
		cTransactionQuantity.setReorderable(false);
		
		TransactionDetailTable.getColumns().addAll(cTransactionIDdet, cTransactionPetname, cTransactionPetType, cTransactionQuantity);
		
		btnDetail.setDisable(true);
		TransactionHeaderTable.getSelectionModel().selectedItemProperty().addListener((item, oldVal, newVal) -> {
			if (item != null) {
				btnDetail.setDisable(false);
			}else {
				btnDetail.setDisable(true);
			}
			
		});
		
		if (TransactionHeaderTable.getSelectionModel().getSelectedItems() == null) {
			Alert Failed = new Alert(AlertType.ERROR);
			Failed.setHeaderText("Error !");
			Failed.setContentText("You havent selected any item !");
			Failed.show();
		}else {
			
			btnDetail.setOnAction(e ->{

				TransactionIDdata d = new TransactionIDdata(TransactionHeaderTable.getSelectionModel().getSelectedItem().getTransactionID());
				d.setTransactionID(TransactionHeaderTable.getSelectionModel().getSelectedItem().getTransactionID());
				System.out.println(d.getTransactionID());
				
				TransactionDetailTable.getItems().clear();
				TransactionDetailTable.getItems().addAll(TransactionDetail.getTransDetail());
			
				
				
			});
			
			fpTrans.getChildren().addAll(lblTransDet, btnDetail);
			fpTrans.setHgap(525);
			
			IFFrameTransHistoryVB.getChildren().addAll(lblTranshis, TransactionHeaderTable, fpTrans, TransactionDetailTable);
		}
		
	
	}
	
	public void InternalFrameTransHistory () {
		transactionhistory.setOnAction(e ->{
			if (frame.getChildren().contains(internalFrame)) {
				internalFrame.close();
				TransactionHeaderTable.getItems().clear();
				TransactionHeaderTable.getItems().addAll(TransactionHeader.getTrans());
			}
			internalFrame2 = new Window("Transaction History");
			internalFrame2.setPrefSize(1000, 800);
			
			internalFrame2.setLayoutX(0);
			internalFrame2.setLayoutY(25);
			internalFrame2.setMovable(false);
			IFFrameTransHistoryVB.setStyle("-fx-background-color: White;");
			internalFrame2.getContentPane().getChildren().add(IFFrameTransHistoryVB);
			
			InternalFrameTransHistoryAlign();
			TransactionHeaderTable.getItems().clear();
			TransactionHeaderTable.getItems().addAll(TransactionHeader.getTrans());
			frame.setCenter(internalFrame2);
		});
		
	} 
	
	public void InternalFrameTransHistoryAlign() {
		IFFrameTransHistoryVB.setSpacing(10);
		IFFrameTransHistoryVB.setPadding(new Insets(15));
		
		
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
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Connector.init();
		
		init();
		align();
		cmd();
		InternalFrame1();
		
		InternalFrame1initPetTable();
		InternalFrame1initPetCart();
		InternalFrame2initPetCart();
		
		InternalFrameTransHistory();
		
		InternalFrameinitTransHistory();
		
		warnawarni();
		
		arg0.setScene(scene);
		arg0.show();
		validuser();
	}

	
}



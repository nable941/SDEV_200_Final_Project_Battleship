package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class PlacementControler {
	
	@FXML
	Label lblPlayerBoard;
	@FXML
	TextField tfPlaceCoordinate;
	@FXML
	Label lblShipSize;
	@FXML
	RadioButton rdoHorizontal;
	@FXML
	RadioButton rdoVertical;
	@FXML
	Button btnPlace;
	
	protected boolean isValid;
	protected String computerStart;
	protected boolean computerOrientation;
	private String coordinate;
	private boolean isHorizontal;
	private Player player1;
	private Player player2;
	private int size;
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	public void placeShip(ActionEvent event) throws IOException {
		isValid = false;
		
			coordinate = tfPlaceCoordinate.getText();
			isValid = Gameboard.coordinatesValid(coordinate);
			if (rdoHorizontal.isSelected()) {
				isHorizontal = true;
			}else {
				isHorizontal = false;
			}
			size = player1.getSize();
			if(!isValid) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid Coordinate");
				alert.setContentText("Enter the coordinate letter first then number without a space./n"
						+ "Letters A-J, Numbers 0-9");
				alert.setHeaderText("Issue with coordinate: "+ coordinate);
				alert.show();
			}else {
				isValid = Player.willFit(coordinate, size, isHorizontal);
				if(!isValid) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Ship placement error");
					alert.setHeaderText("Will not fit");
					String orientation = isHorizontal ? "horizontally" : "vertically";
					alert.setContentText("A ship of size " + size + "will not fit "+ orientation + " at " + coordinate + ".");
					alert.show();
				}else {
					player1.placeShip(coordinate, size, isHorizontal, player1.getShipTracker());
					setComputer();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
					root = loader.load();
					
					GameControler gameControler = loader.getController();

					gameControler.setMap(player1.displayGameboards());
					gameControler.setPlayers(player1,  player2);
					gameControler.setAttacks(player1.getGuns());
					
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}
			}
		
		
		
	}
	
	public void setMap(Gameboard map, int size) {
		String placementMap = map.toString();
		
		lblPlayerBoard.setText(placementMap);
		lblShipSize.setText("You ship size is " + size);
	}
	
	public void setPlayers(Player p1, Player p2) {
		this.player1 = p1;
		this.player2 = p2;
	}
	
	public void setComputer() {
		isValid = false;
		
		//Randomly place player2 ship
		do {
			computerStart = Player.generateRandomCoordinate();
			computerOrientation = Player.generateRandomOneTwo() == 1 ? true : false;
			isValid = Player.willFit(computerStart, player2.getSize(), computerOrientation);		
		}while(!isValid);
		player2.placeShip(computerStart, player2.getSize(), computerOrientation, player2.getShipTracker());
	}
}

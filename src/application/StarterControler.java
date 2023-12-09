/**
 * Controller class for the document {@code StartScene.fxml}
 * @author Nathan Able
 * @version 1.0
 */
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StarterControler {

	@FXML
	TextField tfName;
	@FXML
	Button btnVeryEasy;
	@FXML
	Button btnEasy;
	@FXML
	Button btnNormal;
	@FXML
	Button btnHard;
	@FXML
	Button btnVeryHard;
	
	private Player player1;
	private Player player2;
	private Stage stage;
	private Scene scene;
	private Parent root;
	/**
	 * Handles the event when any of the difficulty buttons are clicked. 
	 * @param event (btnVeryEasy || btnEasy || btnNormal || btnHard || btnVeryHard) clicked
	 * @throws IOException
	 * 
	 */
	@FXML
	public void setDifficulty(ActionEvent event)throws IOException {

		Button clickedButton = (Button)event.getSource();
		String difficulty = clickedButton.getId();
		String name = tfName.getText();
		if (name.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Name Entered");
			alert.setContentText("You must enter a name for yourself");
			alert.show();
		}else {
			
			if(difficulty.equalsIgnoreCase("btnVeryEasy")) {
				player1 = new Player(name, "Carrier", 5, 3);
				player2 = new Player("Computer","Carrier", 5, 3);
			}else if (difficulty.equalsIgnoreCase("btnEasy")) {
				player1 = new Player(name, "Battleship", 4, 2);
				player2 = new Player("Computer", "Battleship", 4, 2);
			}else if (difficulty.equalsIgnoreCase("btnNormal")) {
				player1 = new Player(name, "Crusier", 3, 2);
				player2 = new Player("Computer", "Crusier", 3, 2);
			}else if (difficulty.equalsIgnoreCase("btnHard")) {
				player1 = new Player(name, "Submarine", 3, 1);
				player2 = new Player("Computer", "Submarine", 3, 1);
			}else if (difficulty.equalsIgnoreCase("btnVeryHard")) {
				player1 = new Player(name, "Destroyer", 2, 1);
				player2 = new Player("Computer", "Destroyer", 2, 1);
			}else {
				System.out.println(clickedButton.getId());
				System.out.println(clickedButton.getId()=="btnVeryEasy");
				System.out.println(difficulty.equalsIgnoreCase("btnVeryEasy"));;
			}
			try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PlacementScene.fxml"));
			root = loader.load();
			
			PlacementControler placementController = loader.getController();
			Gameboard map = player1.getShipTracker();
			int shipSize = player1.getSize();
			placementController.setMap(map, shipSize);
			placementController.setPlayers(player1,  player2);
			}catch(Exception e) {
				System.out.println(e);
				
			}
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
}

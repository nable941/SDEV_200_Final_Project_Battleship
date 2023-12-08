package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameControler {
	@FXML
	Label lblGameboard;
	@FXML
	Label lblAttack1;
	@FXML
	Label lblAttack2;
	@FXML
	Label lblAttack3;
	@FXML
	TextField tfAttack1;
	@FXML
	TextField tfAttack2;
	@FXML
	TextField tfAttack3;
	@FXML
	Button btnAttack;
	
	private boolean haveWinner;
	private boolean isValid;
	private Player player1;
	private Player player2;
	private String coordinate;
	
	String[] attack = new String[3];

	
	public void setPlayers(Player p1, Player p2) {
		player1 = p1;
		player2 = p2;
		
	}
	public void setMap(String map) {
		lblGameboard.setText(map);
	}
	
	public void setAttacks(int attacks) {
		if(attacks >1) {
			tfAttack2.setVisible(true);
			lblAttack2.setVisible(true);
		}
		if (attacks > 2) {
			tfAttack3.setVisible(true);
			lblAttack3.setVisible(true);
		}
	}
	
	@FXML
	public void attack(ActionEvent event) {
		Alert player2Turn = new Alert(AlertType.INFORMATION);
		player2Turn.setTitle(player2.getName() + "'s turn");
		player2Turn.setContentText("Attacks complete. " + player2.getName() + "'s ship is attacking");
		
		
		haveWinner = false;
		attack[0] = tfAttack1.getText();
		tfAttack1.setText("");
		if (player1.getGuns()>1) {
			attack[1] = tfAttack2.getText();
			tfAttack2.setText("");
		}
		if (player1.getGuns()>2) {
			attack[2] = tfAttack3.getText();
			tfAttack3.setText("");
		}
		for (int i = 0; i<= player1.getGuns()-1; i++) {
			isValid = Gameboard.coordinatesValid(attack[i]);
			player1.attackOpponent(attack[i], player2);
		}
		setMap(player1.displayGameboards());
		if (player1.getScore()==player2.getSize()) {
			haveWinner = true;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Winner");
			alert.setContentText(player1.getName() + " Wins");
			alert.show();
		}
		if(!haveWinner) {
			player2Turn.showAndWait();
			for( int i= 1; i <= player2.getGuns(); i++) {
				if (i== 1) {
					coordinate = player2.getComputerAttackCoordinates(player2.getFirstAttackCords(), player2.getFirstAttack());
					player2.attackOpponent(coordinate, player1);
					player2.setFirstAttackCords(coordinate);
					player2.setFirstAttack(player2.getAttackTracker().getCoordinateValue(Gameboard.convertRow(coordinate), Gameboard.convertColumn(coordinate)));
					lblAttack1.setText(coordinate);
					
				}else if (i==2) {
					coordinate = player2.getComputerAttackCoordinates(player2.getSecondAttackCords(), player2.getSecondAttack());
					player2.attackOpponent(coordinate, player1);
					player2.setSecondAttackCords(coordinate);
					player2.setSecondAttack(player2.getAttackTracker().getCoordinateValue(Gameboard.convertRow(coordinate), Gameboard.convertColumn(coordinate)));
					lblAttack2.setText(coordinate);
				}else if (i == 3) {
					coordinate = player2.getComputerAttackCoordinates(player2.getThirdAttackCords(), player2.getThirdAttack());
					player2.attackOpponent(coordinate, player1);
					player2.setThirdAttackCords(coordinate);
					player2.setThirdAttack(player2.getAttackTracker().getCoordinateValue(Gameboard.convertRow(coordinate), Gameboard.convertColumn(coordinate)));
					lblAttack3.setText(coordinate);
				}
			}
		}
		setMap(player1.displayGameboards());

		if (player2.getScore() == player1.getSize()) {
			haveWinner = true;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Winner");
			alert.setContentText(player2.getName() + " Wins");
			alert.show();
		}
	}
}

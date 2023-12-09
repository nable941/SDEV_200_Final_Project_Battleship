/**
 * Launch class for the Battleship application
 * @author Nathan Able
 * @version 1.0
 */
package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	/**
	 * Initializes the window created from the StartScene {@link StartScene.fxml}
	 */
	@Override
	public void start(Stage stage) throws IOException {
		//Launch program with the starter window
		Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Launches the application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

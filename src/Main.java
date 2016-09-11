

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main program, it is basically boilerplate to create animated scene
 * Dependencies: Game Object
 * Assumption: Assumes there is an init function in Game Class
 * @author Pratiksha Sharma
 */

public class Main extends Application {
	private Game myGame;
	
	/**
	 * Set things up at the beginning.
	 */	
	@Override
	public void start (Stage stage) {
		// create your own game here
		myGame = new Game();
		stage.setTitle(myGame.getTitle());    
		Scene scene = myGame.init();
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}

	/**
	 * Exit the program.
	 */
	public static void exitGame(){
		Platform.exit();
		System.exit(0);
	}
}




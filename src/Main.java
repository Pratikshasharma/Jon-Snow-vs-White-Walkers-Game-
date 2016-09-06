
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 749;
	
    /**
     * Set things up at the beginning.
     */
    private Game myGame;
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
}

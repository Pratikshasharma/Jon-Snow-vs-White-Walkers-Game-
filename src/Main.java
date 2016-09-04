
import java.awt.Button;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 639;
    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    /**
     * Set things up at the beginning.
     */
    private Game myGame;
    @Override
    public void start (Stage stage) {
        // create your own game here
        myGame = new Game();
        stage.setTitle(myGame.getTitle());
        
        // attach game to the stage and display it
        Scene scene = myGame.init(WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
        
        Button startButton = new Button("START");
        Button exitButton = new Button("EXIT");
        //startButton = startButton.stage;
        
        // sets the game's loop
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),e -> myGame.step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;


class Game {

	public static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private Scene myScene;
	private Welcome welcome;
	private Levels myLevel;

	public static final String TITLE = "Jon Snow vs The White Walkers";

	Button startButton = new Button("START");
	Button exitButton = new Button("EXIT");

	/**
	 * Returns name of the game.
	 */
	public String getTitle () {
		return TITLE;
	}

	public Game(){
		 welcome = new Welcome();
		 myLevel = new Levels();
	}
	
	public Scene init () {
		// create a scene graph to organize the scene
		buttonSettings();
		myScene = new Scene(welcome.createRoot(startButton, exitButton), Main.WIDTH, Main.HEIGHT, Color.WHITE); 

		// Check for the Event
		startButton.setOnAction(e -> startGame());
		myScene.setOnKeyPressed(e -> myLevel.handleKeyInput(e.getCode()));


		// sets the game's loop
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),e -> myLevel.step(SECOND_DELAY));

		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();

		return myScene;
	} 

	public  void startGame(){
		myScene.setRoot(myLevel.createRoot());
	}

	public void buttonSettings(){
		exitButton.setLayoutX(Main.WIDTH/1.8);
		startButton.setLayoutX(Main.WIDTH/1.8 + 50);
		exitButton.setLayoutY(Main.HEIGHT- Main.HEIGHT/6);
		startButton.setLayoutY(Main.HEIGHT - Main.HEIGHT/6);
	}
}





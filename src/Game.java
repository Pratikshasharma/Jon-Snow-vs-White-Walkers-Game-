

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Separate the game code from some of the boilerplate code.
 * Controls the flow of the code based on Level of Game
 * Dependencies: Objects- myFirstLevel, mySecondLevel, step function in both Level1 and Level2 classes
 * @author Pratiksha Sharma
 */
class Game {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 749;
	public static final int LIVES = 5;
	public static final String TITLE = "Jon Snow vs The White Walkers";

	private Scene myScene;
	private Welcome welcome;

	private boolean isLevel1 = true;
	private Level1 myFirstLevel;
	private Level2 mySecondLevel;

	private Button startButton = new Button("START");
	private Button exitButton = new Button("EXIT");
	

	/**
	 * Returns name of the game.
	 */
	public String getTitle () {
		return TITLE;
	}


	public Game(){
		welcome = new Welcome();
		myFirstLevel = new Level1();
		mySecondLevel = new Level2();
	}

	/**
	 * Creates the Game's Scene
	 * Sets the animation for the game
	 * @return myScene scene for the Stage
	 */
	public Scene init () {
		setButtonSettings();
		myScene = new Scene(welcome.createRoot(startButton, exitButton), WIDTH, HEIGHT, Color.WHITE); 
		startButton.setOnAction(e -> startGame());
		exitButton.setOnAction(e-> Main.exitGame());
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode(),true));
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),e -> this.step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		return myScene;
	} 


	private LevelController levelManager(){
		if (isLevel1) { return myFirstLevel;};
		return mySecondLevel;	
	}

	private void startGame(){
		myScene.setRoot(levelManager().createSceneNodes());
	}

	private void setButtonSettings(){
		exitButton.setLayoutX(WIDTH/1.8);
		startButton.setLayoutX(WIDTH/1.8 + 50);
		exitButton.setLayoutY(HEIGHT-HEIGHT/6);
		startButton.setLayoutY(HEIGHT - HEIGHT/6);
	}

	private void step(){
		boolean changeLevel = levelManager().updateLevel();
		if(changeLevel) {
			isLevel1=false;
			startGame();
		}
	}

	private void handleKeyInput(KeyCode code, boolean isLevel1){
		levelManager().handleKeyInput(code);
	}
}





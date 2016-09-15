import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

// This entire file is a part of my masterpiece
// Pratiksha Sharma

/**This class contains the common behaviors in both Level 1 and level 2. It contains functions to create different nodes in the Scene
 *root and common conditions that need to be checked in both levels. 
 *This is a good piece of code, because it is an abstract super class that can be inherited in Level classes. Extending the game to more than
 *2 levels becomes much easier because of this super class. The abstract methods- updateLevel() and createsceneNodes() leaves the implementation up
 *to the Level classes that inherit this class. 
 *However, this class does too many things at the moment. It could have been made much better by dividing it to control static and dynamic
 *objects separately in the Scene.
 *
 * @author Pratiksha Sharma
 */
public abstract class LevelController {
	private ImageView backgroundImage;
	protected ImageView myIronThrone;
	private Enemy myEnemy;
	private boolean shootBall;
	private Collection<Enemy> myEnemyList = new ArrayList<Enemy>();
	public static final double BALL_SPEED = 800;
	public static final int PLAYER_SPEED = 30;
	private static final int ENEMY_COUNT = 15;
	public static final double ENEMY_SPEED = 30;
	private static final String THRONE_IMAGE_FILE = "throne.gif";
	private static final String HIT_MUSIC_FILE = "HIT.mp3";
	private static final String REPLAY_MUSIC_FILE = "Replay.m4a";
	private static final String GAME_OVER_MUSIC_FILE = "Death.mp3"; 
	protected Button exitButton;
	protected Player myPlayer;
	protected ScoreBoard myScoreBoard;
	protected boolean startExitScreen;
	protected boolean addEnemiesTwice = true;
	protected SnowBall mySnowBall;
	protected Group root;

	public LevelController() {
		myPlayer = new Player();
		myScoreBoard = new ScoreBoard();
		root = new Group();
	}

	/**
	 * Adds the common nodes needed in the Scene in both Levels.
	 */
	public Group addCommonNodes() {
		addBackgroundImage();
		addPlayer();
		addIronThrone();
		addEnemies();
		addBall();
		displayScoreBoard(false);
		return root;
	}

	private void addPlayer() {
		myPlayer.setPlayerPosition(50, Game.HEIGHT-250);
		root.getChildren().add(myPlayer.getImageView());
	}

	/**
	 * Adds enemies to the Scene Root node.
	 */
	protected void addEnemies() {
		for (int i = 0; i < ENEMY_COUNT; i++) {
			myEnemy = new Enemy();
			double tempWidth = Math.random() * (Game.WIDTH - (0.8*Game.WIDTH)) + (0.8*Game.WIDTH);
			myEnemy.spwanEnemy(tempWidth, Game.HEIGHT - i * 60);
			myEnemy.spwanEnemy(tempWidth, Game.HEIGHT - i * 60);
			if(!checkOutOfBounds(myEnemy.getImageView().getX(),myEnemy.getImageView().getY()+ myEnemy.getImageView().getFitHeight())){		
				root.getChildren().add(myEnemy.getImageView());
				myEnemyList.add(myEnemy);
			}
		}
	}

	private void addBall() {
		mySnowBall = new SnowBall();
		mySnowBall.setSnowBallPosition((myPlayer.getImageView().getX() + 0.4*myPlayer.getImageView().getBoundsInLocal().getWidth()) , (myPlayer.getImageView().getY() + 0.5*myPlayer.getImageView().getBoundsInLocal().getHeight()));
		root.getChildren().add(mySnowBall.getImageView());
	}

	private void addIronThrone() {
		myIronThrone = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(THRONE_IMAGE_FILE)));
		myIronThrone.setX(Game.WIDTH - myIronThrone.getBoundsInLocal().getWidth() - 10);
		myIronThrone.setY(Game.HEIGHT / 2 - myIronThrone.getBoundsInLocal().getHeight());
		root.getChildren().add(myIronThrone);
	}

	/**
	 * Updates the step function in the loop based on the Game level
	 * Implementation done in Level 1 and level 2 classes
	 */
	public abstract boolean updateLevel();

	/**
	 * Creates the nodes in the Scene based on the game Level
	 */
	public abstract Group createSceneNodes();

	/**
	 * Has common Checks to be called in the step function from both game levels
	 * Checks if the enemy is dead, the player is dead, or the player has run
	 * out of lives. Updates and Displays Scoreboard
	 */
	public void stepHelper() {
		for (Enemy tempEnemy : myEnemyList) {
			tempEnemy.getImageView().setX(tempEnemy.getImageView().getX() -  2 * ENEMY_SPEED* Game.SECOND_DELAY);
			if ((!tempEnemy.isDead()) && checkCollision(tempEnemy.getImageView().getBoundsInParent(),mySnowBall.getImageView().getBoundsInParent())) {
				playMusic(HIT_MUSIC_FILE);
				myScoreBoard.updateScoreBoard();
				removeDeadEnemyFromScreen(tempEnemy);
				addBall();
				displayScoreBoard(true);
				break;
			}

			if ((!myPlayer.isDead()) && (!tempEnemy.isDead()) && checkCollision(tempEnemy.getImageView().getBoundsInParent(),myPlayer.getImageView().getBoundsInParent())) {
				myPlayer.setDead(true);
				myPlayer.getImageView().setVisible(false);
				mySnowBall.getImageView().setVisible(false);
				myScoreBoard.updateLives();
				displayScoreBoard(true);
				break;
			}
			if (shootBall) {
				mySnowBall.moveBall();
				if (mySnowBall.getImageView().getX() >= Game.WIDTH) {
					addBall();
					shootBall = false;
				}
			}
		}

		if (myPlayer.isDead()) {
			if (myScoreBoard.getLives() > 0) {
				playMusic(REPLAY_MUSIC_FILE);
				myPlayer.setDead(false);
				myPlayer.setPlayerPosition(50, Game.HEIGHT-250);
				addBall();
			} else if (!startExitScreen) {
				playMusic(GAME_OVER_MUSIC_FILE);
				updateSplashScreen("You Lost.");
				addExitButton();
				startExitScreen = true;
			}
		}
		if (startExitScreen) {
			exitButton.setOnAction(e -> Main.exitGame());
		}
	}

	/**
	 * Controls what to do each time a key is pressed
	 */
	protected void handleKeyInput(KeyCode code) {
		switch (code) {
		case SPACE:
			shootBall = true;
			break;
		case RIGHT:
			if (myPlayer.getImageView().getX() <= Game.WIDTH - myPlayer.getImageView().getBoundsInLocal().getWidth()) {
				myPlayer.getImageView().setX(myPlayer.getImageView().getX() + PLAYER_SPEED);
				mySnowBall.getImageView().setX(mySnowBall.getImageView().getX() + PLAYER_SPEED);		
			}
			break;

		case LEFT:
			if (myPlayer.getImageView().getX() > 0) {
				myPlayer.getImageView().setX(myPlayer.getImageView().getX() - PLAYER_SPEED);
				mySnowBall.getImageView().setX(mySnowBall.getImageView().getX() - PLAYER_SPEED);	
			}
			break;
		case UP:
			if (myPlayer.getImageView().getY() > 0) {
				myPlayer.getImageView().setY(myPlayer.getImageView().getY() - PLAYER_SPEED);
				mySnowBall.getImageView().setY(mySnowBall.getImageView().getY() - PLAYER_SPEED);
			}
			break;
		case DOWN:
			if (myPlayer.getImageView().getY() <= Game.HEIGHT- myPlayer.getImageView().getBoundsInLocal().getHeight()- 80) {
				myPlayer.getImageView().setY(myPlayer.getImageView().getY() + PLAYER_SPEED);
				mySnowBall.getImageView().setY(mySnowBall.getImageView().getY() + PLAYER_SPEED);
			}
			break;

		case C:
			for (Enemy myCheatEnemy : myEnemyList) {
				removeDeadEnemyFromScreen(myCheatEnemy);
				playMusic(HIT_MUSIC_FILE);
				myScoreBoard.updateScoreBoard();
				displayScoreBoard(true);
				myEnemyList.remove(myCheatEnemy);
				break;
			}
			break;
		default:
			// do nothing
		}
	}

	/**
	 * Displays the Scoreboard
	 * @param deleteScoreBoardNodes boolean to reset ScoreBoard nodes if already in the root  
	 */

	protected void displayScoreBoard(boolean updateScoreBoardNodes) {
		if(!updateScoreBoardNodes){
			root.getChildren().add(myScoreBoard.getScoreText());
			root.getChildren().add(myScoreBoard.getDeadWhiteWalkersText());
			root.getChildren().add(myScoreBoard.getLivesText());
		}
		myScoreBoard.setScoreText(myScoreBoard.getScoreText());
		myScoreBoard.setLivesText(myScoreBoard.getLivesText());
		myScoreBoard.setWhiteWalkerCoundText(myScoreBoard.getDeadWhiteWalkersText());		
	}


	/**
	 * Updates the Splash Screen once Level is Lost, or Game is Won
	 * @param myString text to be displayed in the Splash Screen at the end of the Level
	 */

	protected void updateSplashScreen(String myString) {
		root.getChildren().clear();
		ImageView jonOnThrone = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("JonOnThrone.gif")));
		Text gameOverText = myScoreBoard.createTextObject(myString,0.5 * Game.WIDTH, 0.5 * Game.HEIGHT, 40, Color.RED);
		root.getChildren().add(jonOnThrone);
		root.getChildren().add(gameOverText);
		displayScoreBoard(true);
	}

	/**
	 * Adds exitButton in splash Screen
	 */
	protected void addExitButton() {
		exitButton = new Button("EXIT GAME");
		exitButton.setLayoutX(0.5 * Game.WIDTH);
		exitButton.setLayoutY(0.6 * Game.HEIGHT);
		root.getChildren().add(exitButton);
	}

	private void removeDeadEnemyFromScreen(Enemy deadEnemy) {
		deadEnemy.setDead(true);
		myEnemyList.remove(deadEnemy);
		deadEnemy.getImageView().setVisible(false);
		mySnowBall.getImageView().setVisible(false);
	}

	private void addBackgroundImage() {
		backgroundImage = new ImageView(new Image(getClass().getResourceAsStream("Background.gif")));
		root.getChildren().add(backgroundImage);
	}

	protected void addInstructionToWin(String myString) {
		Text myText = myScoreBoard.createTextObject(myString, 40, 30, 15,Color.BLUE);
		root.getChildren().add(myText);
	}

	protected void addLevel(String myLevel) {
		Text myText = myScoreBoard.createTextObject(myLevel, Game.WIDTH / 2,20, 20, Color.CRIMSON);
		root.getChildren().add(myText);
	}

	/**
	 * Plays the music file sent as a String
	 * @param myFile name of the file of music to be played
	 */
	protected void playMusic(String myFile) {
		File newfile = new File(myFile);
		if (newfile.exists()){
			Media sound = new Media(newfile.toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();	
		}
	}

	/**
	 * Checks if Player Completed the Level by reaching the throne
	 * @return returns boolean if player completed Level
	 */
	protected boolean checkCollision( Bounds myBound1, Bounds myBound2 ) {
		return (myBound1.intersects(myBound2));
	}
	protected boolean checkOutOfBounds(double xPosition, double yPosition){
		if (xPosition >= Game.WIDTH || xPosition <=0 || yPosition >=Game.HEIGHT || yPosition<=0 ){
			return true;
		}
		return false;	
	}
}

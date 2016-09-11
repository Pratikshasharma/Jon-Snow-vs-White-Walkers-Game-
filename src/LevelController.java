import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * 
 * Super-Class: Contains all common features needed in both Level 1 and Level 2 Class
 * Assumption: Assumes there are the following classes: Player, Enemy,ScoreBoard and SnowBall 
 * Dependencies: Depends on the the Assumed Classes above,Static constants defined in Game Class(Width, Height, etc)
 * Background.gif, jonOnThronegif, Replay.m4a,HIT.mp3
 * @author Pratiksha Sharma
 */
public abstract class LevelController {
	private ImageView backgroundImage;
	private ImageView myIronThrone;
	private Enemy myEnemy;
	private boolean shootBall;
	private Collection<Enemy> myEnemyList = new ArrayList<Enemy>();
	public static final double BALL_SPEED = 800;
	public static final int PLAYER_SPEED = 30;
	public static final double ENEMY_SPEED = 30;
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
		myPlayer.setPlayerPosition();
		root.getChildren().add(myPlayer.getImageView());
	}

	/**
	 * Adds enemies to the Scene Root node.
	 */

	protected void addEnemies() {
		for (int i = 0; i < 20; i++) {
			myEnemy = new Enemy();
			double minWidth = 0.8 * Game.WIDTH;
			double tempWidth = Math.random() * (Game.WIDTH - minWidth)
					+ minWidth;
			myEnemy.spwanEnemy(tempWidth, Game.HEIGHT - i * 60);
			if (myEnemy.getImageView().getX() < Game.WIDTH
					&& myEnemy.getImageView().getY() >= 0
					&& myEnemy.getImageView().getY() + 100 <= Game.HEIGHT) {
				root.getChildren().add(myEnemy.getImageView());
				myEnemyList.add(myEnemy);
			}
		}
	}

	private void addBall() {
		mySnowBall = new SnowBall();
		mySnowBall.setSnowBallPosition(myPlayer.getImageView().getX(), myPlayer
				.getImageView().getY());
		root.getChildren().add(mySnowBall.getImageView());
	}

	private void addIronThrone() {
		myIronThrone = new ImageView(new Image(getClass().getClassLoader()
				.getResourceAsStream("Throne.gif")));
		myIronThrone.setX(Game.WIDTH
				- myIronThrone.getBoundsInLocal().getWidth() - 10);
		myIronThrone.setY(Game.HEIGHT / 2
				- myIronThrone.getBoundsInLocal().getHeight());
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
			tempEnemy.getImageView().setX(
					tempEnemy.getImageView().getX() - 1 * 2 * ENEMY_SPEED
							* Game.SECOND_DELAY);
			if (checkBallEnemyCollision(tempEnemy)) {
				myEnemyList.remove(tempEnemy);
				playMusic("HIT.mp3");
				displayScoreBoard(true);
				break;
			}

			if (checkEnemyPlayerCollision(tempEnemy)) {
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
			playMusic("Replay.m4a");
			if (myScoreBoard.getLives() > 0) {
				myPlayer.setDead(false);
				myPlayer.setPlayerPosition();
				mySnowBall.setSnowBallPosition(myPlayer.getImageView().getX(),
						myPlayer.getImageView().getY());
			} else if (!startExitScreen) {
				playMusic("Death.mp3");
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
			if (myPlayer.getImageView().getX() <= Game.WIDTH
					- myPlayer.getImageView().getBoundsInLocal().getWidth()) {
				myPlayer.getImageView().setX(
						myPlayer.getImageView().getX() + PLAYER_SPEED);
				mySnowBall.getImageView().setX(
						mySnowBall.getImageView().getX() + PLAYER_SPEED);
				break;
			}

		case LEFT:
			if (myPlayer.getImageView().getX() > 0) {
				myPlayer.getImageView().setX(
						myPlayer.getImageView().getX() - PLAYER_SPEED);
				mySnowBall.getImageView().setX(
						mySnowBall.getImageView().getX() - PLAYER_SPEED);
				break;
			}
		case UP:
			if (myPlayer.getImageView().getY() > 0) {
				myPlayer.getImageView().setY(
						myPlayer.getImageView().getY() - PLAYER_SPEED);
				mySnowBall.getImageView().setY(
						mySnowBall.getImageView().getY() - PLAYER_SPEED);
				break;
			}
		case DOWN:
			if (myPlayer.getImageView().getY() <= Game.HEIGHT
					- myPlayer.getImageView().getBoundsInLocal().getHeight()
					- 80) {
				myPlayer.getImageView().setY(
						myPlayer.getImageView().getY() + PLAYER_SPEED);
				mySnowBall.getImageView().setY(
						mySnowBall.getImageView().getY() + PLAYER_SPEED);
				break;
			}

		case C:
			if (myPlayer.getImageView().getY() <= Game.HEIGHT
					- myPlayer.getImageView().getBoundsInLocal().getHeight()
					- 70) {
				for (Enemy myCheatEnemy : myEnemyList) {
					playMusic("HIT.mp3");
					removeDeadEnemyFromScreen(myCheatEnemy);
					myScoreBoard.updateScoreBoard();
					displayScoreBoard(true);
					myEnemyList.remove(myCheatEnemy);
					break;
				}
			}
		default:
			// do nothing
		}
	}

	/**
	 * Displays the Scoreboard
	 * 
	 * @param deleteScoreBoardNodes
	 *            boolean to delete ScoreBoard nodes if already in the root
	 */

	protected void displayScoreBoard(boolean deleteScoreBoardNodes) {
		if (deleteScoreBoardNodes) {
			updateScoreBoardNodes();
		}
		root.getChildren().add(myScoreBoard.getScoreText());
		root.getChildren().add(myScoreBoard.getDeadWhiteWalkersText());
		root.getChildren().add(myScoreBoard.getLivesText());
	}

	private void updateScoreBoardNodes() {
		root.getChildren().remove(myScoreBoard.getLivesText());
		root.getChildren().remove(myScoreBoard.getDeadWhiteWalkersText());
		root.getChildren().remove(myScoreBoard.getScoreText());
	}

	/**
	 * Updates the Splash Screen once Level is Lost, or Game is Won
	 * 
	 * @param myString
	 *            text to be displayed in the Splash Screen at the end of the
	 *            Level
	 */

	protected void updateSplashScreen(String myString) {
		root.getChildren().clear();
		ImageView jonOnThrone = new ImageView(new Image(getClass()
				.getClassLoader().getResourceAsStream("JonOnThrone.gif")));
		Text gameOverText = myScoreBoard.createTextObject(myString,
				0.5 * Game.WIDTH, 0.5 * Game.HEIGHT, 40, Color.RED);
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
		deadEnemy.getImageView().setVisible(false);
		mySnowBall.getImageView().setVisible(false);
		myEnemyList.remove(deadEnemy);
		addBall();
	}

	private void addBackgroundImage() {
		backgroundImage = new ImageView(new Image(
				getClass().getResourceAsStream("Background.gif")));
		root.getChildren().add(backgroundImage);
	}

	protected void addInstructionToWin(String myString) {
		Text myText = myScoreBoard.createTextObject(myString, 40, 30, 15,
				Color.BLUE);
		root.getChildren().add(myText);
	}

	protected void addLevel(String myLevel) {
		Text myText = myScoreBoard.createTextObject(myLevel, Game.WIDTH / 2,
				20, 20, Color.CRIMSON);
		root.getChildren().add(myText);
	}

	private boolean checkBallEnemyCollision(Enemy myTempEnemy) {
		if ((!myTempEnemy.isDead())
				&& myTempEnemy
						.getImageView()
						.getBoundsInParent()
						.intersects(
								mySnowBall.getImageView().getBoundsInParent())) {
			removeDeadEnemyFromScreen(myTempEnemy);
			myScoreBoard.updateScoreBoard();
			mySnowBall.getImageView().setVisible(false);

			return true;
		}
		return false;
	}

	private boolean checkEnemyPlayerCollision(Enemy myTempEnemy) {
		if ((!myTempEnemy.isDead())
				&& (!myPlayer.isDead())
				&& myTempEnemy
						.getImageView()
						.getBoundsInParent()
						.intersects(myPlayer.getImageView().getBoundsInParent())) {
			myPlayer.setDead(true);
			myPlayer.getImageView().setVisible(false);
			mySnowBall.getImageView().setVisible(false);
			myScoreBoard.updateLives();
			return true;
		}
		return false;
	}

	/**
	 * Plays the music file sent as a String
	 * 
	 * @param myFile name of the file of music to be played
	 */

	protected void playMusic(String myFile) {
		Media sound = new Media(new File(myFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

	/**
	 * Checks if Player Completed the Level by reaching the throne
	 * @return returns boolean if player completed Level
	 */
	protected boolean checkPlayerThroneCollision() {
		return (myPlayer.getImageView().getBoundsInParent()
				.intersects(myIronThrone.getBoundsInParent()));
	}
}

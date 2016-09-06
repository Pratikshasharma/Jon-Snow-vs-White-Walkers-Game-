
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Levels{	
	ImageView backgroundImage;
	ImageView myIronThrone;
	Player myPlayer;
	double time;
	Enemy myEnemy;
	ScoreBoard myScoreBoard;
	SnowBall mySnowBall;
	private ArrayList<Enemy> myEnemyList = new ArrayList<Enemy>();
	private static final double BALL_SPEED = 40;
	public static final int PLAYER_SPEED = 4;
	public static final double ENEMY_SPEED = 30;
	
	Group root = new Group();
	
	public Levels() {
		myPlayer = new Player();
		mySnowBall = new SnowBall();
		myScoreBoard = new ScoreBoard();
	}

	Group createRoot(){
		backgroundImage = new ImageView(new Image(Levels.class.getResourceAsStream("background.gif")));
		root.getChildren().add(backgroundImage);

		addPlayer();
		addIronThrone();
		addBall();
		addEnemies();
		// add ScoreBoard
		root.getChildren().add(myScoreBoard.getScoreText());
		root.getChildren().add(myScoreBoard.getWhiteWalkersCount());
		return root;	
	}

	private void addPlayer(){
		myPlayer.createPlayer();
		root.getChildren().add(myPlayer.getImageView());
	}

	private void addEnemies(){
		for ( int i=0;i<=20;i++){	
			myEnemy = new Enemy();
			double maxWidth = Main.WIDTH - myIronThrone.getBoundsInLocal().getWidth();
			double minWidth = Main.WIDTH/3;
			double tempWidth =  Math.random() * (maxWidth-minWidth) + minWidth; 
			//double tempWidth =  Math.random() * (200) + minWidth; 
			//myEnemy.spwanEnemy(tempWidth,( Main.HEIGHT - i*myEnemy.getImageView().getBoundsInLocal().getHeight()) + i*20);
			myEnemy.spwanEnemy(tempWidth, i*110 + i*5);
			if (myEnemy.getImageView().getX() <= Main.WIDTH && myEnemy.getImageView().getY() <= Main.HEIGHT) {
				root.getChildren().add(myEnemy.getImageView());
				myEnemyList.add(myEnemy);
			}
		}
	}

	private void addBall(){
		mySnowBall.createSnowBall();
		root.getChildren().add(mySnowBall.getImageView());
	}

	private void addIronThrone(){
		myIronThrone = new ImageView(new Image(Levels.class.getResourceAsStream("Throne.gif")));
		myIronThrone.setX(Main.WIDTH - myIronThrone.getBoundsInLocal().getWidth()-10);
		myIronThrone.setY(Main.HEIGHT/2 - myIronThrone.getBoundsInLocal().getHeight());
		root.getChildren().add(myIronThrone);
	}

	public void step (double elapsedTime){
		//UpdateEnemies and Check for Collision
		time = elapsedTime;
		for (Enemy tempEnemy: myEnemyList){
			tempEnemy.getImageView().setX(tempEnemy.getImageView().getX()  - 1 * 2* ENEMY_SPEED * time );

			if (tempEnemy.getImageView().getBoundsInParent().intersects(mySnowBall.getImageView().getBoundsInParent())){
				tempEnemy.dead= true;
				tempEnemy.getImageView().setVisible(false);
				// remove from the list
				myEnemyList.remove(tempEnemy);
				
			}
			// Collision with the Player
			if (tempEnemy.getImageView().getBoundsInParent().intersects(myPlayer.getImageView().getBoundsInParent())){
				myPlayer.dead= true;
				myPlayer.getImageView().setVisible(false);
				// Call a Method for Ending the Game
			}
			
			//Update the Score if an enemy is dead
			if (tempEnemy.dead){
				
			}
		}
	}	

	public void handleKeyInput(KeyCode code){	
		switch (code) {
		case SPACE:
			//double initialWidth = mySnowBall.getImageView().getX();
			//double initialHeight = mySnowBall.getImageView().getY();

			mySnowBall.getImageView().setX(mySnowBall.getImageView().getX() +  2* BALL_SPEED * time);
			//return SnowBall back to Player's Hand
			//			mySnowBall.getImageView().setX(initialWidth);
			//			mySnowBall.getImageView().setX(initialHeight);
			break;	

		case RIGHT:
			if (myPlayer.getImageView().getX() <= Main.WIDTH){
				myPlayer.getImageView().setX(myPlayer.getImageView().getX() + PLAYER_SPEED);
				mySnowBall.getImageView().setX(mySnowBall.getImageView().getX()+PLAYER_SPEED);
				break;
			}
			
		case LEFT:
			if (myPlayer.getImageView().getX() >0){
				myPlayer.getImageView().setX(myPlayer.getImageView().getX() - PLAYER_SPEED);
				mySnowBall.getImageView().setX(mySnowBall.getImageView().getX() - PLAYER_SPEED);
				break;
			}
		case UP:
			if (myPlayer.getImageView().getY() >0){
				myPlayer.getImageView().setY(myPlayer.getImageView().getY() - PLAYER_SPEED);
				mySnowBall.getImageView().setY(mySnowBall.getImageView().getY() - PLAYER_SPEED);
				break;
			}
		case DOWN:
			if (myPlayer.getImageView().getY() <= Main.HEIGHT){
				myPlayer.getImageView().setY(myPlayer.getImageView().getY() + PLAYER_SPEED);
				mySnowBall.getImageView().setY(mySnowBall.getImageView().getY() + PLAYER_SPEED);
				break;
			}
		default:
			// do nothing
		}
	}

}

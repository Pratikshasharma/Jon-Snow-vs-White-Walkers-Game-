
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;

/**
 * separate functions needed in Level 2 of the game
 * @author Pratiksha Sharma
 *Dependencies: LevelController Super Class, Obstacle Class 
 *Assumption: Assumes createSceneNodes() and updateLevel() abstract methods created in Level Class
 */
public class Level2 extends LevelController {
	private Obstacle myObstacle;
	private List<Obstacle> myBlockList = new ArrayList<Obstacle>();
	public static  int BLOCK_DIRECTION = 1;
	public static final int BLOCK_SPEED = 80;

	public Level2(){
		myObstacle = new Obstacle();
	}

	/**
	 * Creates Nodes needed in the Scene for Level 2
	 */
	@Override 
	public Group createSceneNodes() {
		addCommonNodes();
		addLevel("LEVEL 2");
		addInstructionToWin("Reach Iron Throne To Win");
		myBlockList = myObstacle.createBlockNode(root);
		return root;
	}

	/**
	 * Updates the Step function needed for Level 2
	 */

	@Override
	public boolean updateLevel() {
		stepHelper();
		updateBlockPosition();
		checkPlayerBlockCollision();
		if(checkPlayerThroneCollision()){ wonGame();}
		if (startExitScreen) {
			exitButton.setOnAction(e -> Main.exitGame());
		}
		return false;
	}

	private boolean checkPlayerBlockCollision() {
		for (Obstacle tempObstacle : myBlockList) {
			if (myPlayer.getImageView().getBoundsInParent().intersects(tempObstacle.getImageView().getBoundsInParent())) {
				myPlayer.getImageView().setVisible(false);
				mySnowBall.getImageView().setVisible(false);
				myPlayer.setDead(true);
				myScoreBoard.updateLives();
				displayScoreBoard(true);
				return true;
			}
		}
		return false;
	}

	private void updateBlockPosition() {
		for (int i=0;i< myBlockList.size();i++) {
			myBlockList.get(i).getImageView().setY(
					myBlockList.get(i).getImageView().getY() - i*BLOCK_DIRECTION
					* Math.random()* BLOCK_SPEED * Game.SECOND_DELAY);
			
			if (myBlockList.get(i).getImageView().getY() <= 0) {
				BLOCK_DIRECTION *= -1;
			}

			if (myBlockList.get(i).getImageView().getY() >= Game.HEIGHT) {
				BLOCK_DIRECTION *= -1;
			}
		}
	}

	private void wonGame() {
		myPlayer.setWonGame(true);
		updateSplashScreen("YOU WON THE GAME");
		displayScoreBoard(true);
		addExitButton();
		startExitScreen= true;
	}
	
}

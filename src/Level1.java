import javafx.scene.Group;

/**
 * Separates functions needed in Level 1 in the game
 * Dependencies: LevelController Super Class 
 * Assumption: Assumes createSceneNodes() and updateLevel() abstract methods created in Level Class
 *@author Pratiksha Sharma
 */

public class Level1 extends LevelController {
	/**
	 * Creates nodes needed in the Scene for Level 1
	 * @return root-  root for the Scene for Level1
	 */

	@Override
	public Group createSceneNodes() {
		addCommonNodes();
		addLevel("LEVEL 1");
		addInstructionToWin("Kill 8 White Walkers and Reach the Iron Throne to Win");
		return root;
	}

	/**
	 * Updates the Step function needed for Level 1
	 * @return boolean whether to update level or not- returns true if level1 completed
	 */
	@Override
	public boolean updateLevel() {
		stepHelper();
		if ( (myScoreBoard.getDeadWhiteWalkerCount() < 8 && (System.currentTimeMillis() % 1950 ==0))) {
			addEnemiesTwice = false;
			addEnemies();
		}
		if ((myScoreBoard.getDeadWhiteWalkerCount() >= 8)
				&& checkPlayerThroneCollision()) {
			playMusic("GOT.mp3");
			return true;
		}
		return false;
	}
}

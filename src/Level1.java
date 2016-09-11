import javafx.scene.Group;

public class Level1 extends Level {

	/**
	 * Creates Nodes needed in the Scene for Level 1
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
	 */
	@Override
	public boolean updateLevel() {
		stepHelper();
		if ((myPlayer.getImageView().getX() >= Game.WIDTH / 2)
				&& (myScoreBoard.getDeadWhiteWalkerCount() < 8)
				&& (addEnemiesTwice)) {
			addEnemiesTwice = false;
			addEnemies();
		}
		if ((myScoreBoard.getDeadWhiteWalkerCount() >= 7)
				&& checkPlayerThroneCollision()) {
			playMusic("GOT.mp3");
			return true;
		}
		return false;
	}
}

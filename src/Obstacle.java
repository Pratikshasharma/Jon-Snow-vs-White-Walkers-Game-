import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Obstacle Class for creating Blocks in Level 2 
 * Dependencies: Block.gif, 
 * @author Pratiksha Sharma
 * 
 */
public class Obstacle {
	private ImageView myBlock;
	private Obstacle obstacle;
	private List<Obstacle> myBlocksList = new ArrayList<Obstacle>();

	public Obstacle() {
		myBlock = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("Block.gif")));
	}

	/**
	 * Creates and returns blocks in Level2
	 * @param myRoot
	 * @return List of the Blocks added to the root of the scene
	 */
	public List<Obstacle> createBlockNode(Group myRoot) {
		for (int i = 0; i < 5; i++) {
			obstacle = new Obstacle();
			obstacle.getImageView().setX(Game.WIDTH-100*i-i*50);
			obstacle.getImageView().setY(5);
			myRoot.getChildren().add(obstacle.getImageView());
			myBlocksList.add(obstacle);
		}
		return myBlocksList;
	}

	/**
	 * Returns the myBlock ImageView of the Obstacle
	 */
	public ImageView getImageView() {
		return myBlock;
	}

}


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Create a Player object that has attributes- dead, wonGame
 * Dependencies: Player.gif Image
 * @author Pratiksha Sharma
 *
 */
public class Player {
	private ImageView playerImageView;
	private boolean dead;
	private boolean wonGame;

	public Player(){
		playerImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("Player.gif")));	
		dead = false;
		wonGame = false;
	}

	/**
	 * Sets Player to the initial position in the Scene and 
	 */
	public void setPlayerPosition(){
		playerImageView.setVisible(true);
		playerImageView.setX(50);
		playerImageView.setY(Game.HEIGHT-250);	
	}

	public ImageView getImageView(){
		return playerImageView;
	}

	public boolean isDead(){
		return dead;
	}

	public boolean isWonGame(){
		return wonGame;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void setWonGame(boolean wonGame) {
		this.wonGame = wonGame;
	}
}

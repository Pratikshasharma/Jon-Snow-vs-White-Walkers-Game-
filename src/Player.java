
import javafx.scene.image.ImageView;

/**
 * Create a Player object that has attributes- dead, wonGame
 * Dependencies: Player.gif Image
 * @author Pratiksha Sharma
 *
 */
public class Player extends Sprite {
	private ImageView playerImageView;
	private boolean dead;
	private boolean wonGame;
	private static final String PLAYER_IMAGE_FILE = "Player.gif";

	public Player(){
		super(PLAYER_IMAGE_FILE);
		this.playerImageView = 	super.getMySprite();
		this.dead = false;
		this.wonGame = false;
	}

	/**
	 * Sets Player to the initial position in the Scene and 
	 */
	public void setPlayerPosition(double width, double height){	
		super.setPosition(width,height);	
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

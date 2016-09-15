
import javafx.scene.image.ImageView;

/**
 * Creates an enemy object that can be create multiple times in Level class
 * Dependencies: Enemy.gif
 * @author Pratiksha Sharma
 */

public class Enemy extends Sprite{
	private ImageView enemy;
	private boolean dead;
	private static final String ENEMY_IMAGE_FILE = "Enemy.gif";
	
	public Enemy(){
		super(ENEMY_IMAGE_FILE);
		this.enemy = super.getMySprite();
		this.dead = false;
	}
	
	public ImageView getImageView(){
		return enemy;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public void setDead(boolean dead){
		 this.dead = dead;
	}
	
	public void spwanEnemy(double xPosition, double yPosition){
		super.setPosition(xPosition, yPosition);
}
}

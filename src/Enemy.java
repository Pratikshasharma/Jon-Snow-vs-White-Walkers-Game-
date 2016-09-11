
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Creates an enemy object that can be create multiple times in Level class
 * Dependencies: Enemy.gif
 * @author Pratiksha Sharma
 */

public class Enemy {
	private ImageView enemy;
	private boolean dead;
	
	public Enemy(){
		this.enemy = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("Enemy.gif")));	
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
		enemy.setX(xPosition);
		enemy.setY(yPosition);
}
}

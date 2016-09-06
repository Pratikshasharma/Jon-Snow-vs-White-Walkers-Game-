
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {
	public ImageView enemy;
	boolean dead;
	
	public Enemy(){
		enemy = new ImageView(new Image(Enemy.class.getResourceAsStream("enemy.gif")));	
		dead = false;
	}
	
	public ImageView getImageView(){
		return enemy;
	}
	
	public boolean getState(){
		return dead;
	}
	
	public void spwanEnemy(double xPosition, double yPosition){
		enemy.setX(xPosition);
		enemy.setY(yPosition);
}
}

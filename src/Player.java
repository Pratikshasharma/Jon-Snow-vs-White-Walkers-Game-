
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Player {
	private ImageView playerImageView;
	 boolean dead;

	public Player(){
		playerImageView = new ImageView(new Image(Enemy.class.getResourceAsStream("player.gif")));	
		dead = false;
	}
	
	public void createPlayer(){
		playerImageView.setX(Main.WIDTH/2  - 3*playerImageView.getBoundsInLocal().getWidth());
		playerImageView.setY(Main.HEIGHT- 1.3* playerImageView.getBoundsInLocal().getHeight());	
	}

	public ImageView getImageView(){
		return playerImageView;
	}

}

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SnowBall {
	private ImageView snowBall;
	Player player = new Player();

	public SnowBall(){
		snowBall = new ImageView(new Image(Sprite.class.getResourceAsStream("ball.gif")));	
	}

	public void createSnowBall(){
		snowBall.setX(player.getImageView().getBoundsInLocal().getWidth()/2);
		snowBall.setY(1.6*player.getImageView().getBoundsInLocal().getHeight());
	}

	public ImageView getImageView(){
		return snowBall;
	}




}


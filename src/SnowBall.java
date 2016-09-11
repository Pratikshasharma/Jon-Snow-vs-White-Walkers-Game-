import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Create a SnowBall object that can be used by the player
 * Dependencies: Ball.gif, Player Class
 * @author Pratiksha Sharma
 *
 */
public class SnowBall {
	private ImageView snowBall;
	Player player = new Player();

	public SnowBall(){
		snowBall = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("Ball.gif")));	
	}

	/**
	 * Sets position of Snowball respective of the player
	 * @param width
	 * @param height
	 */
	public void setSnowBallPosition(double width, double height){
		snowBall.setX(width + 0.4*player.getImageView().getBoundsInLocal().getWidth());
		snowBall.setY(height + 0.5*player.getImageView().getBoundsInLocal().getHeight());
		snowBall.setVisible(true);
	}

	public ImageView getImageView(){
		return snowBall;
	}
	
	/**
	 * moves the ball when the player shoots
	 */
	public void moveBall(){
		snowBall.setX(snowBall.getX() +  LevelController.BALL_SPEED * Game.SECOND_DELAY);
	}

}


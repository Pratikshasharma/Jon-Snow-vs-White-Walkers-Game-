import javafx.scene.image.ImageView;

/**
 * Create a SnowBall object that can be used by the player
 * Dependencies: Ball.gif, Player Class
 * @author Pratiksha Sharma
 *
 */
public class SnowBall extends Sprite {
	private ImageView snowBall;
	Player player = new Player();
	private static final String BALL_IMAGE_FILE = "Ball.gif";

	public SnowBall(){
		super(BALL_IMAGE_FILE);
		this.snowBall = super.getMySprite();
	}

	/**
	 * Sets position of Snowball respective of the player
	 * @param width
	 * @param height
	 */
	public void setSnowBallPosition(double width, double height){
		//super.setPosition(width + 0.4*player.getImageView().getBoundsInLocal().getWidth(), height + 0.5*player.getImageView().getBoundsInLocal().getHeight());
	super.setPosition(width, height);
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


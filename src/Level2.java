
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Level2 extends Levels{

	private int BLOCK_DIRECTION = 1;
	private Rectangle myLeftBlock;
	private Rectangle myRightBlock;
	private int BLOCK_SPEED = 30;
	
	// Create a New Player??
	
	public void createBlockNode(){
		// Starts from the Bottom 
		myLeftBlock = new Rectangle( Main.WIDTH * 0.5, Main.HEIGHT - 50, 20, 20);
		myLeftBlock.setFill(Color.RED);
		// Starts from the Top
		myRightBlock = new Rectangle( Main.WIDTH * 0.6,10, 20, 20);
		myRightBlock.setFill(Color.RED);

		
		root.getChildren().add(myLeftBlock);
		root.getChildren().add(myRightBlock);
	}
	
	public void updateBlocks(){
		//step(time)
		
		myLeftBlock.setY(myLeftBlock.getY() - BLOCK_DIRECTION * 2 * BLOCK_SPEED * time);
		myRightBlock.setY(myLeftBlock.getY() + BLOCK_DIRECTION * 2 * BLOCK_SPEED * time);
		
		if (myLeftBlock.getY() <=0 ){
        	 BLOCK_DIRECTION *=-1;
        }
        
        if (myLeftBlock.getY() >= Main.HEIGHT){
        	 BLOCK_DIRECTION *=1;
        }
        
        if (myRightBlock.getY() <=0 ){
       	 BLOCK_DIRECTION *=1;
       }
       
       if (myRightBlock.getY() >= Main.HEIGHT){
       	 BLOCK_DIRECTION *=-1;
       }
  
	}
	
	
	public void checkCollision(){
		if (myPlayer.getImageView().getBoundsInParent().intersects(myLeftBlock.getBoundsInParent())){
			myPlayer.getImageView().setVisible(false);
			//End the game	
			System.out.println(" YOU LOST");
		}
		
		// Check Game Won
		if (myPlayer.getImageView().getBoundsInParent().intersects(myIronThrone.getBoundsInParent())){
			// Game Won
			System.out.println(" YOU WON");
		}
		
		
        
	}

}

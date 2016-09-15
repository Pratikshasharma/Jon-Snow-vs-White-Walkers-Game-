
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
	private ImageView mySprite;
	
public Sprite(String myFileName){
	 this.mySprite=	new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myFileName)));	
	}

protected ImageView getMySprite(){
	return mySprite;
}
protected void setPosition(double width, double height){
	mySprite.setX(width);
	mySprite.setY(height);
	mySprite.setVisible(true);
}
}

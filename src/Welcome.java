
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Create the initial SplashScreen as the program runs
 * Dependencies: Welcome.gif
 * @author Pratiksha Sharma
 *
 */
public class Welcome{	
	private ImageView initialImage;

	/**
	 * creates the root for the Welcome Scene Root
	 * @param startButton button on Welcome Screen
	 * @param exitButton Button on Welcome Screen
	 * @return root		root of the Scene on Stage
	 */
	public Group createRoot(Button startButton, Button exitButton){
		Group root = new Group();
		Image welcome = new Image(getClass().getClassLoader().getResourceAsStream("Welcome.gif"));
		initialImage = new ImageView(welcome);
		
		root.getChildren().add(initialImage);
		root.getChildren().add(startButton);
		root.getChildren().add(exitButton);	
		return root;	
	}

}




import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Welcome{	
	private ImageView initialImage;

	Group createRoot(Button startButton, Button exitButton){
		Group root = new Group();
		Image welcome = new Image(getClass().getClassLoader().getResourceAsStream("Welcome.gif"));
		initialImage = new ImageView(welcome);
		root.getChildren().add(initialImage);
		root.getChildren().add(startButton);
		root.getChildren().add(exitButton);
		
		// Change button settings

		return root;	
	}
	
}



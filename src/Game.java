import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


class Game {
    public static final int KEY_INPUT_SPEED = 5;
    private static final int BOUNCER_SPEED = 30;
    
	private Scene myScene;
    private ImageView myView;
    
	public static final String TITLE = "Jon Snow vs The White Walkers";

	 /**
     * Returns name of the game.
     */
    public String getTitle () {
        return TITLE;
    }
    public Scene init (int width, int height) {
        // create a scene graph to organize the scene
        Group root = new Group();
        // create a place to see the shapes
        myScene = new Scene(root, width, height, Color.WHITE);
        // make some shapes and set their properties
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("Welcome.gif"));
        myView = new ImageView(image);
        root.getChildren().add(myView);
        return myScene;
    } 
    /**
     * Change properties of shapes to animate them
     * 
     * Note, there are more sophisticated ways to animate shapes,
     * but these simple ways work too.
     */
    
    public void step (double elapsedTime) {
        // update attributes
        myView.setX(myView.getX() + BOUNCER_SPEED * elapsedTime);  
    }
    
}

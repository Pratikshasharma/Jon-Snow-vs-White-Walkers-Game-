
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ScoreBoard {
	private int totalScore;
	private int deadWhiteWalkerCount;
	private int remainingLives;
	private Text scoreDisplayText;
	private Text whiteWalkersDisplayText;
	private Text livesText;

	public ScoreBoard(){
		this.totalScore = 0;
		this.deadWhiteWalkerCount = 0;
		this.remainingLives = 5;
		this.scoreDisplayText = createTextObject("SCORE : ",0.8*Game.WIDTH,20,15,Color.DARKGREEN);
		this.whiteWalkersDisplayText = createTextObject("Number of White Walkers Killed: ",40,60,15,Color.RED);
		this.livesText = createTextObject("Remaining Lives: ",0.8*Game.WIDTH,60,15,Color.GREEN);
	}

	public Text getScoreText(){
		scoreDisplayText.setText("Score : " + totalScore);
		return scoreDisplayText;
	}

	/**
	 * 
	 * @return text for count of Dead White Walkers
	 */
	public Text getDeadWhiteWalkersText(){
		whiteWalkersDisplayText.setText("Number of Dead White Walkers : " + deadWhiteWalkerCount);
		return whiteWalkersDisplayText;
	}

	/**
	 * returns the Text for number of Lives left
	 */
	public Text getLivesText(){
		livesText.setText("Remaining Lives: " + remainingLives);
		return livesText;
	}

	/**
	 * Updates Score on the ScoreBoard
	 */
	public void updateScoreBoard(){
		deadWhiteWalkerCount += 1;
		totalScore +=10;
	}

	/**
	 * Updates remaining lives of the player
	 */
	public void updateLives(){
		remainingLives = remainingLives-1;
	}

	public int getScore(){
		return totalScore;
	}
	public int getLives(){
		return remainingLives;
	}

	public int getDeadWhiteWalkerCount(){
		return deadWhiteWalkerCount;
	}

	/**
	 * Creates text Object with the parameters passed
	 */

	public Text createTextObject(String myString,double xPosition, double yPosition,int fontSize, Color myColor){
		Text myText = new Text(xPosition,yPosition,myString);
		myText.setFont(Font.font("Comic Sans", FontWeight.BOLD,fontSize));
		myText.setFill(myColor);

		return myText;

	}

}

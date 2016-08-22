package model_logic;

/**
 * Class to hold game settings
 * 
 * @author alistaircooper
 *
 */
public class Settings {

	public static final double SCENE_WIDTH = 800;
	public static final double SCENE_HEIGHT = 800;

	// number of life forms
	public static final int NUMBER_OF_RED = 15;
	public static final int NUMBER_OF_BLUE = 15;
	public static final int NUMBER_OF_GREEN = 15;

	// size of life forms
	public static final double LIFEFORM_RADIUS = 6.0;
	
	// life form fertility waiting period (frames)
	public static final int FERTILITY_WAITING_PERIOD = 60;

	// start placement of life forms
	public static final int RED_X_START = 50;
	public static final int RED_Y_START = 550;

	public static final int BLUE_X_START = 750;
	public static final int BLUE_Y_START = 550;

	public static final int GREEN_X_START = 400;
	public static final int GREEN_Y_START = 50;
	
	// maximum velocity of life forms
	public static final double LIFEFORM_MAXV = 3.1;

	// size of death forms
	public static final int DEATHFORM_HEIGHT = 50;
	public static final int DEATHFORM_WIDTH = 50;
	
	// speed of death forms (enemies)            
	public static final double DEATHFORM_SPEED = 2.3;

	// size of wall block elements
	public static final double WALL_BLOCK_HEIGHT = 80.0;
	public static final double WALL_BLOCK_WIDTH = 80.0;
	
	// velocity factor life forms are increased by when hitting wall blocks
	public static final double WALL_VELOCITY_BOOST = 1.4;

	// velocity of The Protector (player)
	public static final double PLAYER_SPEED = 3.0;
	

}

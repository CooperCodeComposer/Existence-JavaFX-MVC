package model_logic;

/**
 * Class to hold game score statistics
 * 
 * @author alistaircooper
 *
 */
public class Statistics {
	
	// frame count
	private static double frameCount = 0;
	
	// frames per "year" in game
	private final static double FRAMES_PER_YEAR = 60; 
	
	// number of years ecosystem has survived
	public static int yearsSurvived = 0;
	
	
	/**
	 * Method to updateYearsSurvived
	 */
	public static void updateYearsSurvived() {
		// increment frame count
		frameCount ++;
		
		yearsSurvived = (int)Math.floor(frameCount / FRAMES_PER_YEAR);
	}

}

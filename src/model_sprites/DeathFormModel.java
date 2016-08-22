package model_sprites;

import view_sprites.DeathFormModelObserver;
import java.util.ArrayList;
import model_logic.Settings;

/**
 * Class to represent DeathFormModel
 * 
 * @author alistaircooper
 *
 */
public class DeathFormModel extends Sprite implements DeathFormModelInterface {

	/** array list for model observers */
	ArrayList<DeathFormModelObserver> deathFormModelObservers = new ArrayList<DeathFormModelObserver>();

	/** width of death form */
	private int width = Settings.DEATHFORM_HEIGHT; // width of the sprite in px

	/** height of death form */
	private int height = Settings.DEATHFORM_WIDTH; // height of the sprite in px

	/** constructor */
	public DeathFormModel() {

		// set velocity
		vX = Settings.DEATHFORM_SPEED;
		vY = Settings.DEATHFORM_SPEED;
	}

	/**
	 * Method to update the position
	 */
	@Override
	public void update() {

		// update current x and y within life form model
		currentX = currentX + vX;
		currentY = currentY + vY;

		// update the view
		DeathFormModelObserver observer = (DeathFormModelObserver) deathFormModelObservers.get(0);
		observer.updateViewPosition(vX, vY);
	}

	/**
	 * Returns height of image
	 * 
	 * @return int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns width of image
	 * 
	 * @return int
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * When encountering a Wall determine if they
	 * collided.
	 * 
	 * @param other
	 * 
	 * @return boolean 
	 * true if collide with a Wall otherwise false.
	 * 
	 */
	@Override
	public boolean collide(Sprite other) {
		if (other instanceof WallModel) {
			
			WallModel wall = (WallModel) other;
			
			// rectangle to rectangle collision check
			
			double r1Left = this.currentX;
			double r1Right = this.currentX + this.getWidth();
			double r1Top = this.currentY;
			double r1Bottom = this.currentY + this.getHeight();
			
			double r2Left = wall.currentX;
			double r2Right = wall.currentX + wall.getWidth();
			double r2Top = wall.currentY;
			double r2Bottom = wall.currentY + wall.getHeight();
			
		    if ( (r1Right < r2Left) || (r1Top > r2Bottom) || (r1Left > r2Right) || (r1Bottom < r2Top)) {
		    	return false;
		    } else {
		    	return true;
		    }
		}
		
		if (other instanceof LifeFormModel) {
			
			LifeFormModel lifeForm = (LifeFormModel) other;
			
			// life form is circle by collision area is treated as rectangle
			// life form (0, 0) is circle center
			
			double r1Left = this.currentX;
			double r1Right = this.currentX + this.getWidth();
			double r1Top = this.currentY;
			double r1Bottom = this.currentY + this.getHeight();
			
			double r2Left = lifeForm.currentX - lifeForm.getRadius();
			double r2Right = lifeForm.currentX + lifeForm.getRadius();
			double r2Top = lifeForm.currentY - lifeForm.getRadius();
			double r2Bottom = lifeForm.currentY + lifeForm.getRadius();
			
		    if ( (r1Right < r2Left) || (r1Top > r2Bottom) || (r1Left > r2Right) || (r1Bottom < r2Top)) {
		    	return false;
		    } else {
		    	return true;
		    }
		}
		
		if (other instanceof TheProtectorModel) {
			
			TheProtectorModel theProtector = (TheProtectorModel) other;
			
			// rectangle to rectangle collision check
			
			double r1Left = this.currentX;
			double r1Right = this.currentX + this.getWidth();
			double r1Top = this.currentY;
			double r1Bottom = this.currentY + this.getHeight();
			
			double r2Left = theProtector.currentX;
			double r2Right = theProtector.currentX + theProtector.getWidth();
			double r2Top = theProtector.currentY;
			double r2Bottom = theProtector.currentY + theProtector.getHeight();
			
		    if ( (r1Right < r2Left) || (r1Top > r2Bottom) || (r1Left > r2Right) || (r1Bottom < r2Top)) {
		    	return false;
		    } else {
		    	return true;
		    }
		}
		
		return false;
	}

	/**
	 * Method to register observer
	 */
	public void registerObserver(DeathFormModelObserver o) {
		deathFormModelObservers.add(o);
	}

	/**
	 * Method to remove observer
	 */
	public void removeObserver(DeathFormModelObserver o) {
		int i = deathFormModelObservers.indexOf(o);
		if (i >= 0) {
			deathFormModelObservers.remove(i);
		}
	}

}

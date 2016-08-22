package model_sprites;

import java.util.ArrayList;

import model_logic.Settings;
import view_sprites.TheProtectorModelObserver;

/**
 * Class to represent TheProtector (player) model
 * 
 * 
 * @author alistaircooper
 *
 */
public class TheProtectorModel extends Sprite implements TheProtectorModelInterface {

	/** array list for model observers */
	public ArrayList<TheProtectorModelObserver> theProtectorModelObservers = new ArrayList<TheProtectorModelObserver>();

	/** speed (pixels per keystroke input) */
	public final double SPEED = Settings.PLAYER_SPEED;

	/** can player move (false when player would intersect wall block */
	public boolean canMove = true;

	/** width */
	public int width = 40;

	/** height */
	public int height = 40;

	/** constructor */
	public TheProtectorModel(double startX, double startY) {
		this.currentX = startX;
		this.currentY = startY;
	}

	/**
	 * Method to update the position
	 * 
	 */
	@Override
	public void update() {

		if (!canMove) {
			return; // don't move if they would be colliding with wall block
		} else {
			// update current x and y within life form object
			currentX = currentX + vX;
			currentY = currentY + vY;

			// contain within boundaries
			// at left boundary
			if (currentX < 0) {
				currentX = 0;
				vX = 0;
			}
			// at right boundary
			if (currentX > Settings.SCENE_WIDTH - width) {
				currentX = Settings.SCENE_WIDTH - width;
				vX = 0;
			}
			// at top boundary
			if (currentY < 0) {
				currentY = 0;
				vY = 0;
			}
			// at bottom boundary
			if (currentY > Settings.SCENE_WIDTH - height) {
				currentY = Settings.SCENE_WIDTH - height;
				vY = 0;
			}

			// update the view
			TheProtectorModelObserver observer = (TheProtectorModelObserver) theProtectorModelObservers.get(0);
			observer.updateViewPosition(vX, vY);

		}
	}

	/**
	 * Method to notify the Protector view to process keyboard input
	 */
	public void processInput() {

		// update the view
		TheProtectorModelObserver observer = (TheProtectorModelObserver) theProtectorModelObservers.get(0);
		observer.processInputInController();
	}

	/**
	 * Method to set vX
	 */
	public void setvX(double vX) {
		this.vX = vX;
	}

	/**
	 * Method to set vY
	 */
	public void setvY(double vY) {
		this.vY = vY;
	}

	/**
	 * Returns true if The Protector is alive
	 * 
	 * @return boolean
	 */
	public boolean getIsAlive() {
		return isAlive;
	}

	/**
	 * Set value for isAlive
	 */
	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
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

	@Override
	public boolean collide(Sprite other) {
		if (other instanceof LifeFormModel) {
			LifeFormModel lifeForm = (LifeFormModel) other;
			
			double pCenterX = this.currentX + (this.width / 2);
			double pCenterY = this.currentY + (this.height / 2);
			
			// note life forms are circles with 0, 0 as center
			// walls are rectangles with 0, 0 as top left
			double circleDistanceX = Math.abs(other.currentX - pCenterX);
			double circleDistanceY = Math.abs(other.currentY - pCenterY);
			

		    if (circleDistanceX > ( (this.width / 2) + lifeForm.getRadius() ) ) { return false; }
		    if (circleDistanceY > ( (this.height / 2) + lifeForm.getRadius() ) ) { return false; }

		    if (circleDistanceX <= (this.width / 2)) { return true; } 
		    if (circleDistanceY <= (this.width / 2)) { return true; }

		    double cornerDistance_sq = Math.pow((circleDistanceX - this.width / 2), 2) + 
		    		Math.pow((circleDistanceY - this.height / 2), 2);
		    
		    return (cornerDistance_sq <= Math.pow(lifeForm.getRadius(), 2));
		}
		if (other instanceof WallModel) {
			WallModel wall = (WallModel) other;
			
			/*
			 * rectangle to rectangle collision check
			 * the velocity is added to the positions to check for potential 
			 * FUTURE collision to disable the canMove
			 */
			
			double r1Left = this.currentX + vX;
			double r1Right = this.currentX + this.width + vX;
			double r1Top = this.currentY + vY;
			double r1Bottom = this.currentY + this.height + vY;
			
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
		return false;
	}

	/**
	 * When encountering a LifeForm to determine if they collided.
	 * 
	 * @param LifeFormModel
	 *            lifeForm
	 * @return boolean true if collide with lifeForm, otherwise false.
	 */
	public boolean collide(LifeFormModel other) {

		// return
		// node.getBoundsInParent().intersects(other.node.getBoundsInParent());
		return false;
	}

	/**
	 * When encountering a Wall to determine if they collided.
	 * 
	 * @param other
	 * 
	 * @return boolean true if collide with wall, otherwise false.
	 */
	public boolean collide(WallModel other) {

		// boolean wouldCollideWithWall;
		//
		// if (other.node.getBoundsInParent().intersects(currentX + vX, currentY
		// + vY, node.getBoundsInParent().getWidth(),
		// node.getBoundsInParent().getHeight())) {
		// wouldCollideWithWall = true;
		// } else {
		// wouldCollideWithWall = false;
		// }
		//
		// return wouldCollideWithWall;
		return false;
	}

	/**
	 * Method to register observer
	 */
	public void registerObserver(TheProtectorModelObserver o) {
		theProtectorModelObservers.add(o);
	}

	/**
	 * Method to remove observer
	 */
	public void removeObserver(TheProtectorModelObserver o) {
		int i = theProtectorModelObservers.indexOf(o);
		if (i >= 0) {
			theProtectorModelObservers.remove(i);
		}
	}

}

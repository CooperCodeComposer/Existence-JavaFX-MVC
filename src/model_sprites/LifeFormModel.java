package model_sprites;

import java.util.ArrayList;
import java.util.Random;

import model_logic.Settings;
import view_sprites.LifeFormModelObserver;

/**
 * Super class for life forms LifeFormModel hold logic for life forms
 * 
 *
 * @author alistaircooper
 */
public class LifeFormModel extends Sprite implements LifeFormModelInterface {

	/** array list for model observers */
	ArrayList<LifeFormModelObserver> lifeFormModelObservers = new ArrayList<LifeFormModelObserver>();

	/** radius of lifeform */
	protected double radius = Settings.LIFEFORM_RADIUS;

	/** is the lifeform fertile */
	protected Boolean fertile = false; // false when born

	/** time since mating */
	protected int timeSinceMating = 0; // zero when born

	/** wating period before fertile again (frames) */
	private final int fertilityWaitingPeriod = Settings.FERTILITY_WAITING_PERIOD;

	/** constructor for lifeform */
	public LifeFormModel() {

		setVelocity(); // sets random velocity to life form

	}

	/**
	 * Method to update the position of life form
	 */
	@Override
	public void update() {

		// update current x and y within life form model
		currentX = currentX + vX;
		currentY = currentY + vY;

		// update the view
		LifeFormModelObserver observer = (LifeFormModelObserver) lifeFormModelObservers.get(0);
		observer.updateViewPosition(vX, vY);
	}

	/**
	 * Did this sprite collide into the other sprite?
	 *
	 * @param other
	 *            - The other sprite.
	 * @return
	 */
	@Override
	public boolean collide(Sprite other) {
		if (other instanceof LifeFormModel) {
			return collide((LifeFormModel) other);
		} 
//		if (other instanceof WallModel) {
//			return collideWall((WallModel) other);
//		} 
		return false;		
	}

	/**
	 * When encountering another LifeForm to determine if they collided.
	 * 
	 * @param other
	 *            Another lifeForm
	 * @return boolean true if this lifeForm and other lifeForm have collided,
	 *         otherwise false.
	 */
	public boolean collide(LifeFormModel other) {
		
		// determine it's size
        double dx = other.currentX - this.currentX;
        double dy = other.currentY - this.currentY;
        double distance = Math.sqrt( dx * dx + dy * dy );
        double minDist  = other.getRadius() + this.getRadius();
        
        return (distance < minDist);

	}
	
	/**
	 * When encountering a Wall determine if they collided.
	 * 
	 * @param other
	 * 
	 * @return boolean 
	 * true if this lifeForm and a wall have collided, otherwise false.
	 * 
	 */
	public boolean collideWall(WallModel other) {
		
		double wallCenterX = other.currentX + (other.getWidth() / 2);
		double wallCenterY = other.currentY + (other.getHeight() / 2);
		
		// note life forms are circles with 0, 0 as center
		// walls are rectangles with 0, 0 as top left
		double circleDistanceX = this.currentX - wallCenterX;
		double circleDistanceY = this.currentY - wallCenterY;
		

	    if (circleDistanceX > ( (other.getWidth() / 2) + this.getRadius() ) ) { return false; }
	    if (circleDistanceY > ( (other.getHeight() / 2) + this.getRadius() ) ) { return false; }

	    if (circleDistanceX <= (other.getWidth() / 2)) { return true; } 
	    if (circleDistanceY <= (other.getHeight() / 2)) { return true; }

	    double cornerDistance_sq = Math.pow((circleDistanceX - other.getWidth() / 2), 2) + 
	    		Math.pow((circleDistanceY - other.getHeight() / 2), 2);

	    return (cornerDistance_sq <= Math.pow(this.getRadius(), 2)); 
		
	}

	/**
	 * Method to randomly set lifeform's velocity
	 * 
	 */
	public void setVelocity() {
		Random rnd = new Random();

		// random 1 + (.0 to 1) * random (1 or -1) direction
		vX = (1 + rnd.nextDouble()) * (rnd.nextBoolean() ? 1 : -1);
		vY = (1 + rnd.nextDouble()) * (rnd.nextBoolean() ? 1 : -1);

	}
	
	/**
	 * Method to set time since mating
	 * 
	 */
	public void setTimeSinceMating(int timeSinceMating) {
		this.timeSinceMating = timeSinceMating;

	}

	/**
	 * Returns radius of life form
	 * 
	 * @return double
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Returns whether life form is fertile
	 * 
	 * @return boolean
	 */
	public boolean getFertile() {
		return fertile;
	}

	/**
	 * Advance time since mating increments time since last mating and tests if
	 * life form is currently fertile
	 * 
	 */
	public void advanceTimeSinceMating() {
		timeSinceMating += 1;

		if (timeSinceMating > fertilityWaitingPeriod) {
			fertile = true;
		} else {
			fertile = false;
		}
	}

	/**
	 * Method to trigger implosion in the view layer and set isAlive to false
	 * 
	 */
	public void implode() {
		vX = vY = 0; // stops moving

		// implode the view
		LifeFormModelObserver observer = (LifeFormModelObserver) lifeFormModelObservers.get(0);
		observer.implode();
	}

	/**
	 * Method to register observer
	 */
	public void registerObserver(LifeFormModelObserver o) {
		lifeFormModelObservers.add(o);
	}

	/**
	 * Method to remove observer
	 */
	public void removeObserver(LifeFormModelObserver o) {
		int i = lifeFormModelObservers.indexOf(o);
		if (i >= 0) {
			lifeFormModelObservers.remove(i);
		}
	}
}

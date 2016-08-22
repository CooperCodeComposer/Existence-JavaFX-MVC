package model_sprites;


import model_logic.Settings;

/**
 * Class for walls
 * 
 * @author alistaircooper
 *
 */
public class WallModel extends Sprite {
	
	/** height of wall blocks */
	private double height = Settings.WALL_BLOCK_HEIGHT;
	
	/** width of wall blocks */
	private double width = Settings.WALL_BLOCK_WIDTH;

	/** constructor */
	public WallModel() {

	}

	@Override
	public void update() {
		// do nothing. Wall blocks don't move

	}
	
	/**
	 * When encountering a TheProtector, DeathForm or a LifeForm to determine if they collided.
	 * 
	 * @param other 
	 *            
	 * @return boolean true if collide with TheProtector, Wall or LifeForm otherwise false.
	 */
	@Override
	public boolean collide(Sprite other) {
		if (other instanceof LifeFormModel) {
			return collide((LifeFormModel) other);
		}
		
		return false;
	}
	
	/**
	 * When encountering a LifeForm determine if they collided.
	 * 
	 * @param other
	 * 
	 * @return boolean 
	 * 
	 */
	public boolean collide(LifeFormModel other) {
		
		double wallCenterX = this.currentX + (this.getWidth() / 2);
		double wallCenterY = this.currentY + (this.getHeight() / 2);
		
		// note life forms are circles with 0, 0 as center
		// walls are rectangles with 0, 0 as top left
		double circleDistanceX = Math.abs(other.currentX - wallCenterX);
		double circleDistanceY = Math.abs(other.currentY - wallCenterY);
		

	    if (circleDistanceX > ( (this.getWidth() / 2) + other.getRadius() ) ) { return false; }
	    if (circleDistanceY > ( (this.getHeight() / 2) + other.getRadius() ) ) { return false; }

	    if (circleDistanceX <= (this.getWidth() / 2)) { return true; } 
	    if (circleDistanceY <= (this.getHeight() / 2)) { return true; }

	    double cornerDistance_sq = Math.pow((circleDistanceX - this.getWidth() / 2), 2) + 
	    		Math.pow((circleDistanceY - this.getHeight() / 2), 2);
	    
	    return (cornerDistance_sq <= Math.pow(other.getRadius(), 2)); 
		
	}
	
	/**
	 * Returns height 
	 * 
	 * @return double 
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * Returns width 
	 * 
	 * @return double 
	 */
	public double getWidth() {
		return width;
	}


}

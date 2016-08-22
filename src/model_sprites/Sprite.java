package model_sprites;

 
/**
 * The Sprite class represents a image or node to be displayed.
 * The game loop will call the update()
 * and collide() method at every interval of a key frame.
 *  
 * References: JavaFX 2 Game Tutorial by Carl Dea 
 * https://carlfx.wordpress.com/2012/03/29/javafx-2-gametutorial-part-1/
 * 
 */
public abstract class Sprite {
 
    /** velocity vector x direction */
    public double vX = 0;
 
    /** velocity vector y direction */
    public double vY = 0;
    
    /** current x value within scene */
    public double currentX;
    
    /** current y value within scene */
    public double currentY;
 
    /** alive? */
    public boolean isAlive = true;
 
    /**
     * Updates this sprite object's velocity, or animations.
     */
    public abstract void update();
    
    /**
     * Set is alive 
     * 
     */
    public void setIsAlive(boolean isAlive) {
    	this.isAlive = isAlive;
    }
 
    /**
     * Did this sprite collide into the other sprite?
     *
     * @param other - The other sprite.
     * @return
     */
    public boolean collide(Sprite other) {
        return false;
    }
}

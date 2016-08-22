package model_sprites;

import view_sprites.LifeFormModelObserver;

/**
 * Interface to set guidelines for LifeForm Model
 * 
 * @author alistaircooper
 *
 */
public interface LifeFormModelInterface {

	void setVelocity();
	
	void setTimeSinceMating(int timeSinceMating);

	void update();

	boolean collide(Sprite other);

	boolean collide(LifeFormModel other);
	
	double getRadius();

	boolean getFertile();
	
	void setIsAlive(boolean isAlive);

	void advanceTimeSinceMating();

	// code to register observers

	void registerObserver(LifeFormModelObserver o);

	void removeObserver(LifeFormModelObserver o);

}

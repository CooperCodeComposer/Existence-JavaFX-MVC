package model_sprites;

import view_sprites.TheProtectorModelObserver;

/**
 * Interface to set guidelines for TheProtectorModel
 * 
 * @author alistaircooper
 *
 */
public interface TheProtectorModelInterface {

	void update();
	
	void processInput() ;

	boolean getIsAlive();

	void setIsAlive(boolean isAlive);
	
	void setvX(double vX);
	
	void setvY(double vY);
	
	int getHeight();
	
	int getWidth();

	boolean collide(Sprite other);

	boolean collide(LifeFormModel other);

	boolean collide(WallModel other);

	// code to register observers

	void registerObserver(TheProtectorModelObserver o);

	void removeObserver(TheProtectorModelObserver o);

}

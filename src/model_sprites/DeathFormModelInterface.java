package model_sprites;

import view_sprites.DeathFormModelObserver;

/**
 * Interface to set guidelines for DeathFormModel
 * 
 * @author alistaircooper
 *
 */
public interface DeathFormModelInterface {
	
	int getWidth();
	
	int getHeight();
	
	void update();
	
	boolean collide(Sprite other);

	// code to register observers

	void registerObserver(DeathFormModelObserver o);

	void removeObserver(DeathFormModelObserver o);

}

package model_logic;

import model_sprites.Sprite;
import view.ExistenceModelObserver;

/**
 * Interface to set guidelines for Existence Model
 * @author alistaircooper
 *
 */
public interface ExistenceModelInterface {
	
	void initialize();
	
	void generateRedLifeForms(int numSpheres, int startX, int startY);
	
	void generateBlueLifeForms(int numSpheres, int startX, int startY);
	
	void generateGreenLifeForms(int numSpheres, int startX, int startY);
	
	void generateDeathForm(double x, double y);
		
	void buildWall(double x, double y);
	
	void handleUpdate(Sprite sprite);
	
	boolean handleCollision(Sprite spriteA, Sprite spriteB);
	
	void cleanupSprites();
	
	void updateGameScore();
	
	void isGameOver();
	
	void createScoreLayer();
	
	String getWindowTitle();
	
	SpriteManager getSpriteManager();
	
	// code to register observers
	
	void registerObserver(ExistenceModelObserver o);
	
	void removeObserver(ExistenceModelObserver o);

}

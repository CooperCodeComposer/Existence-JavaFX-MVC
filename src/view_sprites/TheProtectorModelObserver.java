package view_sprites;


/**
 * Interface to declare methods used by the TheProtector
 * 
 * @author alistaircooper
 *
 */
public interface TheProtectorModelObserver {
	
	void updateViewPosition(double vX, double vY);
	
	void setupSpriteAnimation();
	
	void processInputInController();
	

}

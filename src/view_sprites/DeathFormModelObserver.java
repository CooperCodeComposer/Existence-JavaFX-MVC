package view_sprites;

/**
 * Interface to declare methods used by the DeathFormView
 * 
 * @author alistaircooper
 *
 */
public interface DeathFormModelObserver {
	
	void updateViewPosition(double vX, double vY);
	
	void setupSpriteAnimation();

}

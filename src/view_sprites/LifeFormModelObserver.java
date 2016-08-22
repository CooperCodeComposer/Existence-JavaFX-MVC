package view_sprites;

/**
 * Interface to declare methods used by the LifeFormView subclasses
 * 
 * @author alistaircooper
 *
 */
public interface LifeFormModelObserver {
	
	void updateViewPosition(double vX, double vY);
	
	void implode();

}

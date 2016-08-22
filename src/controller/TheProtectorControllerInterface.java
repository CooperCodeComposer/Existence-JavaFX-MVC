package controller;

import view_sprites.TheProtectorView;

/**
 * Interface for TheProtector controller (The player)
 * defines methods for movement
 * 
 * @author alistaircooper
 *
 */
public interface TheProtectorControllerInterface {
	
	void processInput();
	
	TheProtectorView getTheProtectorView();

}

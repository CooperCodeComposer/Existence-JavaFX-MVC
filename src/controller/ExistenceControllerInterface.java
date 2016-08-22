package controller;

import view.ExistenceGameWorldView;

/**
 * Interface to set guidelines for ExistenceController
 * @author alistaircooper
 *
 */
public interface ExistenceControllerInterface {

	// if there was a view to control the game world (e.g. to restart / start the game) 
	// the code would go here
	
	ExistenceGameWorldView getExistenceGameWorldView();
}

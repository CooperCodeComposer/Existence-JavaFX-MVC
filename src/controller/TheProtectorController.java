package controller;

import model_logic.Settings;
import model_sprites.TheProtectorModelInterface;
import view_sprites.TheProtectorView;

/**
 * Class for TheProtectorController
 * contains reference to the TheProtectorModel
 * contains reference to the TheProtectorView
 * 
 * @author alistaircooper
 *
 */
public class TheProtectorController implements TheProtectorControllerInterface {
	
	/** reference to the model */
	TheProtectorModelInterface theProtectorModel;
	
	/** reference to the view */
	TheProtectorView theProtectorView;
	
	/**
	 * Constructor for TheProtectorController
	 * pass in TheProtectorModel
	 * 
	 * @param theProtectorModel
	 */
	public TheProtectorController(TheProtectorModelInterface theProtectorModel) {
		this.theProtectorModel = theProtectorModel;
		
		// create the view. Pass in controller and the model
		theProtectorView = new TheProtectorView(this, theProtectorModel);
		
		// set initial position
		theProtectorView.node.setTranslateX(Settings.SCENE_WIDTH /2);
		theProtectorView.node.setTranslateY(Settings.SCENE_HEIGHT /2);
			
	}
	
	/**
	 * Method to process input from keyboard
	 *
	 */
	public void processInput() {
		
		// vertical direction
		if (theProtectorView.input.isMoveUp()) {
			theProtectorModel.setvY(-Settings.PLAYER_SPEED);

		} else if (theProtectorView.input.isMoveDown()) {
			theProtectorModel.setvY(Settings.PLAYER_SPEED);

		} else {
			theProtectorModel.setvY(0);
		}

		// horizontal direction
		if (theProtectorView.input.isMoveLeft()) {
			theProtectorModel.setvX(-Settings.PLAYER_SPEED);

		} else if (theProtectorView.input.isMoveRight()) {
			theProtectorModel.setvX(Settings.PLAYER_SPEED);

		} else {
			theProtectorModel.setvX(0);
		}
	}
	
	/**
	 * Method to return the Protector view
	 * needed so that TheProtectorView can be added to the main 
	 * game world view in the driver and request focus
	 * 
	 */
	public TheProtectorView getTheProtectorView() {
		return theProtectorView;
	}

}

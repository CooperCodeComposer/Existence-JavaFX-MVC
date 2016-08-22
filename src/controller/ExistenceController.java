package controller;

import javafx.stage.Stage;
import model_logic.ExistenceModelInterface;
import view.ExistenceGameWorldView;

/**
 * Class for ExistenceController
 * contains reference to the ExistenceModel
 * contains reference to the ExistenceGameWorldView
 * 
 * @author alistaircooper
 *
 */
public class ExistenceController implements ExistenceControllerInterface {
	
	/** reference to the model */
	ExistenceModelInterface existenceModel;
	
	/** reference to the view */
	ExistenceGameWorldView existenceGameWorldView;
	
	/**
	 * Constructor for ExistenceController
	 * pass in model. Have to pass in stage from driver package to initialize view
	 * 
	 * @param existenceModel
	 */
	public ExistenceController(ExistenceModelInterface existenceModel, Stage primaryStage) {
		this.existenceModel = existenceModel;
		// create the main view
		existenceGameWorldView = new ExistenceGameWorldView(this, existenceModel);
		
		// initialize the view 
		existenceGameWorldView.initialize(primaryStage);
		
	}
	
	/**
	 * Method to return TheExistenceGameWorldView
	 * needed so that TheProtectorView can be added to the main 
	 * game world view in the driver
	 * 
	 */
	public ExistenceGameWorldView getExistenceGameWorldView() {
		return existenceGameWorldView;
	}
	

}

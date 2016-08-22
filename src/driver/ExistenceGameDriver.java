package driver;

import controller.ExistenceController;
import controller.ExistenceControllerInterface;
import controller.TheProtectorController;
import controller.TheProtectorControllerInterface;
import javafx.application.Application;
import javafx.stage.Stage;
import model_logic.ExistenceModel;
import model_logic.ExistenceModelInterface;
import model_logic.GameWorldModel;
import model_sprites.TheProtectorModel;
import view.ExistenceGameWorldView;
import view_sprites.TheProtectorView;
 
/**
 * The main method for the game
 * 
 * @author alistaircooper
 */
public class ExistenceGameDriver extends Application {
	
	// **********************
	// setup the game world
	//***********************
	
	// setup game world model 
    GameWorldModel gameWorldModel = new ExistenceModel(60, "E X I S T E N C E");
    ExistenceModelInterface existenceModel = (ExistenceModelInterface)gameWorldModel;
    
    // reference to controller
    ExistenceControllerInterface existenceController;
    
    // Note: view is created within the controller layer
    
    // **********************
 	// setup The Protector (the player)
 	//***********************
    
    // setup game world model 
    TheProtectorModel theProtectorModel = new TheProtectorModel(400, 400);
//    TheProtectorModelInterface theProtectorModelInterface = (TheProtectorModelInterface)theProtectorModel;
    
    // reference to controller
    TheProtectorControllerInterface theProtectorController;
    
    // Note: view is created within the controller layer

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) {
    	
    	// create controller and pass in the model and primaryStage
        existenceController = new ExistenceController(existenceModel, primaryStage);
        
        // create TheProtector controller and pass in the model
        theProtectorController = new TheProtectorController(theProtectorModel);
        
        // add TheProtector to ExistenceModel sprite manager
        existenceModel.getSpriteManager().addSprites(theProtectorModel);
        
        
        // get TheProtectorView created by TheProtectorController
        TheProtectorView theProtectorView = theProtectorController.getTheProtectorView();
        
        // get ExistenceGameWorldView created by ExistenceController
        ExistenceGameWorldView existenceGameWorldView = existenceController.getExistenceGameWorldView();
        
        // initialize the model
    	gameWorldModel.initialize();
        
        // add TheProtectorView to the main game world view
        existenceGameWorldView.getSceneNodes().getChildren().add(0, theProtectorView.node);
        
        // request focus on TheProtectorView
        theProtectorView.node.requestFocus();
        
        // kick off the game loop
        gameWorldModel.beginGameLoop();
 
        // display window
        primaryStage.show();
    }
    
 
}

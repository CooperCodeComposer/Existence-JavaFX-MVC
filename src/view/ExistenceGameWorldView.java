package view;

import controller.ExistenceControllerInterface;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import model_logic.ExistenceModel;
import model_logic.ExistenceModelInterface;
import model_logic.Settings;
import model_logic.Statistics;
import model_sprites.BlueLifeFormModel;
import model_sprites.DeathFormModel;
import model_sprites.DeathFormModelInterface;
import model_sprites.GreenLifeFormModel;
import model_sprites.LifeFormModelInterface;
import model_sprites.RedLifeFormModel;
import view_sprites.DeathFormView;
import view_sprites.LifeFormView;

/**
 * Class to draw main view UI elements for game
 * holds reference to ExistenceModel 
 * registers itself as an observer of the existenceModel
 * 
 * @author alistaircooper
 *
 */
public class ExistenceGameWorldView extends GameWorldView implements ExistenceModelObserver{
	
	/** text for score in years */
	Text yearsText = new Text();
	
	ExistenceModelInterface existenceModel;            // reference to model
	ExistenceControllerInterface existenceController;  // reference to controller
	
	
	public ExistenceGameWorldView(ExistenceControllerInterface existenceController, ExistenceModelInterface existenceModel) {
		this.existenceModel = existenceModel;
		this.existenceController = existenceController;
		existenceModel.registerObserver((ExistenceModelObserver) this);
	}
	
	
	/**
	 * Initialize the game world by adding sprite objects.
	 * 
	 * @param primaryStage
	 */
	@Override
	public void initialize(final Stage primaryStage) {
		// Sets the window title
		primaryStage.setTitle(existenceModel.getWindowTitle());

		// Create the main pane
		setSceneNodes(new Pane());
		
		// Add background image
		String image = ExistenceModel.class.getResource("/images/ExistenceBackground.png").toExternalForm();
		getSceneNodes().setStyle("-fx-background-image: url('" + image + "'); " +
		           "-fx-background-position: center center; " +
		           "-fx-background-repeat: stretch;");
		
		// Set the scene and stage
		setGameSurface(new Scene(getSceneNodes(), Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT));
		primaryStage.setScene(getGameSurface());
		
	}
	
	/**
	 * Method to draw the score board
	 */
	public void drawScoreLayer() {
		Color fillColor = Color.rgb(133, 133, 133);

		yearsText.setFont(Font.font(null, FontWeight.LIGHT, 30));
		yearsText.setFill(fillColor);

		getSceneNodes().getChildren().add(yearsText);

		yearsText.relocate(128, 8);
		yearsText.setText("" + Statistics.yearsSurvived);

		yearsText.setBoundsType(TextBoundsType.VISUAL);
	}
	
	/**
	 * Method to update the score board
	 */
	public void updateScoreLayer() {
		yearsText.setText("" + Statistics.yearsSurvived);
	}

	
	/**
	 * Method to draw red life forms
	 * 
	 * @param redLifeFormModel
	 * @param startX
	 * @param staryY
	 * 
	 */
	public void drawRedLifeForm(RedLifeFormModel redLifeFormModel, double startX, double startY) {
		
		// for simplicity set the color in this method 
		/** color of sphere */
		Color color = Color.rgb(255, 33, 33);
		
		// cast through interface
		LifeFormModelInterface lifeFormModel = (LifeFormModelInterface) redLifeFormModel;
		
		// create a RedLifeFormView and pass in the RedLifeFormModel and color
		LifeFormView lifeFormView = new LifeFormView(lifeFormModel, color);
		
		// set initial view position
		lifeFormView.node.setTranslateX(startX);
		lifeFormView.node.setTranslateY(startY);
		
		// add to scene
		getSceneNodes().getChildren().add(0, lifeFormView.node);	
	}

	/**
	 * Method to draw blue life forms
	 * 
	 * @param blueLifeFormModel
	 * @param startX
	 * @param staryY
	 * 
	 */
	public void drawBlueLifeForm(BlueLifeFormModel blueLifeFormModel, double startX, double startY) {
		
		// for simplicity set the color in this method 
		/** color of sphere */
		Color color = Color.rgb(64, 224, 208);
		
		// cast through interface
		LifeFormModelInterface lifeFormModel = (LifeFormModelInterface) blueLifeFormModel;
		
		// create a RedLifeFormView and pass in the RedLifeFormModel and color
		LifeFormView lifeFormView = new LifeFormView(lifeFormModel, color);
		
		// set initial view position
		lifeFormView.node.setTranslateX(startX);
		lifeFormView.node.setTranslateY(startY);
		
		// add to scene
		getSceneNodes().getChildren().add(0, lifeFormView.node);	
	}

	/**
	 * Method to draw green life forms
	 * 
	 * @param greenLifeFormModel
	 * @param startX
	 * @param staryY
	 * 
	 */
	public void drawGreenLifeForm(GreenLifeFormModel greenLifeFormModel, double startX, double startY) {
		
		// for simplicity set the color in this method 
		/** color of sphere */
		Color color = Color.rgb(127, 255, 0);
		
		// cast through interface
		LifeFormModelInterface lifeFormModel = (LifeFormModelInterface) greenLifeFormModel;
		
		// create a RedLifeFormView and pass in the RedLifeFormModel and color
		LifeFormView lifeFormView = new LifeFormView(lifeFormModel, color);
		
		// set initial view position
		lifeFormView.node.setTranslateX(startX);
		lifeFormView.node.setTranslateY(startY);
		
		// add to scene
		getSceneNodes().getChildren().add(0, lifeFormView.node);	
	}


	/**
	 * Method to draw death form
	 * 
	 * @param deathLifeFormModel
	 * @param startX
	 * @param staryY
	 * 
	 */
	public void drawDeathForm(DeathFormModel deathFormModel, double startX, double startY) {
		
		// cast through interface
		DeathFormModelInterface deathFormModelCast = (DeathFormModelInterface) deathFormModel;
				
		// create a DeathFormView and pass in the DeathFormModel
		DeathFormView deathFormView = new DeathFormView(deathFormModelCast);
				
		// set initial view position
		deathFormView.node.setTranslateX(startX);
		deathFormView.node.setTranslateY(startY);
				
		// add to scene
		getSceneNodes().getChildren().add(0, deathFormView.node);
		
	}

	
	/**
	 * Method to draw wall block
	 * note: wall blocks do not have a separate view class
	 * 
	 */
	public void drawWall(double x, double y, double height, double width) {
		
		/** shape of wall */
		Rectangle rectangle = new Rectangle();
		
		/** color of wall */
		Color color = Color.rgb(112, 128, 144);
		
		rectangle.setHeight(height);
		rectangle.setWidth(width);
		rectangle.setFill(color);
		
		Node node;
		
		node = rectangle;
		
		node.setTranslateX(x);
		node.setTranslateY(y);
		node.setVisible(true);
		
		// add node to scene
		getSceneNodes().getChildren().add(0, node);
			
	}

}

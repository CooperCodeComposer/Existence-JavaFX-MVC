package view_sprites;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model_logic.Settings;
import model_sprites.LifeFormModelInterface;

/**
 * Class responsible for RedLifeForms in the view layer
 * holds a reference to RedLifeFormModel
 * is an observer of the RedLifeFormModel
 * 
 * @author alistaircooper
 *
 */
public class LifeFormView implements LifeFormModelObserver {
	
	/** reference to the model */
	LifeFormModelInterface lifeFormModel;  
	
	/** node for life form */
	public Node node;
	
	/** shape of life form */
	public Circle sphere = new Circle();
	
	/** radius of life form */
	private double radius = Settings.LIFEFORM_RADIUS;
	
	/** constructor */
	public LifeFormView(LifeFormModelInterface lifeFormModel, Color color) {
		
		// reference to LifeFormModel
		this.lifeFormModel = lifeFormModel;
		lifeFormModel.registerObserver((LifeFormModelObserver) this);

		sphere.setCenterX(0);
		sphere.setCenterY(0);
		sphere.setRadius(radius);
		sphere.setCache(true);
		sphere.setVisible(true);
		//sphere.setId(xxx);
		sphere.setFill(color);
		
		// set javafx node to sphere
		node = sphere;
	
	}
	
	/**
	 * Method to update the view position 
	 */
	public void updateViewPosition(double vX, double vY) {
		node.setTranslateX(node.getTranslateX() + vX);
		node.setTranslateY(node.getTranslateY() + vY);
	}
	
	/**
	 * Animate an implosion. 
	 * 
	 */
	public void implode() {
	
		FadeTransition ft = new FadeTransition(Duration.millis(300), node);
		ft.setFromValue(node.getOpacity());
		ft.setToValue(0);

		ft.setOnFinished(arg0 -> {
			// set isAlive to false in the model
			lifeFormModel.setIsAlive(false);
		//	gameWorldView.getSceneNodes().getChildren().remove(node);
		});

		ft.play();
	}

}

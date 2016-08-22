package view_sprites;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model_logic.Settings;
import model_sprites.DeathFormModelInterface;

/**
 * Class responsible for DeathForm in the view layer holds a reference to
 * DeathFormFormModel is an observer of the DeathFormFormModel
 * 
 * @author alistaircooper
 *
 */
public class DeathFormView implements DeathFormModelObserver {

	/** reference to the model */
	DeathFormModelInterface deathFormModel;

	/** node for death form */
	public Node node;

	/** image for death form */
	private Image image = new Image("images/DeathFormSH.png");

	/** variables required by SpriteAnimation */
	private ImageView imageView = new ImageView(image); // ImageView for sprite
	private int columns = 4; // number of columns in sprite image
	private int count = 8; // number of frames for sprite
	private int offset_X = 0; // offset X of where the image starts
	private int offset_Y = 0; // offset Y of where the image starts
	private int width = Settings.DEATHFORM_HEIGHT; // width of the sprite in px
	private int height = Settings.DEATHFORM_WIDTH; // height of the sprite in px

	public DeathFormView(DeathFormModelInterface deathFormModel) {

		// reference to DeathFormModel
		this.deathFormModel = deathFormModel;
		deathFormModel.registerObserver((DeathFormModelObserver) this);

		imageView.setCache(true);
		imageView.setVisible(true);

		// sets what part of the image is displayed as a frame
		imageView.setViewport(new Rectangle2D(offset_X, offset_Y, width, height));
		
		// setup sprite animation
		setupSpriteAnimation();
		
		Circle sphere = new Circle();
		sphere.setRadius(30);
		sphere.setFill(Color.BLACK);
		
		// set imageView to node
		node = imageView;
	}
	
	/**
	 * Method to setup animation of sprite required by AnimationInterface
	 */
	public void setupSpriteAnimation() {
		// create new sprite animation object
		final Animation animation = new SpriteAnimation(imageView, Duration.millis(3000), count, columns, offset_X,
				offset_Y, width, height);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play(); // plays the animation

	}
	
	/**
	 * Method to update the view position 
	 */
	public void updateViewPosition(double vX, double vY) {
		node.setTranslateX(node.getTranslateX() + vX);
		node.setTranslateY(node.getTranslateY() + vY);
	}

}

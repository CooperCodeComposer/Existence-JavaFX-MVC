package view_sprites;

import controller.TheProtectorControllerInterface;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model_sprites.TheProtectorModelInterface;
import view.Input;

/**
 * Class responsible for TheProtector in the view layer holds a reference to
 * TheProtectorModel is an observer of the TheProtectorModel Holds a reference
 * to TheProtectorController and TheProtectorModel
 * 
 * @author alistaircooper
 *
 */
public class TheProtectorView implements TheProtectorModelObserver {

	/** reference to the model */
	TheProtectorModelInterface theProtectorModel;

	/** reference to the controller */
	TheProtectorControllerInterface theProtectorController;

	/** node for the protector */
	public Node node;
	
	/** input from keyboard */
	public Input input;

	/** sprite sheet */
	private Image image = new Image("images/TheProtectorSH.png");

	/** image view for sprite */
	final ImageView imageView = new ImageView(image); // ImageView for sprite

	/** variables required by SpriteAnimation */
	public int columns = 4; // number of columns in sprite image
	public int count = 8; // number of frames for sprite
	public int offset_X = 0; // offset X of where the image starts
	public int offset_Y = 0; // offset Y of where the image starts
	public int width = 40; // width of the sprite in px
	public int height = 40; // height of the sprite in px

	/** constructor */
	public TheProtectorView(TheProtectorControllerInterface theProtectorController,
			TheProtectorModelInterface theProtectorModel) {

		// register observer to the model
		this.theProtectorModel = theProtectorModel;
		theProtectorModel.registerObserver((TheProtectorModelObserver) this);

		this.theProtectorController = theProtectorController;

		// sets what part of the image is displayed as a frame
		imageView.setViewport(new Rectangle2D(offset_X, offset_Y, width, height));

		setupSpriteAnimation();

		// set javafx node to imageView
		node = imageView;

		// initialize input with the node
		input = new Input(node);

		// register input listeners
		input.addListeners(); // TODO: remove listeners on game over

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

	/**
	 * Method to tell the controller to process keyboard input
	 */
	public void processInputInController() {
		theProtectorController.processInput();
	}

}

package view_sprites;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Class to handle view animation for sprites
 * 
 * References: Creating a Sprite Animation with Javafx
 * Mike's Blog 
 * http://blog.netopyr.com/2012/03/09/creating-a-sprite-animation-with-javafx/
 */
public class SpriteAnimation extends Transition {

	private final ImageView imageView;
	private final int count; // number of sprites
	private final int columns; // number of columns on sprite sheet
	private final int offsetX; // sprite image offset x
	private final int offsetY; // sprite image offset y
	private final int width; // animation width
	private final int height; // animation height

	private int lastIndex;

	public SpriteAnimation(ImageView imageView, Duration duration, int count, int columns, int offsetX, int offsetY,
			int width, int height) {
		this.imageView = imageView;
		this.count = count;
		this.columns = columns;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
		setCycleDuration(duration); // set duration of animation
		// set how animation is interpolated
		setInterpolator(Interpolator.LINEAR); 
	}

	protected void interpolate(double k) {
		// uses value from 0 to 1 to create index number for each sprite
		final int index = Math.min((int) Math.floor(k * count), count - 1);
		if (index != lastIndex) {
			// cycles through the rows and columns
			final int x = (index % columns) * width + offsetX;
			final int y = (index / columns) * height + offsetY;
			imageView.setViewport(new Rectangle2D(x, y, width, height));
			// set the lastIndex to current index
			lastIndex = index;
		}
	}

} 

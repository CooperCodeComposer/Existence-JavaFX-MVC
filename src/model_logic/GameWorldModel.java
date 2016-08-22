package model_logic;

// Javafx components used for model layer timing calculations only
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model_sprites.Sprite;
 
/**
 * This application sets up the game loop 
 * 
 * References: JavaFX 2 Game Tutorial by Carl Dea 
 * https://carlfx.wordpress.com/2012/03/29/javafx-2-gametutorial-part-1/
 *
 */
public abstract class GameWorldModel {
 
    
    /** The game loop using JavaFX's Timeline API.*/
    private static Timeline gameLoop;
 
    /** Number of frames per second. */
    private final int framesPerSecond;
 
    /** Title in the application window.*/
    private final String windowTitle;
 
    /**
     * The sprite manager.
     */
    private final SpriteManager spriteManager = new SpriteManager();
 
    /**
     * Constructor that is called by the derived class. This will
     * set the frames per second, title, and setup the game loop.
     * @param fps - Frames per second.
     * @param title - Title of the application window.
     */
    public GameWorldModel(final int fps, final String title) {
        framesPerSecond = fps;
        windowTitle = title;
        // create and set timeline for the game loop
        buildAndSetGameLoop();
    }
 
    /**
     * Builds and sets the game loop ready to be started.
     */
    protected final void buildAndSetGameLoop() {
 
        final Duration oneFrameAmt = Duration.millis(1000/getFramesPerSecond());
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
            event -> {
            	
			    // update actors
			    updateSprites();
 
			    // check for collision
			    checkCollisions();
 
			    // removed dead things
			    cleanupSprites();
			    
			    // updates game score 
			    updateGameScore();
			    
			    // check if game is over 
			    isGameOver();
 
			}); // oneFrame
        
        Timeline gameTimeline = new Timeline();
        gameTimeline.setCycleCount(Animation.INDEFINITE);
        gameTimeline.getKeyFrames().add(oneFrame);
        
        // sets the game world's game loop (Timeline)
        setGameLoop(gameTimeline);
    }
 
    /**
     * Initialize the game world model.
     * 
     */
    public abstract void initialize();
 
    /**Starts the Timeline objects containing one key frame
     * that simply runs indefinitely with each frame invoking a method
     * to update sprite objects, check for collisions, cleanup sprite
     * objects and check if the game is over
     *
     */
    public void beginGameLoop() {
        getGameLoop().play();
    }
 
    /**
     * Updates each game sprite in the game world. This method will
     * loop through each sprite and passing it to the handleUpdate()
     * method. The derived class should override handleUpdate() method.
     *
     */
    protected void updateSprites() {
        for (Sprite sprite:spriteManager.getAllSprites()){
            handleUpdate(sprite);
        }
    }
 
    /** Updates the sprite object's information to position on the game surface.
     * @param sprite - The sprite to update.
     */
    protected void handleUpdate(Sprite sprite) {
    }
 
    /**
     * Checks each game sprite in the game world to determine a collision
     * occurred. The method will loop through each sprite and
     * passing it to the handleCollision()
     * method. The derived class overrides this method.
     *
     */
    protected void checkCollisions() {
        // check other sprite's collisions
        spriteManager.resetCollisionsToCheck();
        // check each sprite against other sprite objects.
        for (Sprite spriteA:spriteManager.getCollisionsToCheck()){
            for (Sprite spriteB:spriteManager.getAllSprites()){
                if (handleCollision(spriteA, spriteB)) {
                    // The break helps optimize the collisions
                    //  The break statement means one object only hits another
                    // object as opposed to one hitting many objects.
                    // To be more accurate comment out the break statement.
                    break;
                }
            }
        }
    }
 
    /**
     * When two objects collide this method can handle the passed in sprite
     * objects. By default it returns false, meaning the objects do not
     * collide.
     * @param spriteA - called from checkCollision() method to be compared.
     * @param spriteB - called from checkCollision() method to be compared.
     * @return boolean True if the objects collided, otherwise false.
     */
    protected boolean handleCollision(Sprite spriteA, Sprite spriteB) {
        return false;
    }
 
    /**
     * Sprites to be cleaned up.
     */
    protected void cleanupSprites() {
        spriteManager.cleanupSprites();
    }
    
    /**
     * Check if game over conditions are met
     */
    protected abstract void updateGameScore();
    
    /**
     * Check if game over conditions are met
     */
    protected abstract void isGameOver();
 
    /**
     * Returns the frames per second.
     * @return int The frames per second.
     */
    protected int getFramesPerSecond() {
        return framesPerSecond;
    }
 
    /**
     * Returns the game's window title.
     * @return String The game's window title.
     */
    public String getWindowTitle() {
        return windowTitle;
    }
 
    /**
     * The game loop (Timeline) which is used to update, check collisions,
     * cleanup sprite objects, and check if game over at every interval (fps).
     * @return Timeline An animation running indefinitely representing the game
     * loop.
     */
    protected static Timeline getGameLoop() {
        return gameLoop;
    }
 
    /**
     * The sets the current game loop for this game world.
     * @param gameLoop Timeline object of an animation running indefinitely
     * representing the game loop.
     */
    protected static void setGameLoop(Timeline gameLoop) {
        GameWorldModel.gameLoop = gameLoop;
    }
 
    /**
     * Returns the sprite manager containing the sprite objects to
     * manipulate in the game.
     * @return SpriteManager The sprite manager.
     */
    public SpriteManager getSpriteManager() {
        return spriteManager;
    }
 
    
 
}

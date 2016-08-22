package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Base class for main UI elements for game
 * 
 * @author alistaircooper
 *
 */
public abstract class GameWorldView {
	
	/** The JavaFX Scene as the game surface */
    private Scene gameSurface;
    
    /** All nodes to be displayed in the game window. */
    private Pane sceneNodes;
    
    /**
     * Initialize the game world by update the JavaFX Stage.
     * @param primaryStage
     */
    public abstract void initialize(final Stage primaryStage);
    
    /**
     * Returns the JavaFX Scene. This is called the game surface to
     * allow the developer to add JavaFX Node objects onto the Scene.
     * @return
     */
    public Scene getGameSurface() {
        return gameSurface;
    }
 
    /**
     * Sets the JavaFX Scene. This is called the game surface to
     * allow the developer to add JavaFX Node objects onto the Scene.
     * @param gameSurface The main game surface (JavaFX Scene).
     */
    protected void setGameSurface(Scene gameSurface) {
        this.gameSurface = gameSurface;
    }
 
    /**
     * All JavaFX nodes which are rendered onto the game surface(Scene) is
     * a JavaFX Pane object.
     * @return Pane The root containing many child nodes to be displayed into
     * the Scene area.
     */
    public Pane getSceneNodes() {
        return sceneNodes;
    }
 
    /**
     * Sets the JavaFX Group that will hold all JavaFX nodes which are rendered
     * onto the game surface(Scene) is a JavaFX Pane object.
     * @param sceneNodes The root container having many children nodes
     * to be displayed into the Scene area.
     */
    protected void setSceneNodes(Pane sceneNodes) {
        this.sceneNodes = sceneNodes;
    }

}

//*************************-=-=-=-=-=-=-=-=-=-=-=-=-**************************// 
//********************<         E X I S T E N C E         >*******************//
//*************************-=-=-=-=  V 2.0  -=-=-==-**************************//
//**************                     AUTHOR                     **************//
//---------------<_>------->>>   ALISTAIR COOPER   <<<-------<_>--------------//
//*****************<_>         CREATED: 08/10/2016          <_>***************//
//**************************-=-=-=-=-=-=-=-=-=-=-=-=-*************************//
//                                                                            //

/*
 * There are three kinds of life forms in the ecosystem: Red, Blue and Green
 * 
 * Red eat blue 
 * Blue eat green
 * Green eat red
 * 
 * You are The Protector. It's your job to maintain a balanced ecosystem.
 * There must be at least 1 of each species alive at any time
 * 
 * If you come into contact with a life form it will self replicate
 * 
 * Unfortunately, along the way you may encounter death forms. If you touch these
 * you will die. 
 * 
 */
package model_logic;

import view.ExistenceModelObserver;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Timeline; // timeline used for data model calculations
import model_sprites.BlueLifeFormModel;
import model_sprites.DeathFormModel;
import model_sprites.GreenLifeFormModel;
import model_sprites.LifeFormModel;
import model_sprites.RedLifeFormModel;
import model_sprites.Sprite;
import model_sprites.TheProtectorModel;
import model_sprites.WallModel;

/**
 * Subclass of GameWorld to hold logic for existenceGameWorld
 *
 * @author alistaircooper
 */
public class ExistenceModel extends GameWorldModel implements ExistenceModelInterface {

	/** array list for model observers */
	ArrayList<ExistenceModelObserver> existenceModelObservers = new ArrayList<ExistenceModelObserver>();

	public ExistenceModel(int fps, String title) {
		super(fps, title);
	}

	/**
	 * Initialize the game world by adding sprite objects.
	 * 
	 */
	@Override
	public void initialize() {

		// Add score board
		createScoreLayer();

		// Build walls (x, y = top left reference)
		buildWall(200 - (Settings.WALL_BLOCK_WIDTH / 2), 200 - (Settings.WALL_BLOCK_HEIGHT / 2));
		buildWall(200 - (Settings.WALL_BLOCK_WIDTH / 2), 600 - (Settings.WALL_BLOCK_HEIGHT / 2));
		buildWall(600 - (Settings.WALL_BLOCK_WIDTH / 2), 200 - (Settings.WALL_BLOCK_HEIGHT / 2));
		buildWall(600 - (Settings.WALL_BLOCK_WIDTH / 2), 600 - (Settings.WALL_BLOCK_HEIGHT / 2));

		// Create red life forms (number, start x, start y)
		generateRedLifeForms(Settings.NUMBER_OF_RED, Settings.RED_X_START, Settings.RED_Y_START);

		// Create blue life forms (number, start x, start y)
		generateBlueLifeForms(Settings.NUMBER_OF_BLUE, Settings.BLUE_X_START, Settings.BLUE_Y_START);

		// Create red life forms (number, start x, start y)
		generateGreenLifeForms(Settings.NUMBER_OF_GREEN, Settings.GREEN_X_START, Settings.GREEN_Y_START);

		// Create death forms at (x, y)
		generateDeathForm(375, 750);

	}

	/**
	 * Method to create red life forms
	 * 
	 * @param numSpheres
	 * @param startX
	 * @param startY
	 */
	public void generateRedLifeForms(int numSpheres, int startX, int startY) {

		for (int i = 0; i < numSpheres; i++) {

			// initialize
			RedLifeFormModel lifeFormModel = new RedLifeFormModel();

			lifeFormModel.currentX = startX;
			lifeFormModel.currentY = startY;

			// add to actors in play (sprite objects)
			getSpriteManager().addSprites(lifeFormModel);

			// draw the LifeForm in the ExistenceGameWorldView
			ExistenceModelObserver observer = (ExistenceModelObserver) existenceModelObservers.get(0);
			observer.drawRedLifeForm(lifeFormModel, startX, startY);
		}
	}

	/**
	 * Method to create blue life forms
	 * 
	 * @param numSpheres
	 * @param startX
	 * @param startY
	 */
	public void generateBlueLifeForms(int numSpheres, int startX, int startY) {

		for (int i = 0; i < numSpheres; i++) {

			// initialize
			BlueLifeFormModel lifeFormModel = new BlueLifeFormModel();

			lifeFormModel.currentX = startX;
			lifeFormModel.currentY = startY;

			// add to actors in play (sprite objects)
			getSpriteManager().addSprites(lifeFormModel);

			// draw the LifeForm in the ExistenceGameWorldView
			ExistenceModelObserver observer = (ExistenceModelObserver) existenceModelObservers.get(0);
			observer.drawBlueLifeForm(lifeFormModel, startX, startY);
		}
	}

	/**
	 * Method to create green life forms
	 * 
	 * @param numSpheres
	 * @param startX
	 * @param startY
	 */
	public void generateGreenLifeForms(int numSpheres, int startX, int startY) {

		for (int i = 0; i < numSpheres; i++) {

			// initialize
			GreenLifeFormModel lifeFormModel = new GreenLifeFormModel();

			lifeFormModel.currentX = startX;
			lifeFormModel.currentY = startY;

			// add to actors in play (sprite objects)
			getSpriteManager().addSprites(lifeFormModel);

			// draw the LifeForm in the ExistenceGameWorldView
			ExistenceModelObserver observer = (ExistenceModelObserver) existenceModelObservers.get(0);
			observer.drawGreenLifeForm(lifeFormModel, startX, startY);
		}
	}

	/**
	 * Method to give birth to new life forms
	 * 
	 * @param parentLifeForm
	 * @param startX
	 * @param startY
	 */
	public void giveBirthToLifeForm(LifeFormModel parentLifeForm) {

		// reset time since mating on the parent
		parentLifeForm.setTimeSinceMating(0);

		// cast as same kind of life form as parent
		if (parentLifeForm instanceof RedLifeFormModel) {
			RedLifeFormModel newLifeModel = new RedLifeFormModel();

			double parentX = parentLifeForm.currentX;
			double parentY = parentLifeForm.currentY;

			newLifeModel.currentX = parentX;
			newLifeModel.currentY = parentY;

			// set direction to opposite of parent
			newLifeModel.vX = -parentLifeForm.vX;
			newLifeModel.vY = -parentLifeForm.vY;

			// add to actors in play (sprite objects)
			getSpriteManager().addSprites(newLifeModel);

			// draw the LifeForm in the ExistenceGameWorldView
			ExistenceModelObserver observer = (ExistenceModelObserver) existenceModelObservers.get(0);
			observer.drawRedLifeForm(newLifeModel, parentX, parentY);

		} else if (parentLifeForm instanceof BlueLifeFormModel) {
			BlueLifeFormModel newLifeModel = new BlueLifeFormModel();

			double parentX = parentLifeForm.currentX;
			double parentY = parentLifeForm.currentY;

			newLifeModel.currentX = parentX;
			newLifeModel.currentY = parentY;

			// set direction to opposite of parent
			newLifeModel.vX = -parentLifeForm.vX;
			newLifeModel.vY = -parentLifeForm.vY;

			// add to actors in play (sprite objects)
			getSpriteManager().addSprites(newLifeModel);

			// draw the LifeForm in the ExistenceGameWorldView
			ExistenceModelObserver observer = (ExistenceModelObserver) existenceModelObservers.get(0);
			observer.drawBlueLifeForm(newLifeModel, parentX, parentY);

		} else {
			GreenLifeFormModel newLifeModel = new GreenLifeFormModel();

			double parentX = parentLifeForm.currentX;
			double parentY = parentLifeForm.currentY;

			newLifeModel.currentX = parentX;
			newLifeModel.currentY = parentY;

			// set direction to opposite of parent
			newLifeModel.vX = -parentLifeForm.vX;
			newLifeModel.vY = -parentLifeForm.vY;

			// add to actors in play (sprite objects)
			getSpriteManager().addSprites(newLifeModel);

			// draw the LifeForm in the ExistenceGameWorldView
			ExistenceModelObserver observer = (ExistenceModelObserver) existenceModelObservers.get(0);
			observer.drawGreenLifeForm(newLifeModel, parentX, parentY);
		}

	}

	/**
	 * Method to create death form
	 * 
	 * @param x
	 * @param y
	 * 
	 */
	public void generateDeathForm(double startX, double startY) {

		// initialize
		DeathFormModel deathFormModel = new DeathFormModel();

		deathFormModel.currentX = startX;
		deathFormModel.currentY = startY;

		// add to actors in play (sprite objects)
		getSpriteManager().addSprites(deathFormModel);

		// draw the LifeForm in the ExistenceGameWorldView
		ExistenceModelObserver observer = (ExistenceModelObserver) existenceModelObservers.get(0);
		observer.drawDeathForm(deathFormModel, startX, startY);
	}

	/**
	 * Method to build walls
	 */
	public void buildWall(double x, double y) {

		WallModel w = new WallModel();

		w.currentX = x;
		w.currentY = y;

		// add to actors in play (sprite objects)
		getSpriteManager().addSprites(w);

		// update the view
		ExistenceModelObserver observer = (ExistenceModelObserver) existenceModelObservers.get(0);
		observer.drawWall(x, y, w.getHeight(), w.getWidth());

	}

	/**
	 * Each sprite will update velocity, increment time since last mating and
	 * bounce off wall borders.
	 * 
	 * @param sprite
	 * 
	 */
	@Override
	public void handleUpdate(Sprite sprite) {
		if (sprite instanceof LifeFormModel) {
			LifeFormModel lifeForm = (LifeFormModel) sprite;

			// advance time since last mating
			lifeForm.advanceTimeSinceMating();

			// advance the spheres position
			lifeForm.update();

			// bounce off the walls when outside of boundaries
			// remember x and y reference CENTER of circle
			if (lifeForm.currentX > (Settings.SCENE_WIDTH - lifeForm.getRadius())
					|| (lifeForm.currentX - lifeForm.getRadius()) < 0) {
				lifeForm.vX = lifeForm.vX * -1;
			}
			if (lifeForm.currentY > Settings.SCENE_HEIGHT - lifeForm.getRadius()
					|| (lifeForm.currentY - lifeForm.getRadius()) < 0) {
				lifeForm.vY = lifeForm.vY * -1;
			}
		}

		if (sprite instanceof DeathFormModel) {
			DeathFormModel deathForm = (DeathFormModel) sprite;

			// advance the death form position
			deathForm.update();

			// bounce off the walls when outside of boundaries
			if (deathForm.currentX > (Settings.SCENE_WIDTH - deathForm.getWidth()) || deathForm.currentX < 0) {
				deathForm.vX = deathForm.vX * -1;
			}
			if (deathForm.currentY > Settings.SCENE_HEIGHT - deathForm.getHeight() || deathForm.currentY < 0) {
				deathForm.vY = deathForm.vY * -1;
			}
		}

		if (sprite instanceof TheProtectorModel) {
			TheProtectorModel player = (TheProtectorModel) sprite;

			// tell model to process the keyboard input
			player.processInput();

			// update the position within the model
			player.update();

			// reset canMove
			// canMove would have been set to false if the player would have
			// collided with wall block
			player.canMove = true;
		}
	}

	/**
	 * How to handle the collision of two sprite objects.
	 * 
	 * @param spriteA
	 * @param spriteB
	 * @return boolean
	 */
	@Override
	public boolean handleCollision(Sprite spriteA, Sprite spriteB) {
		if (spriteA.collide(spriteB)) {

			// if red hits blue: blue implode
			if (spriteA instanceof RedLifeFormModel && spriteB instanceof BlueLifeFormModel) {
				((LifeFormModel) spriteB).implode();
				getSpriteManager().addSpritesToBeRemoved(spriteB);
				return true;
			}

			// if blue hits green: green implodes
			if (spriteA instanceof BlueLifeFormModel && spriteB instanceof GreenLifeFormModel) {
				((LifeFormModel) spriteB).implode();
				getSpriteManager().addSpritesToBeRemoved(spriteB);
				return true;
			}

			// if green hits red: red implodes
			if (spriteA instanceof GreenLifeFormModel && spriteB instanceof RedLifeFormModel) {
				((LifeFormModel) spriteB).implode();
				getSpriteManager().addSpritesToBeRemoved(spriteB);
				return true;
			}

			// if life form hits a wall life form bounces off
			if (spriteA instanceof WallModel && spriteB instanceof LifeFormModel) {

				LifeFormModel lifeForm = (LifeFormModel) spriteB;
				WallModel wall = (WallModel) spriteA;

				// establish bounds of wall
				double wTop = wall.currentY;
				double wBottom = wall.currentY + wall.getHeight();
				double wLeft = wall.currentX;
				double wRight = wall.currentX + wall.getWidth();

				// establish bounds of LifeForm (treats circle as rectangle)
				// note (0, 0) is center of lifeForm
				double lTop = lifeForm.currentY - lifeForm.getRadius();
				double lBottom = lifeForm.currentY + lifeForm.getRadius();
				double lLeft = lifeForm.currentX - lifeForm.getRadius();
				double lRight = lifeForm.currentX + lifeForm.getRadius();

				// test which side of the wall it hit

				// hit top (must discount left or right collisions)
				if ((lBottom >= wTop) && ((lBottom - wTop) < (lRight - wLeft))
						&& ((lBottom - wTop) < (wRight - lLeft))) {
					lifeForm.vY *= -Settings.WALL_VELOCITY_BOOST;
				}

				// hit bottom (must discount left or right collisions)
				if ((lTop <= wBottom) && ((wBottom - lTop) < (lRight - wLeft))
						&& ((wBottom - lTop) < (wRight - lLeft))) {
					lifeForm.vY *= -Settings.WALL_VELOCITY_BOOST;
				}

				// hit right (must discount top or bottom collisions)
				if ((lLeft <= wRight) && ((wRight - lLeft) < (lBottom - wTop))
						&& ((wRight - lLeft) < (wBottom - lTop))) {
					lifeForm.vX *= -Settings.WALL_VELOCITY_BOOST;
				}

				// hit left (must discount top or bottom collisions)
				if ((lRight >= wLeft) && ((lRight - wLeft) < (lBottom - wTop))
						&& ((lRight - wLeft) < (wBottom - lTop))) {
					lifeForm.vX *= -Settings.WALL_VELOCITY_BOOST;
				}

				// restrict to maximum velocity
				// (so it doesn't get outta control)
				if (lifeForm.vX > Settings.LIFEFORM_MAXV) {
					lifeForm.vX = Settings.LIFEFORM_MAXV;
				}
				if (lifeForm.vX < -Settings.LIFEFORM_MAXV) {
					lifeForm.vX = -Settings.LIFEFORM_MAXV;
				}
				if (lifeForm.vY > Settings.LIFEFORM_MAXV) {
					lifeForm.vY = Settings.LIFEFORM_MAXV;
				}
				if (lifeForm.vY < -Settings.LIFEFORM_MAXV) {
					lifeForm.vY = -Settings.LIFEFORM_MAXV;
				}

				return true;
			}

			// if death form hits a wall: death form bounces off
			if (spriteA instanceof DeathFormModel && spriteB instanceof WallModel) {
				DeathFormModel d = (DeathFormModel) spriteA;
				WallModel w = (WallModel) spriteB;

				// establish bounds of wall
				double wTop = w.currentY;
				double wBottom = w.currentY + w.getHeight();
				double wLeft = w.currentX;
				double wRight = w.currentX + w.getWidth();

				// establish bounds of DeathForm
				double dTop = d.currentY;
				double dBottom = d.currentY + d.getHeight();
				double dLeft = d.currentX;
				double dRight = d.currentX + d.getWidth();

				// test which side of the block it hit

				// hit top (must discount left or right collisions)
				if ((dBottom >= wTop) && ((dBottom - wTop) < (dRight - wLeft))
						&& ((dBottom - wTop) < (wRight - dLeft))) {
					d.vY *= -1;
				}

				// hit bottom (must discount left or right collisions)
				if ((dTop <= wBottom) && ((wBottom - dTop) < (dRight - wLeft))
						&& ((wBottom - dTop) < (wRight - dLeft))) {
					d.vY *= -1;
				}

				// hit right (must discount top or bottom collisions)
				if ((dLeft <= wRight) && ((wRight - dLeft) < (wBottom - dTop))
						&& ((wRight - dLeft) < (dBottom - wTop))) {
					d.vX *= -1;
				}

				// hit left (must discount top or bottom collisions)
				if ((dRight >= wLeft) && ((dRight - wLeft) < (wBottom - dTop))
						&& ((dRight - wLeft) < (wBottom - dTop))) {
					d.vX *= -1;
				}

				return true;
			}

			// if DeathForm hits LifeForm: LifeForm implodes
			if (spriteA instanceof DeathFormModel && spriteB instanceof LifeFormModel) {
				((LifeFormModel) spriteB).implode();
				getSpriteManager().addSpritesToBeRemoved(spriteB);
				return true;
			}

			// if DeathForm hit TheProtector (player): the player dies
			if (spriteA instanceof DeathFormModel && spriteB instanceof TheProtectorModel) {

				TheProtectorModel player = (TheProtectorModel) spriteB;
				player.setIsAlive(false);
			}

			// if the protector (player) hits a life form: life form gives birth
			if (spriteA instanceof TheProtectorModel && spriteB instanceof LifeFormModel) {

				LifeFormModel parentLifeForm = (LifeFormModel) spriteB;

				if (parentLifeForm.getFertile()) {

					giveBirthToLifeForm(parentLifeForm);

					return true;
				}
			}

			// if the protector (player) would hit a wall: turn off move
			if (spriteA instanceof TheProtectorModel && spriteB instanceof WallModel) {
				TheProtectorModel player = (TheProtectorModel) spriteA;

				player.canMove = false;

				return true;
			}
		}
		return false;
	}

	/**
	 * Method to remove dead things.
	 */
	@Override
	public void cleanupSprites() {
		// removes from the scene and backend store
		super.cleanupSprites();
	}

	/**
	 * Method to update game score
	 */
	@Override
	public void updateGameScore() {
		// update frame count and years stat
		Statistics.updateYearsSurvived();

		// update the view
		ExistenceModelObserver observer = (ExistenceModelObserver) existenceModelObservers.get(0);
		observer.updateScoreLayer();
	}

	/**
	 * Method to check if GameOver. Either if 1 of each LifeForm isn't preset.
	 * Or The Protector has been killed by a DeathForm
	 */
	@Override
	public void isGameOver() {
		final Timeline gameLoop = getGameLoop();
		List<Sprite> activeLifeForms = getSpriteManager().getAllSprites();

		Boolean containsRed = false;
		Boolean containsGreen = false;
		Boolean containsBlue = false;

		// check that there's at least 1 of each kind of life form
		for (Sprite a : activeLifeForms) {
			if (a instanceof RedLifeFormModel) {
				containsRed = true;
			}
			if (a instanceof BlueLifeFormModel) {
				containsBlue = true;
			}
			if (a instanceof GreenLifeFormModel) {
				containsGreen = true;
			}
		}
		if (!containsRed || !containsBlue || !containsGreen) {
			gameLoop.stop();
		}

		for (Sprite p : activeLifeForms) {
			if (p instanceof TheProtectorModel) {
				TheProtectorModel theProtector = (TheProtectorModel) p;

				// check if player is dead
				if (!theProtector.getIsAlive()) {
					gameLoop.stop();
				}
			}
		}
	}

	/**
	 * Method to create the score board
	 */
	public void createScoreLayer() {
		ExistenceModelObserver observer = (ExistenceModelObserver) existenceModelObservers.get(0);

		observer.drawScoreLayer();

	}

	/**
	 * Method to register observer
	 */
	public void registerObserver(ExistenceModelObserver o) {
		existenceModelObservers.add(o);
	}

	/**
	 * Method to remove observer
	 */
	public void removeObserver(ExistenceModelObserver o) {
		int i = existenceModelObservers.indexOf(o);
		if (i >= 0) {
			existenceModelObservers.remove(i);
		}
	}

}
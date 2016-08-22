package view;

import model_sprites.BlueLifeFormModel;
import model_sprites.DeathFormModel;
import model_sprites.GreenLifeFormModel;
import model_sprites.RedLifeFormModel;

/**
 * Interface to declare methods used by the ExistenceGameWorldView
 * 
 * @author alistaircooper
 *
 */
public interface ExistenceModelObserver {
	
	void drawRedLifeForm(RedLifeFormModel redLifeFormModel, double startX, double startY);
	
	void drawBlueLifeForm(BlueLifeFormModel blueLifeFormModel, double startX, double startY);
	
	void drawGreenLifeForm(GreenLifeFormModel greenLifeFormModel, double startX, double startY);
	
	void drawDeathForm(DeathFormModel deathFormModel, double startX, double startY);
	
	void drawWall(double x, double y, double height, double width);
		
	void drawScoreLayer();
	
	void updateScoreLayer();

}

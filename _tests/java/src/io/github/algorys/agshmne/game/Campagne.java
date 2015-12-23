package io.github.algorys.agshmne.game;

import io.github.algorys.agshmne.deplacement.Direction;
import io.github.algorys.agshmne.fenetre.Fenetre;
import io.github.algorys.agshmne.outils.MapUtils;
import io.github.algorys.agshmne.personnage.Personnage;
import io.github.algorys.agshmne.region.Region;
import io.github.algorys.agshmne.world.DefaultWorldFactory;
import io.github.algorys.agshmne.world.World;

public class Campagne {
	World world = new DefaultWorldFactory().create();
	Region map = world.getDefaultRegion();
	Personnage pj = new Personnage(map);
	MapUtils mapUtils = new MapUtils(map);
	
	public Campagne() {
	}

	public void run() {
		//world.move(Direction.NORTH, pj);
		//mapUtils.showStatus(pj);
		//world.move(Direction.NORTH, pj);
		//mapUtils.showStatus(pj);
		
		// TEST FENETRE
		Fenetre fenetre = new Fenetre(world);
		fenetre.setVisible(true);
	}
}

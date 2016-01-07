package io.github.algorys.agshmne.game;

import io.github.algorys.agshmne.character.Character;
import io.github.algorys.agshmne.outils.MapUtils;
import io.github.algorys.agshmne.region.JMap;
import io.github.algorys.agshmne.region.Region;
import io.github.algorys.agshmne.start.JWelcome;
import io.github.algorys.agshmne.world.DefaultWorldFactory;
import io.github.algorys.agshmne.world.World;

public class Campagne {
	World world = new DefaultWorldFactory().create();
	Region map = world.getDefaultRegion();
	MapUtils mapUtils = new MapUtils(map);
	
	public Campagne() {
	}

	public void run() {
		//world.move(Direction.NORTH, pj);
		//mapUtils.showStatus(pj);
		//world.move(Direction.NORTH, pj);
		//mapUtils.showStatus(pj);
		
		// TEST FENETRE
		JMap fMap = new JMap(world);
		fMap.setVisible(false);
		
		JWelcome fAccueil = new JWelcome();
		fAccueil.setVisible(false);

		JGame game = new JGame();
		game.setVisible(true);
	}
}

package io.github.algorys.agshmne.quest;

import io.github.algorys.agshmne.character.Character;
import io.github.algorys.agshmne.items.lowQuest.Letters;
import io.github.algorys.agshmne.movement.Position;
import io.github.algorys.agshmne.region.Region;

public class BringQuestTest {

	public static void main(String[] args) {
		Region region = new Region();
		Character pj = new Character(region);
		BringQuest bringQ = new BringQuest(pj, new Letters(pj.getPosition()));
		Position questDestination = bringQ.getQuestDestination();
		boolean win = bringQ.isWin(pj);
		System.out.println("Quête finie ? " + win);
		System.out.println("Inventaire = " + pj.getInventory());
		// Mouvement vers la destination
		pj.setPosition(questDestination);
		win = bringQ.isWin(pj);
		System.out.println("Quête finie ? " + win);
		// Validation de la quêtes
		bringQ.terminate(pj);
		System.out.println("Inventaire = " + pj.getInventory());

	}

}

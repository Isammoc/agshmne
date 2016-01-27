package io.github.algorys.agshmne.fight;

import io.github.algorys.agshmne.character.opponent.beast.Beast;
import io.github.algorys.agshmne.character.opponent.beast.BeastFactory;
import io.github.algorys.agshmne.character.player.Player;

public class FightSample {

	public static void main(String[] args) {
		Beast wolf = new BeastFactory().createBeast();
		Player pj = new Player(null);
		pj.getSocial().setName("Joueur");
		pj.getAttributes().setFOR(12);
		pj.getAttributes().setDEX(12);
		pj.getAttributes().setCON(11);
		pj.initVital();
		
		Fight fight = new Fight(pj, wolf);
		System.out.println(pj.toString());
		System.out.println(wolf.toString());
		fight.newRound();
		fight.newRound();
	}

}

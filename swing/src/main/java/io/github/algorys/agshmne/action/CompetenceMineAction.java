package io.github.algorys.agshmne.action;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;

import io.github.algorys.agshmne.character.Character;
import io.github.algorys.agshmne.items.craft.lowWood;
import io.github.algorys.agshmne.skills.SkillType;
import io.github.algorys.agshmne.skills.factory.Miner;

public class CompetenceMineAction extends AbstractAction implements Observer {
	private final Character pj;

	public CompetenceMineAction(Character pj) {
		super("Récolter du minerai");
		this.pj = pj;
		pj.addObserver(this);
		this.update(pj, null);
	}

	@Override
	public void actionPerformed(ActionEvent mine) {
		System.out.println("Minerai trouvé !");
		pj.getInventory().addItem(new lowWood());
	}

	@Override
	public void update(Observable o, Object arg) {
		switch (pj.getTile().getType()) {
		case Mountain:
		case Hill:
			this.setEnabled(true);
			break;
		default:
			this.setEnabled(false);
		}
	}
}

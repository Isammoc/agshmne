package io.github.algorys.agshmne.game.actions;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;

import io.github.algorys.agshmne.character.player.Player;
import io.github.algorys.agshmne.character.player.skills.SkillTool;
import io.github.algorys.agshmne.character.player.skills.SkillType;
import io.github.algorys.agshmne.items.StackableItemDirectFactory;

@SuppressWarnings("serial")
public class SkillMineAction extends AbstractAction implements Observer {
	private final Player pj;

	public SkillMineAction(Player pj) {
		super("Récolter du minerai");
		this.pj = pj;
		pj.addObserver(this);
		this.update(pj, null);
	}

	@Override
	public void actionPerformed(ActionEvent mine) {
		int mineLevel = pj.getSkills().getSkillLevel(SkillType.miner);
		if(SkillTool.Dice(mineLevel, 10)){
			System.out.println("Minerai trouvé !");
			pj.getInventory().addItem(new StackableItemDirectFactory().createCopper());
		}else {
			System.out.println("Rien d'exploitable");
		}
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

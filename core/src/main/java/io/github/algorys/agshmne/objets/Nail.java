package io.github.algorys.agshmne.objets;

import io.github.algorys.agshmne.inventory.StackableItemAdapter;

public class Nail extends StackableItemAdapter {

	@Override
	public String getName() {
		return "Clou";
	}

	@Override
	public int getWeight() {
		return 0;
	}

}
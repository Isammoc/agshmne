package io.github.algorys.agshmne.skills.factory;

import io.github.algorys.agshmne.skills.ISkillFactory;

public class Cuisiner implements ISkillFactory {
	private final int level;
	
	public Cuisiner() {
		this.level = 0;
	}
	
	@Override
	public String toString() {
		return "Cuisiner ["+level+"]";
	}

	@Override
	public int getLevel() {
		return level;
	}


}

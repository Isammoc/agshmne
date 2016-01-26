package io.github.algorys.agshmne.tempItem;

public interface IStackableItem extends Item {
	int getCount();
	
	public void addCount(int amount);
	
	public void removeCount(int amount);
}

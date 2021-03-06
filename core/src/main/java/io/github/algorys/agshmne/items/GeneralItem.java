package io.github.algorys.agshmne.items;

public class GeneralItem implements Item {
	private String name;

	public GeneralItem(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return (String) this.name;
	}
	
	@Override
	public boolean isSameType(Item other) {
		return other != null && other.getName().equals(this.name);
	}
}

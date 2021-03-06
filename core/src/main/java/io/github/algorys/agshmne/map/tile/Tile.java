package io.github.algorys.agshmne.map.tile;

import java.util.List;
import java.util.Observable;

import io.github.algorys.agshmne.items.Inventory;
import io.github.algorys.agshmne.items.Item;
import io.github.algorys.agshmne.map.city.City;

public class Tile extends Observable {
	private TileType type;
	private Inventory inv = new Inventory();
	private City city;
	private String desc;
	private boolean danger = false;

	public Tile(TileType type, String desc, City city) {
		this(type, desc);
		this.city = city;
	}

	public Tile(TileType type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public TileType getType() {
		return type;
	}

	public boolean addItem(Item e) {
		inv.addItem(e);
		boolean added = true;
		this.setChanged();
		this.notifyObservers();
		return added;
	}

	public List<Item> getItems() {
		return inv.getListBackpack();
	}

	public void removeItem(Item e) {
		this.inv.removeItem(e);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(type);
		sb.append(" (Objets : ");
		for (Item item : inv.getListBackpack()) {
			sb.append(item.getName());
			sb.append(" ");
		}
		sb.append(")");
		return sb.toString();
	}

	public City getCity() {
		return city;
	}

	public boolean isCivilized() {
		return this.city != null;
	}

	public String getDesc() {
		return this.desc;
	}

	public boolean isDanger() {
		return danger;
	}

	public void setDanger(boolean danger) {
		this.danger = danger;
	}
}

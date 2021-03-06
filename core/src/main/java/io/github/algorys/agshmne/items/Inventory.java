package io.github.algorys.agshmne.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Inventory extends Observable {
	// TODO gérer les objets de quêtes différement des objets normaux.
	private List<Item> backpack = new ArrayList<>();
	
	public Inventory() {
	}

	public void addItem(Item item) {
		boolean added = false;
		if (item instanceof IStackableItem) {
			IStackableItem stackToAdd = (IStackableItem) item;
			for (Item inBag : backpack) {
				if (inBag.isSameType(item) && inBag instanceof IStackableItem) {
					IStackableItem stack = (IStackableItem) inBag;
					stack.addCount(stackToAdd.getCount());
					added = true;
					this.setChanged();
					break;
				}
			}
		}
		if (!added) {
			backpack.add(item);
			this.setChanged();
		}
		this.notifyObservers();
	}

	public void removeItem(Item itemToRemove) {
		boolean removed = false;
		if (itemToRemove instanceof IStackableItem) {
			Iterator<Item> iter = backpack.iterator();
			while (iter.hasNext()) {
				Item itemInBag = iter.next();
				IStackableItem stackItemToRemove = (IStackableItem) itemToRemove;
				if (itemInBag.isSameType(itemToRemove)) {
					IStackableItem stackableItemInBag = ((IStackableItem) itemInBag);
					if(stackableItemInBag.getCount() > stackItemToRemove.getCount()) {
						stackableItemInBag.removeCount(stackItemToRemove.getCount());
					} else {
						iter.remove();
					}
					removed = true;
					this.setChanged();
					break;
				}
			}
		}
		if (!removed) {
			Iterator<Item> iter = backpack.iterator();
			while (iter.hasNext()) {
				if (iter.next().isSameType(itemToRemove)) {
					iter.remove();
					this.setChanged();
					break;
				}
			}
		}
		this.notifyObservers();
	}

	public boolean contains(Item item) {
		for (Item inBag : backpack) {
			if (inBag.isSameType(item)) {
				return true;
			}
		}
		return false;
	}

	public int count(Item item) {
		int res = 0;
		for (Item inBag : backpack) {
			if (inBag.isSameType(item)) {
				if(inBag instanceof IStackableItem) {
					IStackableItem stackableItem = (IStackableItem)inBag;
					res += stackableItem.getCount();
				} else {
					res++;
				}
			}
		}
		return res;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("--- Sac-à-dos ---");
		sb.append("\n");
		for (int i = 0; i < backpack.size(); i++) {
			if(backpack.get(i) instanceof StackableItem) {
				IStackableItem stackableItem = (IStackableItem)backpack.get(i);
				sb.append(""+ stackableItem.getName() + ", " + stackableItem.getCount());
			} else {
				sb.append(""+ backpack.get(i).getName());
			}
			sb.append("\n");
		}
		sb.append("------------\n");
		return sb.toString();
	}

	public List<Item> getListBackpack() {
		return backpack;
	}

}
package io.github.algorys.agshmne.tempItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import io.github.algorys.agshmne.inventory.InventoryItem;
import io.github.algorys.agshmne.inventory.StackableItem;

public class InventoryT extends Observable {
	// TODO gérer les objets de quêtes différement des objets normaux.
	public final static int MAX_INVENTORY = 10;
	private List<Item> backpack = new ArrayList<>();
	
	public InventoryT() {
	}

	public void addItem(Item item) {
		boolean added = false;
		if (item instanceof GeneralStackableItem) {
			for (int i = 0; i < backpack.size(); i++) {
				if (backpack.get(i).getName() == item.getName()) {
					IStackableItem stack = (IStackableItem) item;
					stack.addCount(stack.getCount());
					added = true;
					this.setChanged();
					break;
				}
			}
		}
		if (!added) {
			backpack.add(item);
		}
		this.notifyObservers();
	}

	public void removeItem(InventoryItem item) {
		boolean removed = false;
		if (item instanceof GeneralStackableItem) {
			IStackableItem stackItem = (IStackableItem) item;
			for (int i = 0; i < backpack.size(); i++) {
				if (((GeneralStackableItem) item).isSameType((IStackableItem)backpack.get(i))) {
					IStackableItem stack = (IStackableItem) backpack.get(i);
					stack.removeCount(stack.getCount());
					if(stack.getCount() == 0){
						backpack.remove(i); // TODO Si non gestion des emplacements (mais du coup, pas forcément un tableau)
					}
					removed = true;
					this.setChanged();
					break;
				}
			}
		}
		if (!removed) {
			for (int i = 0; i < MAX_INVENTORY; i++) {
				if (backpack.get(i) == item) {
					backpack.remove(i);
					this.setChanged();
					break;
				}
			}
		}
		this.notifyObservers();
	}

	public boolean contains(Class<?> type) {
		for (int i = 0; i < backpack.size(); i++) {
			if (type.isInstance(backpack.get(i))) {
				return true;
			}
		}
		return false;
	}

	public int count(Class<?> type) {
		int res = 0;
		for (int i = 0; i < backpack.size(); i++) {
			if (type.isInstance(backpack.get(i))) {
				if(backpack.get(i) instanceof GeneralStackableItem) {
					IStackableItem stackableItem = (IStackableItem)backpack.get(i);
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
			if(backpack.get(i) instanceof GeneralStackableItem) {
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
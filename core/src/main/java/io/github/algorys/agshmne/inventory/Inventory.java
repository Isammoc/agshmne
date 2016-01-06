package io.github.algorys.agshmne.inventory;

public class Inventory {
	public final static int MAX_INVENTORY = 10;

	private InventoryItem[] backpack = new InventoryItem[MAX_INVENTORY];

	public Inventory() {
	}

	public void addItem(InventoryItem item) {
		boolean added = false;
		if (item instanceof StackableItem) {
			StackableItem stackItem = (StackableItem) item;
			for (int i = 0; i < MAX_INVENTORY; i++) {
				if (backpack[i] != null && item.getClass() == backpack[i].getClass()) {
					StackableItem stack = (StackableItem) backpack[i];
					stack.addCount(stackItem.getCount());
					added = true;
					break;
				}
			}
		}
		if (!added) {
			for (int i = 0; i < MAX_INVENTORY; i++) {
				if (backpack[i] == null) {
					backpack[i] = item;
					break;
				}
			}
		}
	}

	public void addItem(InventoryItem item, int index) {
		if (backpack[index] != null) {
			throw new IllegalArgumentException("Already an item at this index");
		}
		backpack[index] = item;
	}

	public void removeItem(InventoryItem item) {
		for (int i = 0; i < MAX_INVENTORY; i++) {
			if (backpack[i] == item) {
				backpack[i] = null;
				break;
			}
		}
	}

	public boolean contains(Class<?> type) {
		for (int i = 0; i < MAX_INVENTORY; i++) {
			if (type.isInstance(backpack[i])) {
				return true;
			}
		}
		return false;
	}

	public int count(Class<?> type) {
		int res = 0;
		for (int i = 0; i < MAX_INVENTORY; i++) {
			if (type.isInstance(backpack[i])) {
				if(backpack[i] instanceof StackableItem) {
					StackableItem stackableItem = (StackableItem)backpack[i];
					res += stackableItem.getCount();
				} else {
					res++;
				}
			}
		}
		return res;
	}
}

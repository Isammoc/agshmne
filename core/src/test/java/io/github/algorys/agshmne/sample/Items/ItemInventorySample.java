package io.github.algorys.agshmne.sample.Items;

import io.github.algorys.agshmne.items.GeneralItem;
import io.github.algorys.agshmne.items.Inventory;
import io.github.algorys.agshmne.items.StackableItem;
import io.github.algorys.agshmne.items.Item;
import io.github.algorys.agshmne.items.ItemDirectFactory;
import io.github.algorys.agshmne.items.StackableItemDirectFactory;;

public class ItemInventorySample {

	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		StackableItemDirectFactory stackFact = new StackableItemDirectFactory();
		
		// Ajout une première Pomme
		Item apple = stackFact.createApple();		
		inventory.addItem(apple);
		System.out.println(inventory.toString());
		// Ajout une deuxième Pomme
		apple = stackFact.createApple();
		inventory.addItem(apple);
		System.out.println(inventory.toString());
		System.out.println("Inventaire contient " + inventory.count(apple) + " Pommes !");
		
		// Ajout d'un objet simple
		ItemDirectFactory itemFact = new ItemDirectFactory();
		Item axe = itemFact.createAxe();		
		inventory.addItem(axe);
		System.out.println(inventory.toString());
		
		// Vérification si l'objet existe 
		Item orange = stackFact.createOrange();
		Item sword = itemFact.createSword();
		System.out.println(inventory.contains(axe));
		System.out.println(inventory.contains(apple));
		System.out.println(inventory.contains(orange));
		System.out.println(inventory.contains(sword));
		System.out.println("Inventaire contient " + inventory.count(orange) + " Oranges !");
		
		// Test pour Ajout GeneralStackable (sans Direct)
		Item pear = new StackableItem("Pear", 1);
		inventory.addItem(pear);
		pear = new StackableItem("Pear", 1);
		inventory.addItem(pear);
		Item armor = new GeneralItem("Armure matelassée");
		inventory.addItem(armor);
		System.out.println(inventory.toString());
		
		// Suppression d'un Stackable
		inventory.removeItem(apple);
		System.out.println(inventory.toString());
		inventory.removeItem(apple);
		System.out.println(inventory.toString());
		inventory.removeItem(pear);
		System.out.println(inventory.toString());
		inventory.removeItem(armor);
		inventory.removeItem(axe);
		System.out.println(inventory.toString());



	}

}

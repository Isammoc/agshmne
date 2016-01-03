package io.github.algorys.agshmne.personnage;

import java.util.Observable;

import io.github.algorys.agshmne.deplacement.Position;
import io.github.algorys.agshmne.region.Region;

/*
 * Définit le Personnage
 */
public class Personnage extends Observable {
	private Position position = new Position(0, 0);
	private Region region;
	

	public Personnage(Region region) {
		this.region = region;
		System.out.println("------- Création Personnage --------");
	}

	public Position getPosition() {
		return position;
	}

	public Region getRegion() {
		return this.region;
	}
	
	public void setPosition(Position position) {
		//System.out.println("Nouvelle position => " + position.getX() + ", " + position.getY());
		//System.out.println("Sur case : " + region.getTileFromPosition(position));
		this.position = position;
		this.setChanged();
		this.notifyObservers();
	}
}


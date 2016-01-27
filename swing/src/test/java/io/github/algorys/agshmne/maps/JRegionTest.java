package io.github.algorys.agshmne.maps;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import io.github.algorys.agshmne.character.Character;
import io.github.algorys.agshmne.map.Position;
import io.github.algorys.agshmne.map.region.Region;
import io.github.algorys.agshmne.region.JRegion;

public class JRegionTest {
	public static void main(String[] args) {
		JFrame jf = new JFrame("JRegion test");
		jf.setBackground(Color.BLACK);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Region region = new Region();
		final Character personnage = new Character(region);
		jf.getContentPane().add(new JRegion(personnage));
		System.out.println("-------------------------------------");
		jf.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("Touché!");
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					personnage.setPosition(
							new Position(personnage.getPosition().getX() - 1, personnage.getPosition().getY()));
					System.out.println("-------------------------------------");
					break;
				case KeyEvent.VK_RIGHT:
					personnage.setPosition(
							new Position(personnage.getPosition().getX() + 1, personnage.getPosition().getY()));
					System.out.println("-------------------------------------");
					break;
				case KeyEvent.VK_UP:
					personnage.setPosition(
							new Position(personnage.getPosition().getX(), personnage.getPosition().getY() + 1));
					System.out.println("-------------------------------------");
					break;
				case KeyEvent.VK_DOWN:
					personnage.setPosition(
							new Position(personnage.getPosition().getX(), personnage.getPosition().getY() - 1));
					System.out.println("-------------------------------------");
					break;
				}

			}
		});

		jf.setSize(jf.getPreferredSize());
		jf.setVisible(true);
	}
}

package io.github.algorys.agshmne.character.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

import io.github.algorys.agshmne.map.Position;

public class PlayerTest {

	private final class MyObserver implements Observer {
		private int called = 0;

		@Override
		public void update(Observable o, Object arg) {
			called++;
		}

		public boolean isCalled() {
			return called > 0;
		}
	}

	@Test
	public void whenCharacterIsCreated_positionShouldBe00() {
		Player underTest = new Player(null);
		assertEquals(new Position(0, 0), underTest.getPosition());
	}

	@Test
	public void whenCharacterChangePosition_observersShouldBeNotified() {
		Player underTest = new Player(null);
		MyObserver myObserver = new MyObserver();
		underTest.addObserver(myObserver);
		underTest.setPosition(new Position(1, 0));
		assertTrue(myObserver.isCalled());
	}

	@Test
	public void whenCharacterSetSamePosition_observersShouldNotBeNotified() {
		Player underTest = new Player(null);
		MyObserver myObserver = new MyObserver();
		underTest.addObserver(myObserver);
		underTest.setPosition(new Position(0, 0));
		assertFalse(myObserver.isCalled());
	}

}

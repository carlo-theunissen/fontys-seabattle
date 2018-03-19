package game;

import models.Hit;
import models.Player;

import java.util.Collection;

public class Grid {

	private models.Player Player;
	private int Width;
	private int Height;
	private Collection<Hit> Hits;

	public Grid() {
	}

	public Grid(Grid copy){
		Player = copy.Player;
		Width = copy.Width;
		Height = copy.Height;
		Hits = copy.Hits;
	}

	public Player getPlayer() {
		return Player;
	}

	public void setWidth(int value) {
		Width = value;
	}

	public int getWidth() {
		return Width;
	}

	public void setHeight(int value) {
		Height = value;
	}

	public int getHeight() {
		return Height;
	}

	public Collection getHits() {
		return Hits;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void FireShot(int x, int y) {
		// TODO - implement Grid.FireShot
		throw new UnsupportedOperationException();
	}

}
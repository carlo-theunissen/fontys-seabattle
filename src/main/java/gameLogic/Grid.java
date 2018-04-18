package gameLogic;

import models.Hit;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class Grid {

	private int Width;
	private int Height;
	private Collection<Hit> Hits;

	public Grid() {
		Hits = new HashSet<Hit>();
	}

	public Grid(Grid copy){
		Width = copy.Width;
		Height = copy.Height;
		Hits = copy.Hits != null ? copy.Hits : new HashSet<Hit>();
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

	public Collection<Hit> getHits() {
		return Collections.unmodifiableCollection(Hits);
	}

	public void AddHit(Hit hit) {
		Hits.add(hit);
	}

}
package models;

public class Hit {
	private int x;
	private int y;
	private HitType type;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public HitType getHitType() {
		return type;
	}

	public Hit(int x, int y, HitType type){
		this.x = x;
		this.y = y;
		this.type = type;

	}
}
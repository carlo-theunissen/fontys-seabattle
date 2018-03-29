package models;

public class Hit {
	private final int x;
	private final int y;
	private final HitType type;
	private boolean sunk;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public HitType getHitType() {
		return type;
	}


	public Hit(int x, int y, HitType type, boolean sunk){
		this.x = x;
		this.y = y;
		this.type = type;
		this.sunk = sunk;
	}
	public Hit(int x, int y, HitType type){
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public static String serialize(Hit hit){
		return hit.getX() +":"+ hit.getY() + ":" + (hit.getHitType() == HitType.Collided ? "C" : "S")+ ":" + (hit.sunk ? 1 : 0);
	}
	public static Hit unserialize(String data){
		String[] split = data.split(":");
		return new Hit(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2].equals("C") ? HitType.Collided : HitType.Miss, split[3].equals("1"));
	}

    public boolean getSunk() {
        return sunk;
    }

	public void setSunk(boolean sunk) {
		this.sunk = sunk;
	}

	public boolean softEquals(Object obj) {
		if(!(obj instanceof Hit)){
			return false;
		}
		Hit hit = (Hit) obj;
		return hit.getX() == getX() && hit.getY() == getY();
	}
}
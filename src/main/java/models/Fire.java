package models;

public class Fire {
    private int x;
    private int y;
    public Fire(int x, int y){
        if(x < 0 || y < 0){
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

package org.prateek.CreationalPatterns.PrototypePattern.Solution;

public class GamePiece implements Prototype<GamePiece>{
    public String getColor() {
        return color;
    }

    public int getPosition() {
        return position;
    }

    private String color;
    private int position;

    public void setColor(String color) {
        this.color = color;
    }

    public void setPosition(int position) {
        this.position = position;
    }



    public GamePiece(String color,int position){
        this.color = color;
        this.position = position;
    }

    @Override
    public String toString() {
        return "GamePiece{" +
                "color='" + color + '\'' +
                ", position=" + position +
                '}';
    }

    @Override
    public GamePiece clone() {
        return new GamePiece(this.color,this.position);
    }
}

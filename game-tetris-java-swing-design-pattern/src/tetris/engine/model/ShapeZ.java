package tetris.engine.model;

public class ShapeZ extends Shape{

	public ShapeZ(InGame inGame, int color) {
		super(inGame, color);
		maxSize = 2;
		blocks.get(0).setLocal(4, -1);
		blocks.get(1).setLocal(5, -1);
		blocks.get(2).setLocal(5, -2);
		blocks.get(3).setLocal(6, -2);
		x = 4;
		y = -2;
	}

}

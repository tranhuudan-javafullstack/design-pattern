package tetris.engine.algorithm.decorator;

import tetris.engine.model.InGame;
import tetris.engine.model.Shape;

public class ShapeDecorator extends Shape {

	private Shape shape;

	public ShapeDecorator(InGame inGame) {
		super(inGame, 7);

	}

	public Shape get(Shape shape) {
		this.shape = shape;
		shape.setColor(7);

		return shape;

	}

}

package tetris.engine.algorithm.factory;

import tetris.engine.model.InGame;
import tetris.engine.model.Shape;
import tetris.engine.model.ShapeI;

public class ShapeIFactory extends ShapeFactory {

	@Override
	public Shape createShape(InGame tetris, int index) {
		// TODO Auto-generated method stub
		return new ShapeI(tetris, index);
	}

}

package tetris.engine.algorithm.factory;

import tetris.engine.model.InGame;
import tetris.engine.model.Shape;
import tetris.engine.model.ShapeS;

public class ShapeSFactory extends ShapeFactory {

	@Override
	public Shape createShape(InGame tetris, int index) {
		// TODO Auto-generated method stub
		return new ShapeS(tetris, index);
	}

}

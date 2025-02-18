package tetris.engine.algorithm.factory;

import tetris.engine.model.InGame;
import tetris.engine.model.Shape;
import tetris.engine.model.ShapeT;

public class ShapeTFactory extends ShapeFactory {

	@Override
	public Shape createShape(InGame tetris, int index) {
		// TODO Auto-generated method stub
		return new ShapeT(tetris, index);
	}

}

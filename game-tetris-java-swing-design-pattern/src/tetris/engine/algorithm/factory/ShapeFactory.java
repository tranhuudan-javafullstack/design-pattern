package tetris.engine.algorithm.factory;

import tetris.engine.model.Block;
import tetris.engine.model.InGame;
import tetris.engine.model.Shape;

import java.util.ArrayList;
import java.util.List;

public abstract class ShapeFactory {
	public abstract Shape createShape(InGame tetris, int index);

	public List<Block> createNextShapeBlocks(Shape shape) {
		List<Block> nextShapeBlocks = new ArrayList<Block>();
		for (Block block : shape.getBlocks()) {
			nextShapeBlocks.add(
					new Block(block.getColor(), block.getX() - shape.getX() + 11, block.getY() - shape.getY() + 2));
		}
		return nextShapeBlocks;
	}

	public static ShapeFactory getFactory(int type) {
		if (type == 0) {
			return new ShapeOFactory();
		} else if (type == 1) {
			return new ShapeTFactory();
		} else if (type == 2) {
			return new ShapeLFactory();
		} else if (type == 3) {
			return new ShapeJFactory();
		} else if (type == 4) {
			return new ShapeSFactory();
		} else if (type == 5) {
			return new ShapeZFactory();
		} else if (type == 6) {
			return new ShapeIFactory();
		}
		return null;
	}
}

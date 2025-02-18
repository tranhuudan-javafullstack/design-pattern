package tetris.engine.algorithm.strategy;

import tetris.engine.model.Block;
import tetris.engine.model.Shape;

import java.util.ArrayList;

public class RotateActionStrategy implements ActionStrategy {

	@Override
	public void action(Shape shape) {

		ArrayList<Block> tmpBlocks = new ArrayList<Block>();
		for (Block block : shape.getBlocks()) {
			tmpBlocks.add(new Block(block.getColor(), block.getX() - shape.getX(), block.getY() - shape.getY()));
		}
		for (Block block : tmpBlocks) {
			block.setLocal(block.getY(), block.getX());
			block.setLocal(block.getX(), shape.getMaxSize() - block.getY());
			block.setLocal(block.getX() + shape.getX(), block.getY() + shape.getY());
		}
		boolean flag = true;
		int tmp = 0;
		for (Block block : tmpBlocks) {
			int d = 0;
			if (block.getY() >= shape.getInGame().getBoard().getHeigth()) {
				flag = false;
				break;
			}
			if (block.getX() < 0) {
				d = 0 - block.getX();
			}
			if (block.getX() >= shape.getInGame().getBoard().getWidth()) {
				d = shape.getInGame().getBoard().getWidth() - block.getX() - 1;
			}
			if (Math.abs(d) > Math.abs(tmp)) {
				tmp = d;
			}
		}
		for (Block block : tmpBlocks) {
			block.setLocal(block.getX() + tmp, block.getY());
		}
		shape.setX(shape.getX() + tmp);
		if (shape.getColor() != 7) {
			for (Block block : tmpBlocks) {
				for (Block block2 : shape.getInGame().getBoard().getBlocks()) {
					if (block.getX() == block2.getX() && block.getY() == block2.getY()) {
						flag = false;
						break;
					}
				}
			}
		}
		if (flag) {
			shape.setBlocks(tmpBlocks);
		}

	}

}

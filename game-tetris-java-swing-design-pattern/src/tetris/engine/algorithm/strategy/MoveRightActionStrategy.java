package tetris.engine.algorithm.strategy;

import tetris.engine.model.Block;
import tetris.engine.model.Shape;

import java.util.ArrayList;

public class MoveRightActionStrategy implements ActionStrategy {

	@Override
	public void action(Shape shape) {

		ArrayList<Block> tmpBlocks = new ArrayList<Block>();
		for (Block block : shape.getBlocks()) {
			tmpBlocks.add(new Block(block.getColor(), block.getX(), block.getY()));
		}
		for (Block block : tmpBlocks) {
			block.setLocal(block.getX() + 1, block.getY());
		}
		boolean flag = true;
		for (Block block : tmpBlocks) {
			if (block.getX() < 0 || block.getX() >= shape.getInGame().getBoard().getWidth()) {
				flag = false;
				break;
			}
			if (shape.getColor() != 7) {
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
			shape.setX(shape.getX() + 1);
		}

	}

}

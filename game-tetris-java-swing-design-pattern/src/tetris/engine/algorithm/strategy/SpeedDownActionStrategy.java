package tetris.engine.algorithm.strategy;

import tetris.engine.model.Shape;

public class SpeedDownActionStrategy implements ActionStrategy {

	@Override
	public void action(Shape shape) {
		shape.setDelay(600 - (int) (600 * 0.1 * (shape.getInGame().getLevel() - 1)));
	}

}

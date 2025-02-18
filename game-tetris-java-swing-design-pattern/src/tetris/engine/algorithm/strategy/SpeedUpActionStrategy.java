package tetris.engine.algorithm.strategy;

import tetris.engine.model.Shape;

public class SpeedUpActionStrategy implements ActionStrategy {

    @Override
    public void action(Shape shape) {
        shape.setDelay(50);
    }

}

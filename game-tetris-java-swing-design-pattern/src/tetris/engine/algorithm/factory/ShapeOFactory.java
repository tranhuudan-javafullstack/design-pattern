package tetris.engine.algorithm.factory;

import tetris.engine.model.InGame;
import tetris.engine.model.Shape;
import tetris.engine.model.ShapeO;

public class ShapeOFactory extends ShapeFactory {
    @Override
    public Shape createShape(InGame tetris, int index) {
        return new ShapeO(tetris, index);
    }

}

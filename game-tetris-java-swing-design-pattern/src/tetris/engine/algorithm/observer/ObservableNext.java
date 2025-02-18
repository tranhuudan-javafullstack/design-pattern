package tetris.engine.algorithm.observer;

import tetris.engine.model.Block;
import tetris.engine.model.Shape;

import java.util.List;

public interface ObservableNext {
    void updateNext(List<Block> list, Shape n);

}

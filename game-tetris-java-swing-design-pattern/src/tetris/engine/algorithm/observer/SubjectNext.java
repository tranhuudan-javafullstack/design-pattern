package tetris.engine.algorithm.observer;

import tetris.engine.model.Block;
import tetris.engine.model.Shape;

import java.util.List;

public interface SubjectNext {
    void add(ObservableNext o);

    void remove(ObservableNext o);


    void noti(List<Block> list, Shape n);
}

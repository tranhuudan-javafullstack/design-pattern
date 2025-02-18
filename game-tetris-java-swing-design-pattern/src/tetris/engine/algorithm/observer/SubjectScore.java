package tetris.engine.algorithm.observer;

public interface SubjectScore {
    void add(ObservableScore o);

    void remove(ObservableScore o);

    void noti(int score, int line);

}

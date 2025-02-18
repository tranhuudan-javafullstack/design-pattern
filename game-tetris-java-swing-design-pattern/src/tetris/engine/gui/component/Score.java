package tetris.engine.gui.component;

import tetris.engine.Game;
import tetris.engine.algorithm.observer.ObservableScore;
import tetris.engine.gui.screen.Screen;

import java.awt.*;

public class Score extends Screen implements ObservableScore {

    private int score;
    private int line;

    public Score(Game game) {
        super(game);
        score = 0;
        line = 0;
    }

    @Override
    public void update() {

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("Score " + score, game.getWidth() - 125, 280);
        g.drawString("Line " + line, game.getWidth() - 125, 350);
    }

    @Override
    public void updateScore(int score, int line) {
        this.score += score;
        this.line += line;
    }
}

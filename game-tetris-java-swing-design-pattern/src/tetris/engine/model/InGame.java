package tetris.engine.model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import tetris.engine.algorithm.decorator.ShapeDecorator;
import tetris.engine.algorithm.factory.ShapeFactory;
import tetris.engine.algorithm.observer.ObservableNext;
import tetris.engine.algorithm.observer.ObservableScore;
import tetris.engine.algorithm.observer.SubjectNext;
import tetris.engine.algorithm.observer.SubjectScore;
import tetris.engine.algorithm.strategy.*;
import tetris.engine.gui.component.Next;
import tetris.engine.gui.component.Score;
import tetris.engine.gui.screen.*;

public class InGame implements SubjectScore, SubjectNext {
	private Board board;
	private Shape currentShape, nextShape;
	private final int blockSize = 30;
	private boolean gameOver = false;
	private int level;
	private long count;
	private List<ObservableScore> observerables;
	private List<ObservableNext> observerables2;

	public Shape getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(Shape currentShape) {
		this.currentShape = currentShape;
	}

	public Shape getNextShape() {
		return nextShape;
	}

	public void setNextShape(Shape nextShape) {
		this.nextShape = nextShape;
	}

	public InGame(Score sc, Next n, int level) {
		observerables = new ArrayList<>();
		observerables2 = new ArrayList<>();

		this.level = level;

		count = 0;
		board = new Board();

		add(sc);
		add(n);

		setNextShape();
		setCurrentShape();
	}

	public void update() {

		if (currentShape.isCollision()) {
			board.setShapeToBoard(currentShape);
			int ch = board.checkLine();
			if (ch != 0) {
				noti(ch * (8 + level * 2), ch);
			}

			checkGameOver();
			if (!gameOver)
				setCurrentShape();
		} else
			currentShape.update();
//		keyUpdate();
	}

	public void paint(Graphics g) {
		board.paint(g);
		currentShape.paint(g);
	}

	public void Rotate() {
		currentShape.setActionStrategy(new RotateActionStrategy());
		currentShape.action();
	}

	public void SpeedUp() {
		currentShape.setActionStrategy(new SpeedUpActionStrategy());
		currentShape.action();
	}

	public void speedDown() {
		currentShape.setActionStrategy(new SpeedDownActionStrategy());
		currentShape.action();
	}

	public void right() {
		currentShape.setActionStrategy(new MoveRightActionStrategy());
		currentShape.action();
	}

	public void left() {
		currentShape.setActionStrategy(new MoveLeftActionStrategy());
		currentShape.action();
	}

	private void checkGameOver() {
		for (Block block : board.getBlocks()) {
			if (block.getY() == 0) {
				gameOver = true;
				break;
			}
		}
	}

	private void setCurrentShape() {
		currentShape = nextShape;
		currentShape.setLastTime(System.currentTimeMillis());
		setNextShape();
	}

	private void setNextShape() {
		int index = (int) (Math.random() * 7);
		ShapeFactory factory = ShapeFactory.getFactory(index);
		if (factory != null) {
			nextShape = factory.createShape(this, index);

			count++;
			if (count % 10 == 0) {
				nextShape = new ShapeDecorator(this).get(nextShape);
			}
			noti(factory.createNextShapeBlocks(nextShape), nextShape);
		}
	}

	public Board getBoard() {
		return board;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public void add(ObservableScore o) {
		// TODO Auto-generated method stub
		observerables.add(o);
	}

	@Override
	public void remove(ObservableScore o) {
		// TODO Auto-generated method stub
		for (ObservableScore ob : observerables) {
			if (ob.equals(o))
				observerables.remove(ob);
			break;

		}
	}

	@Override
	public void noti(int score, int line) {
		for (ObservableScore ob : observerables) {
			ob.updateScore(score, line);
		}
	}

	@Override
	public void add(ObservableNext o) {
		observerables2.add(o);
	}

	@Override
	public void remove(ObservableNext o) {
		for (ObservableNext ob : observerables2) {
			if (ob.equals(o))
				observerables2.remove(ob);
			break;

		}
	}

	@Override
	public void noti(List<Block> list, Shape n) {
		for (ObservableNext ob : observerables2) {
			ob.updateNext(list, n);
		}
	}

}

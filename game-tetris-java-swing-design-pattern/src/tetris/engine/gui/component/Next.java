package tetris.engine.gui.component;

import tetris.engine.Game;
import tetris.engine.model.Block;
import tetris.engine.algorithm.observer.ObservableNext;
import tetris.engine.gui.screen.Screen;
import tetris.engine.model.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Next extends Screen implements ObservableNext {

	private List<Block> nextShapeBlocks;
	private Shape nextShape;

	public Next(Game game) {
		super(game);
		nextShape = new Shape();
		nextShapeBlocks = new ArrayList<>();
	}

	@Override
	public void update() {

	}

	public List<Block> getNextShapeBlocks() {
		return nextShapeBlocks;
	}

	public void setNextShapeBlocks(List<Block> nextShapeBlocks) {
		this.nextShapeBlocks = nextShapeBlocks;
	}

	public Shape getNextShape() {
		return nextShape;
	}

	public void setNextShape(Shape nextShape) {
		this.nextShape = nextShape;
	}

	@Override
	public void paint(Graphics g) {
		if (nextShape.getColor() == 7) {
			for (Block block : nextShapeBlocks) {
				block.setColor((int) (Math.random() * 7));
			}
		}
		for (Block block : nextShapeBlocks) {
			block.paint(g);
		}

	}

	@Override
	public void updateNext(List<Block> list, Shape n) {
		nextShapeBlocks = list;
		nextShape = n;
	}
}

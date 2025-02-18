package tetris.engine.model;

import tetris.engine.algorithm.composite.Draw;
import tetris.engine.algorithm.strategy.ActionStrategy;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Shape implements Draw {
	private InGame inGame;
	protected List<Block> blocks = new ArrayList<Block>();
	protected int color;
	protected int x, y;
	protected int maxSize;

	protected int normal = 600, fast = 50;
	protected int delay;

	protected long now, lastTime;

	protected boolean collision = false;

	protected ActionStrategy actionStrategy;

	public Shape() {
	}

	public ActionStrategy getActionStrategy() {
		return actionStrategy;
	}

	public void setActionStrategy(ActionStrategy actionStrategy) {
		this.actionStrategy = actionStrategy;
	}

	public Shape(InGame inGame, int color) {
		this.color = color;
		this.setInGame(inGame);
		for (int i = 0; i < 4; i++) {
			blocks.add(new Block(color));
		}
		normal = 600 - (int) (600 * 0.1 * (inGame.getLevel() - 1));
		delay = normal;
		now = System.currentTimeMillis();
		lastTime = now;
	}

	public Shape(InGame inGame, int color, ArrayList<Block> blocks) {
		this(inGame, color);
		this.blocks = blocks;
	}

	@Override
	public void update() {
		now = System.currentTimeMillis();
		for (Block block : blocks) {
			if ((block.getY() >= getInGame().getBoard().getHeigth() - 1)) {
				collision = true;
				break;
			}
			if (color != 7) {
				for (Block block2 : getInGame().getBoard().getBlocks()) {
					if (block.getX() == block2.getX() && block.getY() + 1 == block2.getY()) {
						collision = true;
						break;
					}
				}
			}
		}
		if (now - lastTime > delay && !collision) {
			lastTime = now;
			y++;
			for (Block block : blocks) {
				block.setLocal(block.getX(), block.getY() + 1);
			}
		}
		if (color == 7) {
			for (Block block : blocks) {
				block.setColor((int) (Math.random() * 7));
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		for (Block block : blocks) {
			block.paint(g);
		}
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getColor() {
		return color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public boolean isCollision() {
		return collision;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public InGame getInGame() {
		return inGame;
	}

	public void setInGame(InGame inGame) {
		this.inGame = inGame;
	}

	public void action() {
		actionStrategy.action(this);
	}
}
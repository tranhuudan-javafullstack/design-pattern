package tetris.engine.gui.screen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import tetris.engine.FileLoader;
import tetris.engine.Game;
import tetris.engine.algorithm.strategy.MoveLeftActionStrategy;
import tetris.engine.algorithm.strategy.MoveRightActionStrategy;
import tetris.engine.algorithm.strategy.RotateActionStrategy;
import tetris.engine.algorithm.strategy.SpeedDownActionStrategy;
import tetris.engine.algorithm.strategy.SpeedUpActionStrategy;
import tetris.engine.controller.MyButton;
import tetris.engine.gui.component.Next;
import tetris.engine.gui.component.Score;
import tetris.engine.model.InGame;

public class GameScr extends Screen {

	private BufferedImage backGround;
	private BufferedImage gameOver;
	private BufferedImage backToMenu;
	private BufferedImage pause;
	private BufferedImage playAgainst;
	private InGame inGame;
	private ArrayList<MyButton> buttons = new ArrayList<MyButton>();
	private MyButton btnBackToMenu;
	private MyButton btnPause;
	private Score score;
	private Next next;
	private Game game;
	private boolean isPause = false;

	public Score getScore() {
		return score;
	}

	public Next getNext() {
		return next;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	private int level = 1;

	public GameScr(Game game) {
		super(game);
		this.game = game;
		score = new Score(game);
		next = new Next(game);
		inGame = new InGame(score, next, level);
		backGround = FileLoader.loadImage("/img_2.png");
		gameOver = FileLoader.loadImage("/img_1.png");
		backToMenu = FileLoader.loadImage("/backToMenu.png");
		pause = FileLoader.loadImage("/pauseAndPlay.png");
		playAgainst = FileLoader.loadImage("/playagainst.png");

		btnBackToMenu = new MyButton(game, backToMenu, 355, 520, 40, 40);
		btnPause = new MyButton(game, pause.getSubimage(0, 0, 512, 512), 355, 420, 40, 40);

		buttons.add(btnBackToMenu);
		buttons.add(btnPause);
	}

	@Override
	public void update() {
		if (!inGame.isGameOver() && !isPause) {
			inGame.update();
		}
		if (btnPause.isMouseUp()) {
			if (!inGame.isGameOver()) {
				isPause = !isPause;
				btnPause.setImage(pause.getSubimage(512 * (isPause ? 1 : 0), 0, 512, 512));
			} else {
				setNewGame(level);
			}
		}
		if (btnBackToMenu.isMouseUp()) {
			game.getWindow().setScreen(Window.Screen.Menu);
		}

		keyStrategy();
	}

	private void keyStrategy() {
		if (game.getInput().isKeyDown(KeyEvent.VK_UP)) {
			inGame.Rotate();
		}
		if (game.getInput().isKeyDown(KeyEvent.VK_DOWN)) {
			inGame.SpeedUp();
		}
		if (game.getInput().isKeyUp(KeyEvent.VK_DOWN)) {
			inGame.speedDown();
		}
		if (game.getInput().isKeyHold(KeyEvent.VK_RIGHT)) {
			inGame.right();
		}
		if (game.getInput().isKeyHold(KeyEvent.VK_LEFT)) {
			inGame.left();
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(backGround, 0, 0, game.getWindow().getCanvas().getWidth(), game.getWindow().getCanvas().getHeight(),
				null);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(new Color(0, 0, 0, 100));
		for (int i = 0; i <= inGame.getBoard().getHeigth(); i++) {
			g2d.drawLine(0, i * inGame.getBlockSize(), inGame.getBoard().getWidth() * inGame.getBlockSize(),
					i * inGame.getBlockSize());
		}
		for (int j = 0; j <= inGame.getBoard().getWidth(); j++) {
			g2d.drawLine(j * inGame.getBlockSize(), 0, j * inGame.getBlockSize(),
					inGame.getBoard().getHeigth() * inGame.getBlockSize());
		}
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		g.drawString("Level  " + level, game.getWidth() - 125, 200);

		inGame.paint(g);
		score.paint(g);
		next.paint(g);

		if (inGame.isGameOver()) {
			btnPause.setImage(playAgainst);
		} else {
			btnPause.setImage(pause.getSubimage(512 * (isPause ? 1 : 0), 0, 512, 512));
		}
		if (buttons != null) {
			for (MyButton button : buttons) {
				button.paint(g);
			}
		}

		if (inGame.isGameOver()) {
			g.drawImage(gameOver, 20, 150, 267, 200, null);
		}

	}

	// khi chơi lại hoặc
	public void setNewGame(int level) {
		this.level = level;
		this.isPause = false;
		inGame = new InGame(score, next, level);
		score.setLine(0);
		score.setScore(0);
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

}
package tetris.engine.gui.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import tetris.engine.FileLoader;
import tetris.engine.Game;
import tetris.engine.controller.MyButton;

public class MenuScr extends Screen {

	private BufferedImage backGround;
	private ArrayList<MyButton> buttons = new ArrayList<MyButton>();
	private MyButton btnNewGame;
	private MyButton btnQuit;
	private MyButton btnPlay;
	private MyButton btnCancel;
	private MyButton btnLeft;
	private MyButton btnRight;

	private int level = 1;

	private boolean isSelectLv = false;

	public MenuScr(Game game) {
		super(game);
		backGround = FileLoader.loadImage("/img_3.png");
		btnNewGame = new MyButton(game, FileLoader.loadImage("/newgame.png"), 240, 350, 180, 53);
		btnQuit = new MyButton(game, FileLoader.loadImage("/quit.png"), 240, 450, 180, 53);
		btnPlay = new MyButton(game, FileLoader.loadImage("/play.png"), 85, 355, 128, 53);
		btnCancel = new MyButton(game, FileLoader.loadImage("/cancel.png"), 235, 355, 128, 53);
		btnLeft = new MyButton(game, FileLoader.loadImage("/left.png"), 100, 240, 50, 50);
		btnRight = new MyButton(game, FileLoader.loadImage("/right.png"), 300, 240, 50, 50);
		buttons.add(btnNewGame);
		buttons.add(btnQuit);
	}

	@Override
	public void update() {
		if (isSelectLv) {
			if (btnPlay.isMouseUp()) {
				game.getWindow().getGameScr().setNewGame(level);
				game.getWindow().setScreen(Window.Screen.Game);
				isSelectLv = false;
			}
			if (btnCancel.isMouseUp()) {
				isSelectLv = false;
			}
			if (btnLeft.isMouseUp() && level > 1) {
				level--;
			}
			if (btnRight.isMouseUp() && level < 3) {
				level++;
			}
		} else {
			if (btnQuit.isMouseUp()) {
				System.exit(0);
			}
			if (btnNewGame.isMouseUp()) {
				isSelectLv = true;
			}
		}

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(backGround, 0, 0, game.getWindow().getCanvas().getWidth(), game.getWindow().getCanvas().getHeight(),
				null);

		if (isSelectLv) {
			btnPlay.paint(g);
			btnCancel.paint(g);
			btnLeft.paint(g);
			btnRight.paint(g);
			g.setColor(Color.white);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 55));
			g.drawString("" + level, 210, 285);
		} else {
			if (buttons != null) {
				for (MyButton button : buttons) {
					button.paint(g);
				}
			}
		}
	}

}

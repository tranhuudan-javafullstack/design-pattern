package tetris.engine.controller;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import tetris.engine.Game;

public class MyButton {
	private Game game;
	private int x, y;
	private int width, height;
	private BufferedImage image;

	public MyButton(Game game, BufferedImage image, int x, int y, int width, int height) {
		this.game = game;
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void paint(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
	}

	public boolean isMouseDown() {
		return game.getInput().isButtonDown(MouseEvent.BUTTON1, x, y, width, height);
	}

	public boolean isMouseUp() {
		return game.getInput().isButtonUp(MouseEvent.BUTTON1, x, y, width, height);
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
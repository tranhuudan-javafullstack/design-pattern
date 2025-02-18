package tetris.engine.controller;

import tetris.engine.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

	private Game game;
	private boolean[] keys = new boolean[256];
	private boolean[] keysLast = new boolean[256];
	private long[] lastTimeKeyHold = new long[256];
	private int delayHold[] = new int[256];
	private boolean[] buttons = new boolean[6];
	private boolean[] buttonsLast = new boolean[6];
	private int mouseX, mouseY;
	private int lastMouseX, lastMouseY;

	public Input(Game game) {
		this.game = game;
		mouseX = 0;
		mouseY = 0;
		lastMouseX = 0;
		lastMouseY = 0;

		game.getWindow().getCanvas().addKeyListener(this);
		game.getWindow().getCanvas().addMouseListener(this);
		game.getWindow().getCanvas().addMouseMotionListener(this);
	}

	public void update() {
		for (int i = 0; i < 256; i++) {
			keysLast[i] = keys[i];
		}
		for (int i = 0; i < 6; i++) {
			buttonsLast[i] = buttons[i];
		}
		lastMouseX = mouseX;
		lastMouseY = mouseY;
	}

	public boolean isKey(int keyCode) {
		return keys[keyCode];
	}

	public boolean isKeyHold(int keyCode) {
		if (isKeyDown(keyCode)) {
			delayHold[keyCode] = 600;
		}
		if (System.currentTimeMillis() - lastTimeKeyHold[keyCode] > delayHold[keyCode] || isKeyDown(keyCode)) {
			lastTimeKeyHold[keyCode] = System.currentTimeMillis();
			delayHold[keyCode] = delayHold[keyCode] - 300 < 50 ? 50 : delayHold[keyCode] - 300;
			return keys[keyCode];
		} else {
			return false;
		}
	}

	public boolean isKeyUp(int keyCode) {
		return !keys[keyCode] && keysLast[keyCode];
	}

	public boolean isKeyDown(int keyCode) {
		return keys[keyCode] && !keysLast[keyCode];
	}

	public boolean isButtonUp(int button, int x, int y, int width, int height) {
		return !buttons[button] && buttonsLast[button] && mouseX >= x && mouseX <= x + width && mouseY >= y
				&& mouseY <= y + height;
	}

	public boolean isButtonDown(int button, int x, int y, int width, int height) {
		return buttons[button] && !buttonsLast[button];
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = (int) (e.getX() / game.getScale());
		mouseY = (int) (e.getY() / game.getScale());

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = (int) (e.getX() / game.getScale());
		mouseY = (int) (e.getY() / game.getScale());

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() <= 6) {
			buttons[e.getButton()] = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() <= 6) {
			buttons[e.getButton()] = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() <= 256) {
			keys[e.getKeyCode()] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() <= 256) {
			keys[e.getKeyCode()] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

}
package tetris.engine;

import tetris.engine.controller.Input;
import tetris.engine.gui.screen.Window;

public class Game {
	private Window window;
	private Input input;
	private int width = 450, height = 600;
	private float scale = 1f;

	public void start() {
		window = new Window(this);
		input = new Input(this);
		run();
	}

	public void run() {

		while (true) {
			updateGame();
			renderGame();
		}

	}

	private void updateGame() {
		window.update();
		input.update();
	}

	private void renderGame() {
		window.paint();
	}

	public Window getWindow() {
		return window;
	}

	public Input getInput() {
		return input;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getScale() {
		return scale;
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}

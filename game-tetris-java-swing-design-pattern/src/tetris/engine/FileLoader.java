package tetris.engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FileLoader {
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Objects.requireNonNull(FileLoader.class.getResource(path)));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}

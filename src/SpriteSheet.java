package game.afhero.torf;

import java.awt.image.BufferedImage;

public class SpriteSheet {

		private BufferedImage sprite;
		public SpriteSheet(BufferedImage ss) {
			this.sprite = ss;
		}
		
		public BufferedImage grabImage(int row, int col, int width, int height) {
			BufferedImage img = sprite.getSubimage((row*width)-width, (col*height) - height, width, height);
			return img;
		}
}

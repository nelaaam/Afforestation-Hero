package game.afhero.torf;
import java.awt.Image;

public class AfforestationHero {
	private BufferedImageLoader loader = new BufferedImageLoader();
	
	
	private int WIDTH = 925;
	private int HEIGHT = 720;
	private Image ICONIMAGE = loader.loadImage("/AHeroLogo.png");
	private STATE GAMESTATE = STATE.Startup;
	
	public AfforestationHero() {
		audioPlayer.load();
		new Window(WIDTH, HEIGHT, ICONIMAGE, "AFFORESTATION HERO: TRUE OR FALSE", GAMESTATE);
		audioPlayer.getMusic("game_music").loop();
		
	}
	
	public static void main(String[] args) {
		new AfforestationHero();
	}

}

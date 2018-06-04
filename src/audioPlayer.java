package game.afhero.torf;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class audioPlayer {
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() {
		
		try {
			soundMap.put("wrong_Answer", new Sound("res/wrongAnswer.wav"));
			soundMap.put("correct_Answer", new Sound("res/correctAnswer.wav"));
			soundMap.put("level_complete", new Sound("res/levelComplete.wav"));
			soundMap.put("level_failed", new Sound("res/levelFailed.wav"));
			soundMap.put("timer_sound", new Sound("res/timerSound.wav"));
			soundMap.put("button_sound", new Sound("res/buttonSound.wav"));
			musicMap.put("game_music", new Music("res/gameMusic.wav"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}

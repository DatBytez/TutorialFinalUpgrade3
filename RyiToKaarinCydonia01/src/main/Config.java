package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import scenes.Playing;

public class Config {

	Playing playing;
	
	public Config(Playing playing) {
		this.playing = playing;
	}
	
	public void saveConfig() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
			
			// Full screen setting
			if(playing.fullScreenOn) {
				bw.write("On");
			}
			if(!playing.fullScreenOn) {
				bw.write("Off");
			}
			bw.newLine();
			
			// Music volume setting
			bw.write(String.valueOf(playing.music.volumeScale));
			bw.newLine();
			
			// Sound effect volume setting
			bw.write(String.valueOf(playing.soundEffect.volumeScale));
			bw.newLine();
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadConfig() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("config.txt"));
			
			String s = br.readLine();
			
			// Full screen check
			if(s.equals("On")) {
				playing.fullScreenOn = true;
			}
			if(s.equals("Off")) {
				playing.fullScreenOn = false;
			}
			
			// Music volume check
			s= br.readLine();
			playing.music.volumeScale = Integer.parseInt(s);
			
			// Music volume check
			s= br.readLine();
			playing.soundEffect.volumeScale = Integer.parseInt(s);
			
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


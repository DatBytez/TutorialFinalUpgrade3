package enviroment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import scenes.Playing;

public class Lighting {
	Playing playing;
	BufferedImage darknessFilter;
	public int dayCounter;
	public float filterAlpha = 0f;

	// Day State
	final int DAY_LENGTH = 180; //RESET TO 36000
	final int NIGHT_LENGTH = 130; //RESET TO 26000
	public final int day = 0;
	public final int dusk = 1;
	public final int night = 2;
	public final int dawn = 3;
	public int dayState = day;

	public Lighting(Playing playing) {
		this.playing = playing;
		setLightSource();

	}

	public void setLightSource() {
		// Create a buffered image
		darknessFilter = new BufferedImage(playing.screenWidth, playing.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

		if (playing.player.currentLight == null) {
			g2.setColor(new Color(0, 0, 0.1f, 0.95f)); // Change .98f according to moon / night vision
		} else {
			// Get the center x and y of the light circle
			int centerX = playing.player.screenX + (playing.tileSize) / 2;
			int centerY = playing.player.screenY + (playing.tileSize) / 2;

			// Create a gradation effect within the light circle
			Color color[] = new Color[12];
			float fraction[] = new float[12];

			color[0] = new Color(0, 0, 0.1f, 0.10f);
			color[1] = new Color(0, 0, 0.1f, 0.42f);
			color[2] = new Color(0, 0, 0.1f, 0.52f);
			color[3] = new Color(0, 0, 0.1f, 0.61f);
			color[4] = new Color(0, 0, 0.1f, 0.69f);
			color[5] = new Color(0, 0, 0.1f, 0.76f);
			color[6] = new Color(0, 0, 0.1f, 0.82f);
			color[7] = new Color(0, 0, 0.1f, 0.87f);
			color[8] = new Color(0, 0, 0.1f, 0.91f);
			color[9] = new Color(0, 0, 0.1f, 0.94f);
			color[10] = new Color(0, 0, 0.1f, 0.96f);
			color[11] = new Color(0, 0, 0.1f, 0.98f); // TODO Change 0.95f according to moon cycles ( 1 is max )

			fraction[0] = 0.00f;
			fraction[1] = 0.40f;
			fraction[2] = 0.50f;
			fraction[3] = 0.60f;
			fraction[4] = 0.65f;
			fraction[5] = 0.70f;
			fraction[6] = 0.75f;
			fraction[7] = 0.80f;
			fraction[8] = 0.85f;
			fraction[9] = 0.90f;
			fraction[10] = 0.95f;
			fraction[11] = 1.00f;

			// Create a gradation paint settings for the light circles
			RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (playing.player.currentLight.lightRadius),
					fraction, color); // TODO may be able to use different colors above to make a 'tripping' effect

			// Set the gradient data on g2
			g2.setPaint(gPaint);
		}

		g2.fillRect(0, 0, playing.screenWidth, playing.screenHeight);

		g2.dispose();

	}
	
	public void resetDay() {
		dayState = day;
		filterAlpha = 0f;
	}

	public void update() {
		if (playing.player.lightUpdated) {
			setLightSource();
			playing.player.lightUpdated = false;
		}

		// Check the date of day
		if (dayState == day) {

			dayCounter++;

			if (dayCounter > DAY_LENGTH) {
				dayState = dusk;
				dayCounter = 0;
			}
		}
		if (dayState == dusk) {
			filterAlpha += 0.0001f;

			if (filterAlpha > 1f) { // TODO This method uses darkness as a timer, not actual time elapsed
				filterAlpha = 1;
				dayState = night;
			}
		}
		if (dayState == night) {
			dayCounter++;

			if (dayCounter > NIGHT_LENGTH) {
				dayState = dawn;
				dayCounter = 0;
			}
		}
		if (dayState == dawn) {
			filterAlpha -= 0.0001f;

			if (filterAlpha < 0f) {
				filterAlpha = 0;
				dayState = day;
			}
		}
	}

	public void draw(Graphics2D g2) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
		g2.drawImage(darknessFilter, 0, 0, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		// DEBUG
		if (playing.debugMode) {
			String situation = "";

			switch (dayState) {
			case day:
				situation = "Day";
				break;
			case dusk:
				situation = "Dusk";
				break;
			case night:
				situation = "Night";
				break;
			case dawn:
				situation = "Dawn";
				break;
			}
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(50F));
			g2.drawString(situation, (playing.tileSize * 1)/2, playing.tileSize * 10);
		}
	}
}

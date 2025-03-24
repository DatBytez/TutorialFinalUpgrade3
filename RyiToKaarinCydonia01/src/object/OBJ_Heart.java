package object;

import entity.Entity;
import entity.EntityType;
import gfx.ImageLoader;
import scenes.Playing;

public class OBJ_Heart extends Entity {
	public static final String objName = "Heart";
	String path;

	public OBJ_Heart(Playing playing) {
		super(playing);
		this.playing = playing;
		this.path = "/objects/";

		type = EntityType.pickup;
		name = objName;
		value = 2;
		down.add(setup("/objects/heart_full", playing.tileSize / 2, playing.tileSize / 2));

		image = ImageLoader.loadImage(path + "heart_full");
		image2 = ImageLoader.loadImage(path + "heart_half");
		image3 = ImageLoader.loadImage(path + "heart_blank");
	}

	public boolean use(Entity entity) {

//		if (entity.life + value <= entity.maxLife) {
			playing.playSoundEffect(2);
			playing.ui.addMessage("Life +" + value + ".");
			entity.life += value;
			return true;
//		}
//		return false;
	}
}

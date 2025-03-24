package object;

import entity.Entity;
import entity.EntityType;
import gfx.ImageLoader;
import scenes.Playing;

public class OBJ_ManaCrystal extends Entity {
	public static final String objName = "Mana Crystal";
	String path;

	public OBJ_ManaCrystal(Playing playing) {
		super(playing);
		this.playing = playing;
		this.path = "/objects/";

		type = EntityType.pickup;
		name = objName;
		value = 1;
		down.add(setup("/objects/manacrystal_full", playing.tileSize / 2, playing.tileSize / 2));
		image = ImageLoader.loadImage(path + "manacrystal_full");
		image2 = ImageLoader.loadImage(path + "manacrystal_blank");
	}

	public boolean use(Entity entity) {

//		if (entity.life + value <= entity.maxLife) {
			playing.playSoundEffect(2);
			playing.ui.addMessage("Mana +" + value + ".");
			entity.mana += value;
			return true;
//		}
//		return false;
	}

}

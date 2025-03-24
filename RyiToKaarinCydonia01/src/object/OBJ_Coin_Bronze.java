package object;

import entity.Entity;
import entity.EntityType;
import scenes.Playing;

public class OBJ_Coin_Bronze extends Entity {
	
	public static final String objName = "Kellan";

	public OBJ_Coin_Bronze(Playing playing) {
		super(playing);
		this.playing = playing;
		price = 1;

		type = EntityType.pickup;
		name = objName;
		value = 1;
		down.add(setup("/objects/coin_bronze", playing.tileSize / 2, playing.tileSize / 2));
	}

	public boolean use(Entity entity) {
		playing.playSoundEffect(1);
		playing.ui.addMessage("Kellan +" + value + ".");
		playing.player.coin += value;
		return true;
	}

}

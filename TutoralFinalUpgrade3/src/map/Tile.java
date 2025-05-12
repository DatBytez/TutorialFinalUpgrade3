package map;

import java.awt.image.BufferedImage;

public class Tile {
    private transient BufferedImage image;
    
    public Tile(BufferedImage image) {
        this.image = image;
    }
    
    public BufferedImage getImage() {
        return image;
    }
}

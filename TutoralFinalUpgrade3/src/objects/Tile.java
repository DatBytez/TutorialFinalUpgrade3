package objects;

import java.awt.image.BufferedImage;

public class Tile {
    private int x, y;            // Tile position
    private BufferedImage image; // Tile image

    public Tile(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(BufferedImage img) {
    	this.image = img;
    }
}
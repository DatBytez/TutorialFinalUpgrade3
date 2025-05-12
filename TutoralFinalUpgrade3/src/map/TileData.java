package map;

import java.awt.image.BufferedImage;

public class TileData {
    private String atlasId;
    private int tileIndex;
    private boolean isAnimated;
    private boolean collision;
    private int animationFrame;
    
    // ADDED (check save functionality)
//    public BufferedImage image;

    // No-args constructor for Gson
    public TileData() {
    }

    public TileData(String atlasId, int tileIndex) {
        this.atlasId = atlasId;
        this.tileIndex = tileIndex;
        this.isAnimated = false;
        this.collision = false;
        this.animationFrame = 0;
    }

    public TileData(TileData other) {
        this.atlasId = other.atlasId;
        this.tileIndex = other.tileIndex;
        this.isAnimated = other.isAnimated;
        this.collision = other.collision;
        this.animationFrame = other.animationFrame;
    }

    // Getters and setters
    public String getAtlasId() {
        return atlasId;
    }

    public void setAtlasId(String atlasId) {
        this.atlasId = atlasId;
    }

    public int getTileIndex() {
        return tileIndex;
    }

    public void setTileIndex(int tileIndex) {
        this.tileIndex = tileIndex;
    }

    public boolean isAnimated() {
        return isAnimated;
    }

    public void setAnimated(boolean animated) {
        isAnimated = animated;
    }

    public boolean isCollidable() {
        return collision;
    }

    public void setCollidable(boolean collidable) {
        collision = collidable;
    }

    public int getAnimationFrame() {
        return animationFrame;
    }

    public void setAnimationFrame(int animationFrame) {
        this.animationFrame = animationFrame;
    }
}

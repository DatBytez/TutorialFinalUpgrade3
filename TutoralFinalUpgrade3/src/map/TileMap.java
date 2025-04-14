package map;

public class TileMap {
    private TileData[][][] tileMapLayers;
    private int activeLayer;

    // No-args constructor for Gson
    public TileMap() {
    }

    public TileMap(TileData[][][] tileMapLayers, int activeLayer) {
        this.tileMapLayers = tileMapLayers;
        this.activeLayer = activeLayer;
    }

    public TileData[][][] getTileMapLayers() {
        return tileMapLayers;
    }

    public void setTileMapLayers(TileData[][][] tileMapLayers) {
        this.tileMapLayers = tileMapLayers;
    }

    public int getActiveLayer() {
        return activeLayer;
    }

    public void setActiveLayer(int activeLayer) {
        this.activeLayer = activeLayer;
    }
}

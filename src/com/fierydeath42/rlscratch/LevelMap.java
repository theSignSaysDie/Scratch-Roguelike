package com.fierydeath42.rlscratch;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class LevelMap {
	int width, height;
	Dictionary<MapLayerType, MapLayer> layers;
	Color backgroundColor;

	public LevelMap(int width, int height) {
		this.width = width;
		this.height = height;
		this.backgroundColor = Color.WHITE;
		layers = new Hashtable<>();
		initializeMap();
	}

	public LevelMap(int width, int height, Color backgroundColor) {
		this.width = width;
		this.height = height;
		this.backgroundColor = backgroundColor;
		layers = new Hashtable<>();
		initializeMap();
	}

	private void initializeMap() {
		layers.put(MapLayerType.TERRAIN, new MapLayer(width, height));
		layers.put(MapLayerType.OBJECT, new MapLayer(width, height));
		layers.put(MapLayerType.ITEM, new MapLayer(width, height));
		layers.put(MapLayerType.MONSTER, new MapLayer(width, height));
	}

	public String getTile(int x, int y, MapLayerType layerName) {
		return layers.get(layerName).getTile(x, y);
	}
}

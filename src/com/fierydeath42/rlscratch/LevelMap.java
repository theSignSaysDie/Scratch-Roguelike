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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public MapLayer getLayer(MapLayerType mlt) {
		return layers.get(mlt);
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

	public void fill(String obj, MapLayerType layerName) {
		getLayer(layerName).fill(obj);
	}

	public void fillRect(int left, int top, int right, int bottom, String objMap, MapLayerType layerName) {
		getLayer(layerName).fillRect(left, top, right, bottom, objMap);
	}

	public void emptyRect(int left, int top, int right, int bottom, String obj, MapLayerType layerName) {
		getLayer(layerName).emptyRect(left, top, right, bottom, obj);
	}

	public void setBGColor(Color color) {
		backgroundColor = color;
	}

	public Color getBGColor() {
		return backgroundColor;
	}
}
package com.fierydeath42.rlscratch;

import java.awt.*;

public class LevelMapGenerator {
	static int width = 16, height = 16;

	public static LevelMap generate(String levelType) {
		Color defaultBGColor = Color.BLACK;
		LevelMap result = new LevelMap(width, height, defaultBGColor);

		switch (levelType) {
			case "debug":
				result.setBGColor(Color.MAGENTA);
				result.fillRect(1, 1, width - 1, height - 1, "floor-debug", MapLayerType.TERRAIN);
				result.emptyRect(0, 0, width, height, "wall-debug", MapLayerType.OBJECT);
				result.fillRect(4, 4, 8, 8, "floor-debug", MapLayerType.TERRAIN);
				result.emptyRect(3, 3, 9, 9, "wall-debug", MapLayerType.OBJECT);
				break;
			case "test":
				result.setBGColor(Color.BLACK);
				result.fillRect(0, 0, width, height, "floor-stone-lighter", MapLayerType.TERRAIN);
				result.emptyRect(0, 0, width, height, "wall-stone-lighter", MapLayerType.OBJECT);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + levelType);
		}

		postProcess(result);

		return result;
	}

	public static void postProcess(LevelMap map) {
		updateAdjacencies(map, "floor");
		updateAdjacencies(map, "wall");
	}

	private static void updateAdjacencies(LevelMap map, String target) {
		MapLayer layer;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				switch(target) {
					case "wall":
						layer = map.getLayer(MapLayerType.OBJECT);
						if (layer.isTile(i, j, target)) {
							layer.setTile(i, j, layer.getTile(i, j) +
									(layer.isTile(i, j-1, target) ? 1 : 0) +
									(layer.isTile(i +1, j, target) ? 1 : 0) +
									(layer.isTile(i, j + 1, target) ? 1 : 0) +
									(layer.isTile(i - 1, j, target) ? 1 : 0)
							);
						}
						break;
					case "floor":
						layer = map.getLayer(MapLayerType.TERRAIN);
						MapLayer wallLayer = map.getLayer(MapLayerType.OBJECT);
						if (layer.isTile(i, j, target)) {
							layer.setTile(i, j, layer.getTile(i, j) +
									(layer.isTile(i, j-1, target) && !wallLayer.isTile(i, j-1, "wall") ? 1 : 0) +
									(layer.isTile(i +1, j, target) && !wallLayer.isTile(i+1, j, "wall") ? 1 : 0) +
									(layer.isTile(i, j + 1, target) && !wallLayer.isTile(i, j+1, "wall") ? 1 : 0) +
									(layer.isTile(i - 1, j, target) && !wallLayer.isTile(i-1, j, "wall") ? 1 : 0)
							);
						}
						break;
				}
			}
		}
	}
}

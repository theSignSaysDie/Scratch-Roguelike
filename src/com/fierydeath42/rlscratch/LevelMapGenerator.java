package com.fierydeath42.rlscratch;

import java.awt.*;

public class LevelMapGenerator {
	public static LevelMap generate(String levelType) {
		int defaultWidth = 16, defaultHeight = 16;
		Color defaultBGColor = Color.BLACK;
		LevelMap result = new LevelMap(defaultWidth, defaultHeight, defaultBGColor);

		switch(levelType) {
			case "debug":
				result.setBGColor(Color.MAGENTA);
				result.fill("floor-debug", MapLayerType.TERRAIN);
				result.emptyRect(0, 0, defaultWidth, defaultHeight, "wall-debugflat", MapLayerType.OBJECT);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + levelType);
		}

		return result;
	}
}

package com.fierydeath42.rlscratch;

import java.awt.*;

public class ScreenRenderer {
	public GameScreen gs;

	public ScreenRenderer(GameScreen gs) {
		this.gs = gs;
	}

	public void render(Graphics g, Dimension d) {
		drawMap(g, d, Campaign.getCurrentLevel());
	}

	public void drawSprite(Graphics g, String id, int x, int y) {
		g.drawImage(SpriteSheet.getSprite(id), x, y, x + GameVariables.spriteSize, y + GameVariables.spriteSize, 0, 0
				, GameVariables.spriteDim, GameVariables.spriteDim, null);
	}

	public void drawMap(Graphics g, Dimension d, LevelMap map) {
		g.setColor(map.getBGColor());
		g.fillRect(0, 0, d.width, d.height);
		for (MapLayerType mlt : MapLayerType.values()) {
			drawLayer(g, map.getLayer(mlt));
		}
	}

	public void drawLayer(Graphics g, MapLayer ml) {
		for (int j = 0; j < ml.getHeight(); j++) {
			for (int i = 0; i < ml.getWidth(); i++) {
				drawSprite(g, ml.getTile(j, i), i * GameVariables.spriteSize, j * GameVariables.spriteSize);
			}
		}
	}
}

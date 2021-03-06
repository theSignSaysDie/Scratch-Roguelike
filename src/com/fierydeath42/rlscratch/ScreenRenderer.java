package com.fierydeath42.rlscratch;

import java.awt.*;
import java.util.Dictionary;
import java.util.Enumeration;

public class ScreenRenderer {
	private static int centerX;
	private static int centerY;
	private static int absCenterX;
	private static int absCenterY;
	private static boolean followingEntity;
	private static Monster targetEntity;
	public GameScreen gs;

	public ScreenRenderer(GameScreen gs) {
		this.gs = gs;
		absCenterX = gs.getWidth() / 2;
		absCenterY = gs.getHeight() / 2;
		centerX = absCenterX;
		centerY = absCenterY;
		followingEntity = false;
	}

	public static void render(Graphics g, Dimension d) {
		drawMap(g, d, Campaign.getCurrentLevel());
		drawEntities(g, Campaign.getEntities());
		g.setColor(Color.GREEN);
		g.fillOval(centerX, centerY, 8, 8);
	}

	public static void drawSprite(Graphics g, String id, int x, int y) {
		if (!id.equals(""))
			g.drawImage(SpriteSheet.getSprite(id), x, y, x + GameRefConstants.spriteSize,
					y + GameRefConstants.spriteSize, 0, 0
					, GameRefConstants.spriteDim, GameRefConstants.spriteDim, null);
	}

	public static void drawMap(Graphics g, Dimension d, LevelMap map) {
		g.setColor(map.getBGColor());
		g.fillRect(0, 0, d.width, d.height);
		for (MapLayerType mlt : MapLayerType.values()) {
			drawLayer(g, map.getLayer(mlt));
		}
	}

	public static void drawEntities(Graphics g, Dictionary<String, Monster> ml) {
		int adjX, adjY;
		Enumeration<String> keys = ml.keys();
		while (keys.hasMoreElements()) {
			Monster m = ml.get(keys.nextElement());
			adjX = (m.x * GameRefConstants.spriteSize) + (absCenterX - centerX);
			adjY = (m.y * GameRefConstants.spriteSize) + (absCenterY - centerY);
			drawSprite(g, m.sprite + (Timekeeper.oneOrZero() ? "0" : "1"), adjX, adjY);
		}
	}

	public static void drawLayer(Graphics g, MapLayer ml) {
		int adjX, adjY;
		for (int j = 0; j < ml.getHeight(); j++) {
			for (int i = 0; i < ml.getWidth(); i++) {
				adjX = (i * GameRefConstants.spriteSize) + (absCenterX - centerX);
				adjY = (j * GameRefConstants.spriteSize) + (absCenterY - centerY);
				drawSprite(g, ml.getTile(i, j), adjX, adjY);
			}
		}
	}

	public static void focus(int x, int y) {
		centerX = x;
		centerY = y;
	}

	public static void focus(Monster m) {
		followingEntity = true;
		targetEntity = m;
	}

	public static void moveCamera(int dx, int dy) {
		focus(centerX + dx, centerY - dy);
	}

	public void focus(String position) {
		switch (position) {
			case "origin":
				focus(0, 0);
				break;
			case "center":
				focus(absCenterX, absCenterY);
				break;
			default:
				throw new IllegalArgumentException("No such camera angle supported.");
		}
	}

	public void updateDimensions() {
		absCenterX = gs.getWidth() / 2;
		absCenterY = gs.getHeight() / 2;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getOriginX() {
		return centerX - gs.getWidth() / 2;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getOriginY() {
		return centerY - gs.getHeight() / 2;
	}

}

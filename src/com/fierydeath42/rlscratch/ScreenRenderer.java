package com.fierydeath42.rlscratch;

import java.awt.*;

public class ScreenRenderer {
	public GameScreen gs;

	public ScreenRenderer(GameScreen gs) {
		this.gs = gs;
	}

	public void render(Graphics g, Dimension d) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, d.width, d.height);

		drawSprite(g, "wall-debug0110", 32, 32);
		drawSprite(g, "wall-debug0101", 64, 32);
		drawSprite(g, "wall-debug0011", 96, 32);
		drawSprite(g, "wall-debug1010", 96, 64);
		drawSprite(g, "wall-debug1001", 96, 96);
		drawSprite(g, "wall-debug0101", 64, 96);
		drawSprite(g, "wall-debug1100", 32, 96);
		drawSprite(g, "wall-debug1010", 32, 64);
	}

	public void drawSprite(Graphics g, String id, int x, int y) {
		g.drawImage(SpriteSheet.getSprite(id), x, y, x + 32, y + 32, 0, 0, 16, 16, null);
	}
}

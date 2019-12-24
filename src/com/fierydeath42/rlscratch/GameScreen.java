package com.fierydeath42.rlscratch;

import javax.swing.*;
import java.awt.*;

class GameScreen extends JComponent {
	GameObject app;
	ScreenRenderer camera;

	public GameScreen(GameObject a) {
		// Bind the GameScreen to the JFrame which contains it
		app = a;
		// Bind the Camera to this GameScreen
		camera = new ScreenRenderer(this);
		app.setTitle(a.getTitle());
	}

	public void paint(Graphics g) {
		getParent().setBackground(Color.BLACK);
		camera.updateDimensions();
		camera.render(g, getSize());
	}

	public ScreenRenderer getCamera() {
		return camera;
	}

	public int getWidth() {
		int width = getSize().width;
		return width;
	}

	public int getHeight() {
		int height = getSize().height;
		return height;
	}
}
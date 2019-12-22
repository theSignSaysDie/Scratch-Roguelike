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
		camera.render(g, getSize());
	}
}
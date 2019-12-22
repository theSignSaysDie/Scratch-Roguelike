package com.fierydeath42.rlscratch;

import javax.swing.*;
import java.awt.*;

public class GameObject extends JFrame {
	String title;
	public GameObject(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void run() {
		initializeGame(new GameScreen(this));
	}

	public void initializeGame(GameScreen gs) {
		loadGraphics();
		buildWindowFrame(gs);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void loadGraphics() {
		// Initialize the SpriteSheet and load graphics resources
		SpriteSheet.initialize();
	}

	// Set up the window frame, dimensions. No level rendering here.
	public void buildWindowFrame(GameScreen gs) {
		int width = 1000;
		int height = 600;
		int sideSpacing = 5, topSpacing = 5;
		int paneTopCorrection = 29, paneSideCorrection = 6;
		int topMargin = paneTopCorrection + 2 * topSpacing, sideMargin = paneSideCorrection + sideSpacing * 2;
		setSize(new Dimension(width + sideMargin, height + topMargin));
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);

		gs.setPreferredSize(new Dimension(width, height));

		panel.add(gs);
		getContentPane().add(panel);

		setVisible(true);
	}
}

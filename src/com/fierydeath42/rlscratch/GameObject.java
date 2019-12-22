package com.fierydeath42.rlscratch;

import javax.swing.*;
import java.awt.*;

public class GameObject extends JFrame {
	String title;
	GameScreen gs;
	public GameObject(String title) {
		this.title = title;
		gs = new GameScreen(this);
	}

	public String getTitle() {
		return title;
	}

	public void run() {
		System.out.println("Running Game...");
		initializeGame(gs);

		System.out.println("Beginning Game Loop...");
		while(true) {
			doGameLoop();
		}
	}

	private void doGameLoop() {
		updateGameState();
		updateGraphics();
	}

	private void updateGameState() {
		/*
		 TODO Program method for interpreting a single character in the context of control keys, command buffer, etc.
		 */
	}

	private void updateGraphics() {
		gs.getParent().setBackground(Color.BLACK);
		gs.repaint();
	}

	public void initializeGame(GameScreen gs) {
		System.out.println("Initializing Game...");
		loadGraphics();
		beginCampaign();
		buildWindowFrame(gs);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("All Done!");
	}

	private void beginCampaign() {
		System.out.println("Beginning Campaign...");
		Campaign.initialize();
		Campaign.addNewLevel("test");
	}

	public void loadGraphics() {
		System.out.println("Loading Graphics...");
		// Initialize the SpriteSheet and load graphics resources
		SpriteSheet.initialize();
	}

	// Set up the window frame, dimensions. No level rendering here.
	public void buildWindowFrame(GameScreen gs) {
		System.out.println("Building Window Frame...");
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

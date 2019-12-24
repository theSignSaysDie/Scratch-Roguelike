package com.fierydeath42.rlscratch;

import javax.swing.*;
import java.awt.*;

public class GameObject extends JFrame {
	String title;
	GameScreen gs;
	GameInputListener gil;
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
		String input = gil.getInputAndClear();
		switch (input) {
			case "w": case "W":
				gs.getCamera().moveCamera(0, (gil.shiftDown ? 5 : 1));
				System.out.println("W");
				break;
			case "a": case "A":
				gs.getCamera().moveCamera((gil.shiftDown ? -5 : -1), 0);
				System.out.println("A");
				break;
			case "s": case "S":
				gs.getCamera().moveCamera(0, (gil.shiftDown ? -5 : -1));
				System.out.println("S");
				break;
			case "d": case "D":
				gs.getCamera().moveCamera((gil.shiftDown ? 5 : 1), 0);
				System.out.println("D");
				break;
		}
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
		setupInput();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("All Done!");
	}

	private void setupInput() {
		gil = new GameInputListener();
		addKeyListener(gil);
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
		setFocusable(true);
		addKeyListener(new GameInputListener());
	}
}

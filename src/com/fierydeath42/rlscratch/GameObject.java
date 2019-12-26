package com.fierydeath42.rlscratch;

import javax.swing.*;
import java.awt.*;

public class GameObject extends JFrame {
	String title;
	GameScreen gs;
	GameKeyboardListener gkl;
	GameMouseListener gml;

	Monster player;

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
		while (true) {
			if (Timekeeper.isItTime()) {
				Timekeeper.setTargetTimeFromNow(1000 / GameRefConstants.maxGameTicksPerSecond);
				doGameLoop();
			}
		}
	}

	private void doGameLoop() {
		updateGameState();
		updateGraphics();
	}

	private void updateGameState() {
		/*if (Mouse.isFocused() && Mouse.isPressed) {
			gs.getCamera().focus(Mouse.originX + Mouse.getDragDeltaX(),	Mouse.originY + Mouse.getDragDeltaY());
			return;
		} else {
			Mouse.originX = gs.getCamera().getCenterX();
			Mouse.originY = gs.getCamera().getCenterY();
		}*/
		for (int i = 0; i < Keyboard.keyQueue.size(); i++) {
			String input = Keyboard.getKeyName(Keyboard.keyQueue.get(i));
			switch (input) {
				case "up":
					gs.getCamera().moveCamera(0, (Keyboard.isShiftDown() ? 5 : 1));
					System.out.println("W");
					break;
				case "left":
					gs.getCamera().moveCamera((Keyboard.isShiftDown() ? -5 : -1), 0);
					System.out.println("A");
					break;
				case "down":
					gs.getCamera().moveCamera(0, (Keyboard.isShiftDown() ? -5 : -1));
					System.out.println("S");
					break;
				case "right":
					gs.getCamera().moveCamera((Keyboard.isShiftDown() ? 5 : 1), 0);
					System.out.println("D");
					break;
			}
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
		keepTime();
		setupInput();
		System.out.println("All Done!");
	}

	private void keepTime() {
		Timekeeper.startTicking();
	}

	private void setupInput() {
		gkl = new GameKeyboardListener();
		initializeKeyboard();
		gml = new GameMouseListener();
		initializeMouse();
		addKeyListener(gkl);
		addMouseListener(gml);
		addMouseMotionListener(gml);
		addMouseWheelListener(gml);
	}

	private void initializeMouse() {
		Mouse mouse = new Mouse();
	}

	private void initializeKeyboard() {
		Keyboard keyboard = new Keyboard();
	}

	private void beginCampaign() {
		System.out.println("Beginning Campaign...");
		Campaign.initialize();
		Campaign.addNewLevel("test");

		player = MonsterSpawner.makeMonster("player");
		Campaign.addEntity(player);
		/*player.x = 5;
		player.y = 5;*/
	}

	public void loadGraphics() {
		System.out.println("Loading Graphics...");
		// Initialize the SpriteSheet and load graphics resources
		SpriteSheet.initialize();
		// Custom mouse cursor
		// TODO implement better cursor
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(SpriteSheet.getSprite("cursor"), new Point(6, 6),
				"game cursor"));
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
		addKeyListener(new GameKeyboardListener());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

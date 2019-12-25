package com.fierydeath42.rlscratch;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyboardListener implements KeyListener {

	public GameKeyboardListener() {	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Keyboard.pressKey(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keyboard.releaseKey(e.getKeyCode());
	}
}

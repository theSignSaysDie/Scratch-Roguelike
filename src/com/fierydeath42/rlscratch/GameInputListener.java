package com.fierydeath42.rlscratch;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameInputListener implements KeyListener {

	// TODO Finish this
	String keyBuffer = "";
	boolean shiftDown, altDown;

	public GameInputListener() {
		shiftDown = false;
		altDown = false;
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar() + " " + e.getKeyCode());

		if(e.getKeyCode() == 16 || e.getKeyCode() == 17) {
			
		} else if (keyBuffer.equals("")) {
			keyBuffer = String.valueOf(e.getKeyChar());
		}

		if(e.isShiftDown()) {
			shiftDown = true;
		}

		if(e.isAltDown()) {
			altDown = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!e.isShiftDown()) {
			shiftDown = false;
		}

		if(!e.isAltDown()) {
			altDown = false;
		}
	}

	public String getInput() {
		return keyBuffer;
	}

	public String getInputAndClear() {
		String result = getInput();
		clearBuffer();
		return result;
	}

	public void clearBuffer() {
		keyBuffer = "";
	}
}

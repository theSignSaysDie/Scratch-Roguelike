package com.fierydeath42.rlscratch;

import java.awt.event.*;

public class GameMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Mouse.pressButton();
		Mouse.hold(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		Mouse.releaseButton();
		Mouse.release();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Mouse.setFocused(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Mouse.setFocused(false);
	}

	// MouseMotion
	@Override
	public void mouseMoved(MouseEvent e) {
		Mouse.setXY(e.getX(), e.getY());

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Mouse.setXY(e.getX(), e.getY());
		Mouse.updateDeltas(e.getPoint());
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Mouse.scrollTicks += (e.getWheelRotation() / e.getScrollAmount());
	}
}
package com.fierydeath42.rlscratch;

import java.awt.*;

public class Mouse {
	static int x, y;
	static int holdX, holdY;
	static int dragDeltaX, dragDeltaY;
	static int originX, originY;
	static boolean isFocused;
	static int scrollTicks;
	static boolean isPressed;

	public static void windupMouse() {
		isFocused = false;
		scrollTicks = 0;
		isPressed = false;
		holdX = 0;
		holdY = 0;
		originX = 0;
		originY = 0;
	}

	public static boolean isFocused() {
		return isFocused;
	}

	public static void setFocused(boolean f) {
		isFocused = f;
	}

	public static int getX() {
		return x;
	}

	public static int getY() { return y; }

	public static void setXY(int X, int Y) {
		x = X;
		y = Y;
	}

	public static void pressButton() {
		isPressed = true;
	}


	public static void releaseButton() {
		isPressed = false;
	}

	public static void hold(Point point) {
		holdX = (int) point.getX();
		holdY = (int) point.getY();
	}

	public static void release() {
		//holdX = 0;
		//holdY = 0;
		modifyOrigin();
		resetDeltas();
	}

	public static void modifyOrigin() {
		originX += dragDeltaX;
		originY += dragDeltaY;
	}

	private static void resetDeltas() {
		dragDeltaX = 0;
		dragDeltaY = 0;
	}

	public static void updateDeltas(Point point) {
		dragDeltaX = holdX - (int) point.getX();
		dragDeltaY = holdY - (int) point.getY();
	}

	public static int getDragDeltaX() {
		return dragDeltaX;
	}

	public static int getDragDeltaY() {
		return dragDeltaY;
	}

	public static int getDragDeltaX(int offset) {
		return dragDeltaX - offset;
	}

	public static int getDragDeltaY(int offset) {
		return dragDeltaY - offset;
	}
}

package com.fierydeath42.rlscratch;

public class MapLayer {
	int width, height;
	String[][] data;
	public MapLayer(int w, int h) {
		width = w;
		height = h;
		data = new String[h][w];
	}

	public void setTile(int x, int y, String obj) {
		// TODO: Add index error handling code
		data[y][x] = obj;
	}

	public String getTile(int x, int y) {
		return data[y][x];
	}

	public void fill(String obj) {
		fillRect(0, 0, width, height, obj);
	}

	public void fillRect(int left, int top, int right, int bottom, String obj) {
		for(int j = top; j < bottom; j++) {
			for(int i = left; i < right; i++) {
				setTile(i, j, obj);
			}
		}
	}
}

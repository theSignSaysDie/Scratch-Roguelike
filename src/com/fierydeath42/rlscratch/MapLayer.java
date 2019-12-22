package com.fierydeath42.rlscratch;

public class MapLayer {
	int width, height;
	String[][] data;
	public MapLayer(int w, int h) {
		width = w;
		height = h;
		data = new String[h][w];
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				data[j][i] = "";
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
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

	public void emptyRect(int left, int top, int right, int bottom, String obj) {
		for(int i = left; i < right; i++) {
			setTile(i, top, obj);
			setTile(i, bottom-1, obj);
		}
		for(int j = top+1; j < bottom-1; j++) {
			setTile(left, j, obj);
			setTile(right-1, j, obj);
		}
	}
}

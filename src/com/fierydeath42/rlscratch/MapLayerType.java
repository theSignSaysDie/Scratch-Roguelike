package com.fierydeath42.rlscratch;

public enum MapLayerType {
	TERRAIN(0), OBJECT(1), ITEM(2), MONSTER(3);

	private final int value;
	private MapLayerType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

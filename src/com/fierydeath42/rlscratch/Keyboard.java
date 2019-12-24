package com.fierydeath42.rlscratch;

import java.util.Dictionary;
import java.util.Hashtable;

public class Keyboard {
	Dictionary<String, Boolean> keysDown;
	Dictionary<String, Integer> keyCodes;

	public Keyboard() {
		keysDown = new Hashtable<>();
		keyCodes = new Hashtable<>();
	}

}

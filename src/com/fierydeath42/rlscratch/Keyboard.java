package com.fierydeath42.rlscratch;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Keyboard {
	static Dictionary<Integer, Boolean> keysDown;
	static Dictionary<String, Integer> keyCodes;
	static Dictionary<Integer, String> codeNames;
	static ArrayList<Integer> keyQueue;
	public Keyboard() {
		keysDown = new Hashtable<>();
		keyCodes = new Hashtable<>();
		codeNames = new Hashtable<>();
		keyQueue = new ArrayList<>();
		initialize();
	}

	private static void initialize() {
		String[] keys = new String[]{null, null, null, "cancel", null, null, null, null, "backspace", "tab", "enter",
				null, "clear", null, null, null, "shift", "ctrl", "alt", "pause", "caps lock", "kana", null, null,
				"final", "kanji", null, "escape", "convert", "no convert", "accept", "mode change", "space", "page " +
				"up", "page down", "end", "home", "left", "up", "right", "down", null, null, null, ",", "-", ".", "/"
				, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", null, ";", null, "=", null, null, null, "a", "b",
				"c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z", "[", "\\", "]", null, null, "num0", "num1", "num2", "num3", "num4", "num5", "num6"
				, "num7", "num8", "num9", "num*", "num+", "num,", "num-", "num.", "num/", "f1", "f2", "f3", "f4", "f5"
				, "f6", "f7", "f8", "f9", "f10", "f11", "f12", null, null, null, "delete"};

		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) {
				keyCodes.put(keys[i], i);
				codeNames.put(i, keys[i]);
				keysDown.put(i, false);

			}
		}
	}

	// TODO Clean up this code re: code copypaste, add isAltDown, shift, arrow key, etc. methods

	// TODO Finish up the key queue (helpful for determining key precedence) and decide whether or not special keys
	//  will be logged

	public static boolean pressKey(String name) {
		Integer test = keyCodes.get(name);
		if(test != null) {
			keysDown.put(test, true);
			System.out.println("Adding key " + name);
			if(keyQueue.indexOf(test) == -1)
				keyQueue.add(test);
			return true;
		} else {
			return false;
		}
	}

	public static boolean pressKey(Integer id) {
		Boolean test = keysDown.get(id);
		if(test != null) {
			keysDown.put(id, true);
			System.out.println("Adding key id " + id);
			if(keyQueue.indexOf(id) == -1)
				keyQueue.add(id);
			return true;
		} else {
			return false;
		}
	}

	public static boolean releaseKey(String name) {
		Integer test = keyCodes.get(name);
		if(test != null) {
			keysDown.put(test, false);
			if(keyQueue.indexOf(test) != -1)
				keyQueue.remove(keyQueue.indexOf(test));
			return true;
		} else {
			return false;
		}
	}

	public static boolean releaseKey(Integer id) {
		Boolean test = keysDown.get(id);
		if(test != null) {
			keysDown.put(id, false);
			if(keyQueue.indexOf(id) != -1)
				keyQueue.remove(keyQueue.indexOf(id));
			return true;
		} else {
			return false;
		}
	}

	public static boolean isDown(String name) {
		Integer test = keyCodes.get(name);
		return (test == null ? false : keysDown.get(test));
	}

	public static boolean isDown(Integer id) {
		Boolean test = keysDown.get(id);
		return (test == null ? false : test);
	}

	public static Integer getKeycode(String name) {
		return keyCodes.get(name);
	}

	public static String getKeyName(int i) {
		return codeNames.get(i);
	}

	public static boolean isShiftDown() {
		return isDown(getKeycode("shift"));
	}
}

package com.fierydeath42.rlscratch;

import java.util.Dictionary;
import java.util.Hashtable;

public class Campaign {
	static Dictionary<String, LevelMap> levels = new Hashtable<>();;
	static int id = 0;
	static String currentLevel;

	public static String addNewLevel(String levelType) {
		LevelMap newMap = LevelMapGenerator.generate(levelType);
		String levelName = levelType + id++;
		levels.put(levelName, newMap);
		currentLevel = levelName;
		return levelName;
	}

	private static LevelMap getLevel(String levelName) {
		return levels.get(levelName);
	}

	public static LevelMap getCurrentLevel() {
		return getLevel(currentLevel);
	}
}

package com.fierydeath42.rlscratch;

import java.util.Dictionary;
import java.util.Hashtable;


/*
	TODO Level linking
	TODO Level transfer
 */


/**
 * <h1>The Campaign</h1>
 * Responsible for storing and randomly generating the various levels of the world.
 * @see LevelMap
 * @see LevelMapGenerator
 */
public class Campaign {
	/**
	 * Catalogues the levels for ease of reference by name.
	 */
	static Dictionary<String, LevelMap> levels;
	static int levelID;
	static int entityID;
	static String currentLevel;
	static Dictionary<String, Monster> entities;

	/**
	 * Called by a running {@code GameObject}.
	 */
	public static void initialize() {
		System.out.println("Initializing Campaign...");
		levels = new Hashtable<>();
		entities = new Hashtable<>();
		levelID = 0;
	}

	public static String addNewLevel(String levelType) {
		System.out.println("Adding a new level... ");
		System.out.println("Generating level of type " + levelType + "...");
		LevelMap newMap = LevelMapGenerator.generate(levelType);
		String levelName = levelType + levelID++;
		System.out.println("Putting " + levelName + " into the Campaign...");
		levels.put(levelName, newMap);
		System.out.println("Setting currentLevel to " + levelName);
		currentLevel = levelName;
		System.out.println("Added " + levelName);
		return levelName;
	}

	private static LevelMap getLevel(String levelName) {
		LevelMap result = levels.get(levelName);
		return result;
	}

	public static LevelMap getCurrentLevel() {
		return getLevel(currentLevel);
	}

	public static Dictionary<String, Monster> getEntities() {
		return entities;
	}

	public static void removeEntity(String target) {
		entities.remove(target);
	}

	public static void addEntity(Monster m) {
		String id = m.type + entityID++;
		entities.put(id, m);
	}
}

package com.fierydeath42.rlscratch;

public class MonsterSpawner {
	public static Monster makeMonster(String type) {
		Monster m = new Monster();
		m.x = 0;
		m.y = 0;
		switch(type) {
			case "player":
				m.name = "player";
				m.type = "player";
				m.health = 100;
				m.maxHealth = 100;
				m.sprite = "dumbfuck";
		}
		return m;
	}
}

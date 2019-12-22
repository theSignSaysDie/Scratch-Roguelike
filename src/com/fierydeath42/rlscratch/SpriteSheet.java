package com.fierydeath42.rlscratch;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class SpriteSheet {
	private static int spriteSize = 16;
	private static Dictionary<String, BufferedImage> spritedict;
	private static BufferedImage missingText;
	private static String sheets;
	private static String spritekey;

	public SpriteSheet() {}

	public static void initialize() {
		if(spritedict == null) {
			spritedict = new Hashtable<String, BufferedImage>();

			// Load sheets
			sheets = "img/sheets.txt";
			// Load sprite locations
			spritekey = "img/spritekey.txt";
			loadKey();
		}
	}

	/*
		Reads the spritekey file, line by line, and creates a dictionary such that
		when you provide the name of the sprite (in-game id), you can retrieve its location
		on a specific sprite sheet.

		This key is used by getSprite() to see if a sprite exists and to get information on
		the sprite's location for packaging and return.
	 */
	private static void loadKey() {
		Dictionary<String, BufferedImage> sheetdict = new Hashtable<>();

		/*
            Load the 'missing image' texture. If the game breaks here, you've got problems.
         */
		try {
			String errImg = "img/missing.png";
			missingText = ImageIO.read(new File(errImg));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

        /*
            Reads the sheets file line by line and makes a dictionary of String keys and
            spritesheets loaded into memory.
         */
		try {
			File sheetsFile = new File(sheets);
			FileInputStream sheetStream = new FileInputStream(sheetsFile);
			InputStreamReader sheetReader = new InputStreamReader(sheetStream);
			StreamTokenizer sheetTokens = new StreamTokenizer(sheetReader);

			String folder, spriteSheet;
			BufferedImage image;

			// Loop that reads the file
			while (sheetTokens.nextToken() != StreamTokenizer.TT_EOF) {
				folder = sheetTokens.sval;
				sheetTokens.nextToken();
				spriteSheet = sheetTokens.sval;
				String imgDir = "img/DawnLike";
				String path = imgDir + "/" + folder + "/" + spriteSheet + ".png";
				image = ImageIO.read(new File(path));
				System.out.println(path);
				sheetdict.put(spriteSheet, image);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			File keyFile = new File(spritekey);
			FileInputStream keyStream = new FileInputStream(keyFile);
			InputStreamReader keyReader = new InputStreamReader(keyStream);
			StreamTokenizer keyTokens = new StreamTokenizer(keyReader);

			// Loop that reads the file
			String id, sheet;
			short x, y;

			while (keyTokens.nextToken() != StreamTokenizer.TT_EOF) {
				id = keyTokens.sval;
				keyTokens.nextToken();
				sheet = keyTokens.sval;
				keyTokens.nextToken();
				x = (short) keyTokens.nval;
				keyTokens.nextToken();
				y = (short) keyTokens.nval;
				BufferedImage sheetTarget = sheetdict.get(sheet);
				BufferedImage entry = sheetTarget.getSubimage(x * spriteSize, y * spriteSize, spriteSize, spriteSize);
				spritedict.put(id, entry);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
		Takes a sprite id and uses the by-id info and sheet information to
		retrieve a specific sprite and return it as a BufferedImage, ready
		for drawing by other classes.
	 */
	public static BufferedImage getSprite(String id) {
		return spritedict.get(id);
	}

	/*
		Takes a short (16 bits long) and separates it into two bytes (8-bit)
		such that the bitwise concatenation of the first and second bytes
		produces the original short.
	 */
}

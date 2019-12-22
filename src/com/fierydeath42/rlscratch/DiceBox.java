package com.fierydeath42.rlscratch;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiceBox {
	private static Pattern p;
	String expressionStr;

	public DiceBox() {
		expressionStr = "(\\d+)?[d|D](\\d+)(([k|K|d|D])(\\d*)([l|L|h|H]))?(([+,-])(\\d+))?";
		p = Pattern.compile(expressionStr);
	}

	public static int roll(String roll) {
		int matchGroup = 0, quantGroup = 1, sizeGroup = 2, ktGroup = 4, nuGroup = 5, hlGroup = 6, pmGroup = 8,
                modGroup = 9;
		Matcher m = p.matcher(roll);
		if (m.find()) {
			// Default: 1
			int quantity = (m.group(quantGroup) == null || m.group(quantGroup).length() == 0 ? 1 :
                    Integer.parseInt(m.group(quantGroup)));
			// No default - present in all matching strings
			int size = Integer.parseInt(m.group(sizeGroup));
			// Default: 0 (equivalent to keep h/l [quantity] or drop h/l 0)
			char keepToss = (m.group(ktGroup) == null ? 0 : m.group(ktGroup).toLowerCase().charAt(0));
			// Default: 1
			int numUsed = (m.group(nuGroup) == null || m.group(nuGroup).length() == 0 ?
                    (keepToss == 0 ? quantity : 1) : Integer.parseInt(m.group(nuGroup)));
			// Default: 0
			char hilo = (m.group(hlGroup) == null ? 0 : m.group(hlGroup).toLowerCase().charAt(0));
			// Default: 0
			char plusminus = (m.group(8) == null ? 0 : m.group(pmGroup).charAt(0));
			// Default: 0
			int modifier = (m.group(modGroup) == null || m.group(modGroup).length() == 0 ? 0 :
                    Integer.parseInt(m.group(modGroup)) * (plusminus == '-' ? -1 : 1));

			int[] rolls = new int[quantity];
			for (int i = 0; i < rolls.length; i++) rolls[i] = (int) Math.floor(Math.random() * size + 1);
			Arrays.sort(rolls);

			int lowerBound = 0;
			int upperBound = rolls.length;
			if (keepToss == 'k' && hilo == 'h') lowerBound = upperBound - numUsed;
			else if (keepToss == 'k' && hilo == 'l') upperBound = numUsed;
			else if (keepToss == 'd' && hilo == 'h') upperBound = upperBound - numUsed;
			else if (keepToss == 'd' && hilo == 'l') lowerBound = numUsed;

			int sum = modifier;
			for (int i = lowerBound; i < upperBound; i++) sum += rolls[i];
			return sum;
		} else {
			return 0;
		}
	}
}
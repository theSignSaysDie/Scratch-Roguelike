package com.fierydeath42.rlscratch;

public class Timekeeper {
	public static long targetTime = -1;
	public static long startTime;
	public static void startTicking() {
		startTime = System.currentTimeMillis();
	}

	public static boolean oneOrZero() {
		return (System.currentTimeMillis() - startTime) % 1000 < 500;
	}

	public static boolean isItTime() {
		return (targetTime <= System.currentTimeMillis());
	}

	public static boolean setTargetTime(long target) {
		targetTime = target;
		return true;
	}

	public static boolean setTargetTimeFromNow(long delta) {
		return setTargetTime(System.currentTimeMillis() + delta);
	}
}

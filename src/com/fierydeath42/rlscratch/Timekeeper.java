package com.fierydeath42.rlscratch;

public class Timekeeper {
	public static long targetTime = -1;

	public static void startTicking() {	}

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

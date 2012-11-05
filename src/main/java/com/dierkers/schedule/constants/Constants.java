package com.dierkers.schedule.constants;

public class Constants {
	/**
	 * Returns true if debug mode is on
	 * @return debug mode status
	 */
	public static boolean isDebug() {
		String debug = System.getenv("DEBUG");
		return debug != null && debug.equals("1");
	}
}

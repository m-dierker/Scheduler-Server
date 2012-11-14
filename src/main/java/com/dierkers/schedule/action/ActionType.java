package com.dierkers.schedule.action;

import java.util.HashMap;

public class ActionType {
	// Debug, prints to error log
	public final static int ERROR_PRINT = 1;

	// Send a Facebook message
	public final static int FACEBOOK_MESSAGE = 2;

	// Send a SMS
	public final static int SMS = 3;

	// Make a call
	public final static int CALL = 4;

	// Send an email (from DreamHost mail servers)
	public final static int MAIL = 5;

	// Check a class's availability
	public final static int CLASS_CHECKER = 6;

	/**
	 * Maps IDs to classes
	 * @return A map of IDs to classes
	 */
	@SuppressWarnings("rawtypes")
	public static HashMap<Integer, Class> getActionMap() {
		HashMap<Integer, Class> actionMap = new HashMap<Integer, Class>();

		actionMap.put(ActionType.ERROR_PRINT, ErrorPrint.class);
		actionMap.put(ActionType.FACEBOOK_MESSAGE, FacebookMessage.class);
		actionMap.put(ActionType.SMS, SMS.class);
		actionMap.put(ActionType.CALL, Call.class);
		actionMap.put(ActionType.MAIL, Mail.class);
		actionMap.put(ActionType.CLASS_CHECKER, ClassChecker.class);

		return actionMap;
	}
}

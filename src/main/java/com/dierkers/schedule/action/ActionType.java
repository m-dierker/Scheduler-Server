package com.dierkers.schedule.action;

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
}

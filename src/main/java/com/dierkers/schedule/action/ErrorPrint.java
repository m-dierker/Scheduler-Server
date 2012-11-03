package com.dierkers.schedule.action;

/**
 * Prints the data to the error log
 * @author Matthew
 * 
 */
public class ErrorPrint extends Action {

	public ErrorPrint(String owner, String data) {
		super(owner, data);
	}

	@Override
	public int getID() {
		return ActionType.ERROR_PRINT;
	}

	@Override
	public void process() {
		System.err.println(getData());
	}

}

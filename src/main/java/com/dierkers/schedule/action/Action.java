package com.dierkers.schedule.action;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * To add a new action, make a class that extends action, and implement the
 * abstract process() method. Add a new ActionType, and add it to the hashmap in
 * the ActionType file. When it's ready to go, go into ScheduleAPIAdd and add it
 * to the switch statement to copy parameters over.
 * @author Matthew
 * 
 */
public abstract class Action {
	private String data;
	private String owner;

	public Action(String owner, String data) {
		this.owner = owner;
		this.data = data;

	}

	public String getData() {
		return this.data;
	}

	public String getOwner() {
		return this.owner;
	}

	public JSONObject getJSONObject() {
		try {
			return new JSONObject(this.getData());
		} catch (JSONException e) {
			System.err.println("Error processing action with ID " + getID());
			e.printStackTrace();
		}
		return null;
	}

	public abstract int getID();

	public abstract void process();
}

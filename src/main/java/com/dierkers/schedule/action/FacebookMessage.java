package com.dierkers.schedule.action;

import org.json.JSONException;
import org.json.JSONObject;

public class FacebookMessage extends Action {

	public FacebookMessage(String owner, String data) {
		super(owner, data);
	}

	@Override
	public int getID() {
		return ActionType.FACEBOOK_MESSAGE;
	}

	@Override
	public void process() {

		JSONObject obj;
		try {
			obj = new JSONObject(this.getData());

			String to = obj.getString("to");
			String msg = obj.getString("msg");

			System.err.println("Sending " + msg + " to " + to);

		} catch (JSONException e) {
			System.err.println("Error sending message on Action ID " + getID());
			e.printStackTrace();
		}

	}
}

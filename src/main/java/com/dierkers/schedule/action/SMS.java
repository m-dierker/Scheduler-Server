package com.dierkers.schedule.action;

import org.json.JSONException;
import org.json.JSONObject;

import com.dierkers.schedule.sms.SMSSender;

public class SMS extends Action {

	public SMS(String owner, String data) {
		super(owner, data);
	}

	@Override
	public int getID() {
		return ActionType.SMS;
	}

	@Override
	public void process() {

		JSONObject obj;
		try {
			obj = new JSONObject(this.getData());

			String to = obj.getString("to");
			String msg = obj.getString("msg");

			SMSSender.sendMsg(to, msg);

		} catch (JSONException e) {
			System.err.println("Error sending message on Action ID " + getID());
			e.printStackTrace();
		}

	}
}

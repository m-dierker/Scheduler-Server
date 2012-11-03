package com.dierkers.schedule.action;

import org.json.JSONException;
import org.json.JSONObject;

import com.dierkers.schedule.tools.http.URLConn;

public class Mail extends Action {

	public Mail(String owner, String data) {
		super(owner, data);
	}

	public int getID() {
		return ActionType.MAIL;
	}

	@Override
	public void process() {

		JSONObject obj;
		try {
			obj = new JSONObject(this.getData());

			String to = obj.getString("to");
			String msg = obj.getString("msg");
			String subj = obj.getString("subj");

			System.err.println("Sending " + msg + " to " + to + " with subj " + subj);

			URLConn.getPage("http://schedule.eatcumtd.com/mail/mail.php?to=" + to + "&subj= " + subj + "&msg=" + msg);

		} catch (JSONException e) {
			System.err.println("Error sending message on Action ID " + getID());
			e.printStackTrace();
		}

	}

}

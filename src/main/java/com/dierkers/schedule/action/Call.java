package com.dierkers.schedule.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class Call extends Action {

	public Call(String owner, String data) {
		super(owner, data);
	}

	@Override
	public int getID() {
		return ActionType.CALL;
	}

	@Override
	public void process() {
		JSONObject obj;
		try {
			obj = new JSONObject(this.getData());

			String to = obj.getString("to");
			String msg = obj.getString("msg").replace(" ", "+");

			Scanner in = new Scanner(new URI("http://api.tropo.com/1.0/sessions?action=create&token=0b79745120c8f0488d10384ff14343e86dfe6d862e2706a5b59e60bc8ab09c37efc3996dd96ddfb6fd7cb35d&to="
					+ to + "&msg=" + msg).toURL().openStream());

			in.close();

		} catch (JSONException e) {
			System.err.println("Error sending message on Action ID " + getID());
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.err.println("Error sending SMS (malformed URL)");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error sending SMS (IOException)");
			e.printStackTrace();
		} catch (URISyntaxException e) {
			System.err.println("Error sending SMS (URI syntax)");
			e.printStackTrace();
		}
	}

}

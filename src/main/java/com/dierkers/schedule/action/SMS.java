package com.dierkers.schedule.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

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

			Scanner in = new Scanner(new URI("http://api.tropo.com/1.0/sessions?action=create&token=18aea8471ca47847abbef040d5cc1c14d1684ac3e094c0a7cd95249c5ff8d722489de69cfdb2af56068fe25a&to="
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

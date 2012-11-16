package com.dierkers.schedule.sms;

import com.dierkers.schedule.tools.http.URLConn;

public class SMSSender {
	/**
	 * Sends a text
	 * @param to phone number to send to
	 * @param msg the message to send
	 * @return the result returned from tropo
	 */
	public static String sendMsg(String to, String msg) {
		msg = msg.replace(" ", "+");
		return URLConn.getPage("http://api.tropo.com/1.0/sessions?action=create&token=18aea8471ca47847abbef040d5cc1c14d1684ac3e094c0a7cd95249c5ff8d722489de69cfdb2af56068fe25a&from=2562746784&to="
				+ to + "&msg=" + msg);
	}
}

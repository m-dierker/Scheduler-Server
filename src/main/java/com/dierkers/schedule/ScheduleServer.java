package com.dierkers.schedule;

import com.dierkers.schedule.db.DBConnector;
import com.dierkers.schedule.http.ScheduleServlet;
import com.dierkers.schedule.http.ScheduleServletRunner;

public class ScheduleServer {

	private DBConnector db;

	public ScheduleServer() {

		db = new DBConnector();

		ScheduleServlet servlet = new ScheduleServlet();
		ScheduleServletRunner servletRunner = new ScheduleServletRunner(servlet);

		// Start up
		servletRunner.start();
	}

	public static void main(String... args) {
		new ScheduleServer();
	}
}

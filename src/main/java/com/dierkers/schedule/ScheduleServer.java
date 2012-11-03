package com.dierkers.schedule;

import com.dierkers.schedule.db.DBConnector;
import com.dierkers.schedule.http.ScheduleServlet;
import com.dierkers.schedule.http.ScheduleServletRunner;
import com.dierkers.schedule.process.ActionProcessor;
import com.dierkers.schedule.process.ActionProcessorRunner;

public class ScheduleServer {

	private DBConnector db;

	public ScheduleServer() {

		db = new DBConnector();

		// Web response stuff
		ScheduleServlet servlet = new ScheduleServlet(this);
		ScheduleServletRunner servletRunner = new ScheduleServletRunner(servlet);

		// Action processing
		ActionProcessor actionProcessor = new ActionProcessor(this);
		ActionProcessorRunner actionProcessorRunner = new ActionProcessorRunner(actionProcessor);

		// Actually start things up
		servletRunner.start();
		actionProcessorRunner.start();
	}

	public DBConnector db() {
		return db;
	}

	public static void main(String... args) {
		new ScheduleServer();
	}
}

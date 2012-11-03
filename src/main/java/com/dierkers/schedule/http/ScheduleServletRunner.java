package com.dierkers.schedule.http;

public class ScheduleServletRunner {

	private ScheduleServlet servlet;

	public ScheduleServletRunner(ScheduleServlet servlet) {
		this.servlet = servlet;
	}

	/**
	 * Start the servlet
	 */
	public void start() {
		new Thread(servlet).start();
	}

}

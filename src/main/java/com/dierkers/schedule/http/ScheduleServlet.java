package com.dierkers.schedule.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ScheduleServlet extends HttpServlet implements Runnable {

	private static final long serialVersionUID = 8172536904188802024L;
	private Server server;

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println("<h1>Hello World</h1>");
	}

	public ScheduleServlet() {
		String port = System.getenv("PORT");
		server = new Server(port != null ? Integer.valueOf(port) : 5000);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		context.addServlet(new ServletHolder(this), "/*");
	}

	public void run() {
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			System.err.println("Error starting the server");
			e.printStackTrace();
		}
	}
}

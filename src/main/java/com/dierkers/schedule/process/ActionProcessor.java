package com.dierkers.schedule.process;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.dierkers.schedule.ScheduleServer;
import com.dierkers.schedule.action.Action;
import com.dierkers.schedule.action.ActionType;

@SuppressWarnings("rawtypes")
public class ActionProcessor implements Runnable {

	private ScheduleServer ss;

	private HashMap<Integer, Class> actionMap;

	public ActionProcessor(ScheduleServer ss) {
		this.ss = ss;

		actionMap = ActionType.getActionMap();
	}

	public void run() {
		while (true) {

			ResultSet rs = ss.db().query("SELECT id, type, owner, time, data, processed FROM schedules WHERE processed=false ORDER BY time ASC LIMIT 5");
			if (rs != null) {
				try {
					while (rs.next()) {

						// If the event is somehow processed already, skip it
						if (rs.getBoolean("processed")) {
							continue;
						}

						long currentTime = System.currentTimeMillis() / 1000;
						long processTime = rs.getInt("time");

						String id = rs.getString("id");
						int type = rs.getInt("type");
						String owner = rs.getString("owner");
						String data = rs.getString("data");

						System.out.println("Checking about action ID " + id + ", and it will be executed in "
								+ (processTime - currentTime));

						if (processTime > currentTime) {
							// The time is still greater than when we should
							// process it
							break;
						}

						// Process the event

						System.err.println("Processing action ID " + id);

						if (!this.actionMap.containsKey(type) || owner == null || owner.equals("null")
								|| owner.trim().equals("")) {
							// Invalid ID type or no owner
							continue;
						}

						Class actionClass = this.getActionForType(type);
						Action action = (Action) actionClass.getConstructors()[0].newInstance(owner, data);

						// If it's constructed correctly, mark it as processed
						// so if this becomes multithreaded, we don't process
						// action multiple times
						markActionProcessed(id);

						// Process the action
						action.process();
					}
				} catch (SQLException e) {
					System.err.println("Error generating the page");
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					System.err.println("Error constructing action (check the constructor)");
					e.printStackTrace();
				} catch (SecurityException e) {
					System.err.println("Error construction action (security fail)");
					e.printStackTrace();
				} catch (InstantiationException e) {
					System.err.println("Error constructing action (instantiation exception)");
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					System.err.println("Error constructing action (illegal access exception)");
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					System.err.println("Error constructing action (invocation target exception)");
					e.printStackTrace();
				}
			}

			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				System.err.println("Action processor interrupted");
				e.printStackTrace();
			}
		}
	}

	public Class getActionForType(int type) {
		return actionMap.get(type);
	}

	public void markActionProcessed(String id) {
		ss.db().update("UPDATE schedules SET processed=true WHERE id='" + id + "'");
	}

}

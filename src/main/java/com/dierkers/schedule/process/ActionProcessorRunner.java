package com.dierkers.schedule.process;


public class ActionProcessorRunner {

	private ActionProcessor processor;

	public ActionProcessorRunner(ActionProcessor processor) {
		this.processor = processor;
	}

	public void start() {
		new Thread(processor).start();
	}
}

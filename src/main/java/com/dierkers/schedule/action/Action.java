package com.dierkers.schedule.action;

public abstract class Action {
	private String data;
	private String owner;

	public Action(String owner, String data) {
		this.owner = owner;
		this.data = data;

	}

	public String getData() {
		return this.data;
	}

	public String getOwner() {
		return this.owner;
	}

	public abstract int getID();

	public abstract void process();
}

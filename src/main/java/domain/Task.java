package main.java.domain;

import java.util.Calendar;
import java.util.List;

public class Task {
	
	private int id;
	private String description;
	private boolean inExecution;
	private Calendar dateFinish;
	private List<FeedstockDetail> feedstocks;
	private Worker worker;
	

	
	public Task() {
	}

	public Task(int id, String description, boolean inExecution, Calendar dateFinish, List<FeedstockDetail> feedstocks,
			Worker worker) {
		this.id = id;
		this.description = description;
		this.inExecution = inExecution;
		this.dateFinish = dateFinish;
		this.feedstocks = feedstocks;
		this.worker = worker;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Calendar dateFinish) {
		this.dateFinish = dateFinish;
	}

	public List<FeedstockDetail> getFeedstocks() {
		return feedstocks;
	}

	public void setFeedstocks(List<FeedstockDetail> feedstocks) {
		this.feedstocks = feedstocks;
	}

	public boolean isInExecution() {
		return inExecution;
	}

	public void setInExecution(boolean inExecution) {
		this.inExecution = inExecution;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	
	
	
	
	

}

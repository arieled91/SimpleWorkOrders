package main.java.domain.business;

import java.util.Calendar;
import java.util.List;

import main.java.common.Utils;
import main.java.data.dao.TaskDao;

public class Task {
	
	private int id;
	private String description;
	private boolean inExecution;
	private Calendar dateFinish;
	private FeedstockDetail feedstock;
	private Worker worker;
	

	
	public Task() {
	}

	public Task(int id, String description, boolean inExecution, Calendar dateFinish, FeedstockDetail feedstock,
			Worker worker) {
		this.id = id;
		this.description = description;
		this.inExecution = inExecution;
		this.dateFinish = dateFinish;
		this.feedstock = feedstock;
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
	
	public String getDateFinishString() {
		return Utils.toString(dateFinish);
	}

	public void setDateFinish(Calendar dateFinish) {
		this.dateFinish = dateFinish;
	}
	
	public void setDateFinish(String dateFinish) {
		this.dateFinish = Utils.fromString(dateFinish);
	}

	public FeedstockDetail getFeedstock() {
		return feedstock;
	}

	public void setFeedstock(FeedstockDetail feedstock) {
		this.feedstock = feedstock;
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
	
	public static Task find(int id) {
		TaskDao dao = new TaskDao();
		return dao.find(id+"");
	}
	
	public static List<Task> list(WorkOrderId id){
		TaskDao dao = new TaskDao(id);
		return dao.getList();
	}
	
	public static List<Task> list(int workOrderId, int workOrderYear){
		return list(new WorkOrderId(workOrderId, workOrderYear));
	}
	
	
	

}

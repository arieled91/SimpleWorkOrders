package main.java.data.dao;

import main.java.common.Utils;
import main.java.data.util.Database;
import main.java.data.util.Filer;
import main.java.domain.business.Feedstock;
import main.java.domain.business.FeedstockDetail;
import main.java.domain.business.Task;
import main.java.domain.business.WorkOrderId;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao extends GenericDao<Task>{
	
	private static final String TASK_PATH = "tasks/";
	
	private WorkOrderId workOrderId;
	
	public TaskDao(){}
	
	public TaskDao(WorkOrderId workOrderId){
		this.workOrderId=workOrderId;
	}
	
	private static final String TABLE_NAME = "ASSIGNED_TASK";
	
	@Override
	public String selectAllQuery() {
		if(workOrderId!=null && workOrderId.getId()>0) return super.selectAllQuery()+" WHERE WORK_ORDER_ID = "+workOrderId.getId() + " AND WORK_ORDER_YEAR = "+workOrderId.getYear();
		return super.selectAllQuery();
	}
	

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public Task build(ResultSet result) throws SQLException {
		Task t = new Task();
		t.setId(result.getInt("ID"));
		t.setDescription(result.getString("DESCRIPTION"));
		t.setInExecution(result.getBoolean("IN_EXECUTION"));
		t.setDateFinish(result.getString("DATE_FINISH"));
		Feedstock feedstock = Feedstock.find(result.getInt("FEEDSTOCK_ID"));
		t.setFeedstock(new FeedstockDetail(feedstock, result.getDouble("AMOUNT")));
		
		return t;
	}

	@Override
	public void insert(Task task) {
		final String insert = "INSERT INTO "+TABLE_NAME +" (DESCRIPTION, WORK_ORDER_ID, WORK_ORDER_YEAR) VALUES('"
						+task.getDescription()+"', "+workOrderId.getId()+" ,"+workOrderId.getYear()
						+")";
		
		Database.get().executeUpdate(insert);
	}
	
	public void insert(Iterable<Task> tasks){
		for (Task task : tasks) {
			insert(task);
		}
	}

	@Override
	public void update(Task task) {
		if(task.getId()<=0) {
			System.err.println("Invalid task id");
			return;
		}
		
		String update = "UPDATE "+TABLE_NAME+" SET"+
							 " DESCRIPTION = '"+task.getDescription()+
							"', IN_EXECUTION = "+task.isInExecution()+
							", DATE_FINISH = '"+task.getDateFinishString()+
						"' WHERE ID = "+task.getId();
				
		Database.get().executeUpdate(update);
	}

	public static Task build(String text) {
		Task task = new Task();
		String[] arr = text.split(";");
		String description = arr[0];
		int feedstockId = 0;
		int amount = 0;
		if(arr.length>1) {
			feedstockId = Utils.parseInt(arr[1]);
			amount = Utils.parseInt(arr[2]);
		}
		task.setDescription(description);
		if(feedstockId>0 && amount>0){
			Feedstock feedstock = Feedstock.find(feedstockId);
			task.setFeedstock(new FeedstockDetail(feedstock, amount));
		}
		return task;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public static List<Task> getTasks(int productId){
		try {
			List<String> rawtasks = Filer.read(TASK_PATH+productId);
			List<Task> tasks = new ArrayList<>();
			if(!Utils.isEmpty(rawtasks))
			for (String line : rawtasks) {
				if(!Utils.isEmpty(line))
					tasks.add(TaskDao.build(line));
			}
			return tasks;
			
		} catch (IOException e) {
			System.out.println("Error, maybe the product id="+productId+" doesn't have a task file");
			return null;
		}
	}

}

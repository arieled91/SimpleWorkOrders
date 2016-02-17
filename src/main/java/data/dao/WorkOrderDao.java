package main.java.data.dao;

import main.java.common.Utils;
import main.java.data.util.Database;
import main.java.domain.business.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WorkOrderDao extends GenericDao<WorkOrder>{
	
	private static final String TABLE_NAME = "WORK_ORDER";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public String findQuery(String id){
		WorkOrderId workOrderId = WorkOrderId.fromString(id);
		return super.findQuery(workOrderId.getId()+" AND YEAR = "+workOrderId.getYear());
	}

	@Override
	public WorkOrder build(ResultSet result) throws SQLException {
		WorkOrder w = new WorkOrder();
        List<Task> tasks = Task.list(w.getId());
		w.setId(new WorkOrderId(result.getInt("ID"),result.getInt("YEAR")));
		w.setDateCreate(result.getString("DATE_CREATE"));
		w.setDateFinish(result.getString("DATE_FINISH"));
		w.setProduct(Product.find(result.getInt("PRODUCT_ID")));
		w.setAmount(result.getDouble("AMOUNT"));
		w.setUrgent(result.getBoolean("URGENT"));
		w.setStatus(result.getString("STATUS"));
		if(!Utils.isEmpty(tasks)) w.setTasks(tasks);
		w.setWorker(Worker.find(result.getInt("WORKER_ID")));
		w.setDescription(result.getString("DESCRIPTION"));
		
		return w;
	}

	@Override
	public void insert(WorkOrder workOrder) {
		final String insert = "INSERT INTO "+TABLE_NAME +" (ID, YEAR, PRODUCT_ID, AMOUNT, DESCRIPTION, URGENT, STATUS, DATE_CREATE, DATE_FINISH, WORKER_ID) VALUES("
				+workOrder.getId().getId()+", "+workOrder.getId().getYear()+", "+workOrder.getProduct().getId()+", "+workOrder.getAmount()+", '"+ workOrder.getDescription()+"', "+workOrder.isUrgent()+", '"+
				workOrder.getStatus()+"', '"+workOrder.getDateCreateString()+"', '"+workOrder.getDateFinishString()+"', "+workOrder.getWorker().getId()
				+")";

		Database.get().executeUpdate(insert);

        workOrder.setTasks(TaskDao.getTasks(workOrder.getProduct().getId()));
		TaskDao taskDao = new TaskDao(workOrder.getId());
		taskDao.insert(workOrder.getTasks());
	}

	@Override
	public void update(WorkOrder workOrder) {
		if(workOrder.getId().getId()<=0 && workOrder.getId().getYear()<=0) {
			System.err.println("Invalid task id");
			return;
		}
		
		String update = "UPDATE "+TABLE_NAME+" SET"+
							 " PRODUCT_ID = "+workOrder.getProduct().getId()+
							", AMOUNT = "+workOrder.getAmount()+
							", DESCRIPTION = '"+workOrder.getDescription()+
							"', URGENT = "+workOrder.isUrgent()+
							", DATE_FINISH = '"+workOrder.getDateFinishString()+
							"', STATUS = '"+workOrder.getStatus()+
							"' , WORKER_ID = "+workOrder.getWorker().getId()+
						" WHERE ID = "+workOrder.getId().getId()+" AND YEAR = "+workOrder.getId().getYear();

        Database.get().executeUpdate(update);


        for (Task task : workOrder.getTasks()) {
            task.update();
        }
    }

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	

}

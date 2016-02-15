package main.java.domain.business;

import java.util.Calendar;

public class WorkOrderId {
	
	private int id;
	private int year=Calendar.getInstance().get(Calendar.YEAR);
	
	
	public WorkOrderId() {
	}

	public WorkOrderId(int id, int year) {
		this.id = id;
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return id +"/"+ year;
	}
	
	public static WorkOrderId fromString(String id){
		String[] ids = id.split("/");
		return new WorkOrderId(Integer.parseInt(ids[0]),Integer.parseInt(ids[1]));
	}
	
	
	
	
	

}

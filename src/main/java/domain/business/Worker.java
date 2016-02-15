package main.java.domain.business;

import java.util.List;

import main.java.data.dao.WorkerDao;

public class Worker {

	private int id;
	private String name;
	
	
	
	public Worker() {
	}

	public Worker(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static Worker find(int id) {
		WorkerDao dao = new WorkerDao();
		return dao.find(id+"");
	}
	
	public static List<Worker> list(){
		WorkerDao dao = new WorkerDao();
		return dao.getList();
	}
	
	@Override
	public String toString() {
		return id + " - " + name;
	}
	
}

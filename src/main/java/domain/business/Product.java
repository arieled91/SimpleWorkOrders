package main.java.domain.business;

import java.util.List;

import main.java.data.dao.ProductDao;

public class Product{
	
	private int id;
	private String name;
	
	public Product() {
	}

	public Product(int id, String name) {
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

	public static Product find(int id) {
		ProductDao dao = new ProductDao();
		return dao.find(id+"");
	}
	
	public static List<Product> list(){
		ProductDao dao = new ProductDao();
		return dao.getList();
	}

	@Override
	public String toString() {
		return id + " - " + name;
	}
	
	
	

}

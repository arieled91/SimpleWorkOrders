package main.java.domain.business;

public class Feedstock {
	
	private int id;
	private String description;
	
	
	public Feedstock() {
	}

	public Feedstock(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}

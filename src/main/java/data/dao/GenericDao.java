package main.java.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.data.util.Database;


public abstract class GenericDao<T> {
	
	public List<T> getList(){
		ResultSet result;
		try {
			result = Database.get().executeQuery(selectAllQuery());
			List<T> list = new ArrayList<>();
			while(result.next()){
				list.add(build(result));
			}
			return list;
		} catch (SQLException e) {
			System.err.println("Error");
			return null;
		}		
	}
	
	public T find(String id){
		ResultSet result;
		try{
			result = Database.get().executeQuery(findQuery(id));
			return build(result);
		}catch (SQLException e) {
			System.out.println("Id not found");
			return null;
		}	
	}
	
	public String selectAllQuery(){
		return "SELECT * FROM "+getTableName();
	}
	
	public String findQuery(String id){
		return "SELECT * FROM "+getTableName()+" WHERE ID = "+id;
	}
	
	public abstract String getTableName();
	public abstract T build(ResultSet result) throws SQLException ;
	public abstract void insert(T obj);
	public abstract void update(T obj);
	public abstract void delete(int id);

}

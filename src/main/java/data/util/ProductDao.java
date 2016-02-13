package main.java.data.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.data.dao.GenericDao;
import main.java.domain.Product;

public class ProductDao extends GenericDao<Product>{

	@Override
	public Product build(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectAllQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Product obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	
	
}

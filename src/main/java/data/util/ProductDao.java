package main.java.data.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.data.dao.GenericDao;
import main.java.domain.Product;

public class ProductDao extends GenericDao<Product>{

	@Override
	public Product build(ResultSet result) throws SQLException {
		Product p = new Product();
		p.setId(result.getInt("ID"));
		p.setName(result.getString("DESCRIPTION"));
		return p;
	}


	@Override
	public String selectAllQuery() {
		return "SELECT * FROM PRODUCTS";
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

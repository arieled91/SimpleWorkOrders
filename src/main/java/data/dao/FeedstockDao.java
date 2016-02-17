package main.java.data.dao;

import main.java.data.util.Database;
import main.java.domain.business.Feedstock;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class FeedstockDao extends GenericDao<Feedstock>{
	
	public FeedstockDao(){}
	
	private static final String TABLE_NAME = "FEEDSTOCK";
	private static final String REQUEST_TABLE_NAME = "FEEDSTOCK_REQUEST";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public Feedstock build(ResultSet result) throws SQLException {
		Feedstock f = new Feedstock();
		f.setId(result.getInt("ID"));
		f.setDescription(result.getString("DESCRIPTION"));
		return f;
	}

	@Override
	public void insert(Feedstock obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Feedstock obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

    public static double stockRequest(int id){
        try{
            CallableStatement call = Database.get().prepareCall("{call stock_request(?,?)}");
            call.setInt(1, id);
            call.registerOutParameter(2, Types.DECIMAL);
            call.execute();
            return call.getDouble(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void stockRequest(int id, double amount) {
        String insertRequest = "INSERT INTO " + REQUEST_TABLE_NAME + " (FEEDSTOCK_ID, AMOUNT) VALUES(" + id + ", " + amount + ")";
        Database.get().executeUpdate(insertRequest);
    }

    }

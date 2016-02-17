package main.java.domain.business;

import main.java.data.dao.FeedstockDao;

import java.util.List;

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

    public static Feedstock find(int id) {
		FeedstockDao dao = new FeedstockDao();
		return dao.find(id+"");
	}
	
	public static List<Feedstock> list(){
		FeedstockDao dao = new FeedstockDao();
		return dao.getList();
	}

    @Override
    public String toString() {
        return id +" - "+ description;
    }

    public static double requestStock(int feedstockId){
        return FeedstockDao.stockRequest(feedstockId);
    }

    public static void requestStock(int feedstockId, double amount){
        FeedstockDao.stockRequest(feedstockId, amount);
    }


}

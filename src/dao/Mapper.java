package dao;


public abstract class Mapper {
	private static String database = "tweket";
	
	public String getDatabase() {
		return Mapper.database;
	}
}


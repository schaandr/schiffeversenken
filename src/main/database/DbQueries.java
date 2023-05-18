package main.database;

import java.sql.PreparedStatement;
import java.util.HashMap;

public class DbQueries {

	private HashMap<String, PreparedStatement> queries= null;
	
	
	public void addQuery(String name, PreparedStatement stmt) {
		this.queries.put(name, stmt);
	}
	
	public void deleteQuery(String name) {
		this.queries.remove(name);
	}
	
	public PreparedStatement[] getAllQueries() {
		PreparedStatement[] tmp = null;
		this.queries.entrySet().toArray(tmp);
		return tmp;
	}
	
	

	
}

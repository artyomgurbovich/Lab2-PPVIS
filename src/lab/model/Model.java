package lab.model;

import java.util.HashMap;
import java.util.Map;

public class Model {
	
	private Map<Student, Passes> records;
	
	public Model() {
		this.records = new HashMap<Student, Passes>();
	}
	
	public Map<Student, Passes> getRecords() {
		return records;
	}
	
	public void setRecords(Map<Student, Passes> records) {
		this.records = records;
	}
}

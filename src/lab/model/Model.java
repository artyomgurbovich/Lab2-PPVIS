package lab.model;

import java.util.ArrayList;

public class Model {
	
	private ArrayList<Student> students;
	
	public Model() {
		this.students = new ArrayList<Student>();
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
}

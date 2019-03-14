package lab.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import lab.model.*;

public class Controller {
	
	private Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public ArrayList<Student> getStudents() {
		return model.getStudents();
	}
	
	public void addStudent(String fullName, int group, int diseasePasses, int otherReasonPasses, int withoutReasonPasses) {
		Student student = new Student(fullName, group, diseasePasses, otherReasonPasses, withoutReasonPasses);
		ArrayList<Student> students = model.getStudents();
		students.add(student);
		model.setStudents(students);
	}
	
	private String getSurname(Student student) {
		return student.getFullName().trim().split("\\s+")[0];
	}
	
	public ArrayList<Student> findByGroup(String surname, int group) {
		ArrayList<Student> result = new ArrayList<Student>();
		for (int i = 0; i < getStudents().size(); i++) {
			if (surname.equals(getSurname(getStudents().get(i))) || (getStudents().get(i).getGroup() == group)) {
				result.add(getStudents().get(i));
			}
		}
		return result;
	}
	
	public ArrayList<Student> findByPassType(String surname, int passType) {
		ArrayList<Student> result = new ArrayList<Student>();
		for (int i = 0; i < getStudents().size(); i++) {
			if (surname.equals(getSurname(getStudents().get(i)))) {
				result.add(getStudents().get(i));
			} else if (passType == 0 && (getStudents().get(i).getDiseasePasses() > 0)) {
				result.add(getStudents().get(i));
			} else if (passType == 1 && (getStudents().get(i).getOtherReasonPasses() > 0)) {
				result.add(getStudents().get(i));
			} else if (passType == 2 && (getStudents().get(i).getWithoutReasonPasses() > 0)) {
				result.add(getStudents().get(i));
			} else if (passType == 3 && (getStudents().get(i).getAllPasses() > 0)) {
				result.add(getStudents().get(i));
			}
		}
		return result;
	}
	
	public ArrayList<Student> findByPassNumber(String surname, int passType, int min, int max) {
		ArrayList<Student> result = new ArrayList<Student>();
		for (int i = 0; i < getStudents().size(); i++) {
			if (surname.equals(getSurname(getStudents().get(i)))) {
				result.add(getStudents().get(i));
			} else if (passType == 0 && (getStudents().get(i).getDiseasePasses() >= min) && (getStudents().get(i).getDiseasePasses() <= max)) {
				result.add(getStudents().get(i));
			} else if (passType == 1 && (getStudents().get(i).getOtherReasonPasses() >= min) && (getStudents().get(i).getOtherReasonPasses() <= max)) {
				result.add(getStudents().get(i));
			} else if (passType == 2 && (getStudents().get(i).getWithoutReasonPasses() >= min) && (getStudents().get(i).getOtherReasonPasses() <= max)) {
				result.add(getStudents().get(i));
			} else if (passType == 3 && (getStudents().get(i).getAllPasses() >= min) && (getStudents().get(i).getOtherReasonPasses() <= max)) {
				result.add(getStudents().get(i));
			}
		}
		return result;
	}
	
	public int RemoveSelected(ArrayList<Student> studentsToRemove) {
		int size = model.getStudents().size();
		model.getStudents().removeAll(studentsToRemove);
		return size - model.getStudents().size();
	}
	
	public void clearStudents() {
		model = new Model();
	}
	
	public void LoadStudents() throws SAXException, IOException, ParserConfigurationException {
		JFileChooser fileload = new JFileChooser();
		int ret = fileload.showDialog(null, "Open file");                
		if (ret == JFileChooser.APPROVE_OPTION) {
		    File file = fileload.getSelectedFile();
			SAXReader reader = new SAXReader();
			model.setStudents(reader.Read(file));
		}
	}
	
	public void SaveStudents() throws ParserConfigurationException, TransformerException {
		JFileChooser filesave = new JFileChooser();
		filesave.setDialogTitle("Specify a file to save");   
		int ret = filesave.showSaveDialog(null);               
		if (ret == JFileChooser.APPROVE_OPTION) {
		    File file = filesave.getSelectedFile();
			DOMWriter writer = new DOMWriter();
			writer.Write(file, getStudents());
		}
	}
}

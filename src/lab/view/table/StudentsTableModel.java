package lab.view.table;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import lab.model.Student;

@SuppressWarnings("serial")
public class StudentsTableModel extends AbstractTableModel {
	
	private ArrayList<Student> students;
	
    StudentsTableModel(ArrayList<Student> students) {
        super();
        this.students = students;
    }
    
    public void setStudents(ArrayList<Student> students) {
    	this.students = students;
    }
    
    @Override
    public int getRowCount() {
        return students.size();
    }
    @Override
    public int getColumnCount() {
        return 6;
    }
    
    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "Full name";
                break;
            case 1:
                result = "Group";
                break;
            case 2:
                result = "Disease passes";
                break;
            case 3:
                result = "Other reason passes";
                break;
            case 4:
                result = "Without reason passes";
                break;
            case 5:
                result = "All passes";
                break;
        }
        return result;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return students.get(r).getFullName();
            case 1:
                return students.get(r).getGroup();
            case 2:
                return students.get(r).getDiseasePasses();
            case 3:
                return students.get(r).getOtherReasonPasses();
            case 4:
                return students.get(r).getWithoutReasonPasses();
            case 5:
                return students.get(r).getAllPasses();
            default:
                return "";
        }
    }
}
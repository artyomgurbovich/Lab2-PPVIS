package lab.view.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import lab.model.Passes;
import lab.model.Student;

@SuppressWarnings("serial")
public class RecordsTableModel extends AbstractTableModel {
	
	private List<Student> students;
	private List<Passes> passes;
	
	private final String COLUMN_0 = "First name";
	private final String COLUMN_1 = "Middle name";
	private final String COLUMN_2 = "Last name";
	private final String COLUMN_3 = "Group";
	private final String COLUMN_4 = "Disease";
	private final String COLUMN_5 = "Other reason";
	private final String COLUMN_6 = "Without reason";
	private final String COLUMN_7 = "All";
	
    RecordsTableModel(Map<Student, Passes> records) {
        super();
        this.students = new ArrayList<Student>(records.keySet());
        this.passes = new ArrayList<Passes>(records.values());
    }
    
    public void setRecords(Map<Student, Passes> records) {
        this.students = new ArrayList<Student>(records.keySet());
        this.passes = new ArrayList<Passes>(records.values());
    }
    
    @Override
    public int getRowCount() {
        return students.size();
    }
    
    @Override
    public int getColumnCount() {
        return 8;
    }
    
    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = COLUMN_0;
                break;
            case 1:
                result = COLUMN_1;
                break;
            case 2:
                result = COLUMN_2;
                break;
            case 3:
                result = COLUMN_3;
                break;
            case 4:
                result = COLUMN_4;
                break;
            case 5:
                result = COLUMN_5;
                break;
            case 6:
                result = COLUMN_6;
                break;
            case 7:
                result = COLUMN_7;
                break;
        }
        return result;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return students.get(r).getFirstName();
            case 1:
            	return students.get(r).getMiddleName();
            case 2:
            	return students.get(r).getLastName();
            case 3:
            	return students.get(r).getGroup();
            case 4:
                return passes.get(r).getDiseasePasses();
            case 5:
            	return passes.get(r).getOtherReasonPasses();
            case 6:
            	return passes.get(r).getWithoutReasonPasses();
            case 7:
            	return passes.get(r).getAllPasses();
            default:
                return "";
        }
    }
}
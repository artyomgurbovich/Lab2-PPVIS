package lab.controller;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import lab.model.*;
import lab.view.window.*;
import lab4.MyFileChooser;
//import javax.swing.JFileChooser;

public class Controller {
	
	private Model model;
	private MainWindow mainWindow;
	private FileManager fileManager;
	
	public Controller(Model model) {
		this.model = model;
		fileManager = new FileManager();
	}
	
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public Map<Student, Passes> getRecords() {
		return model.getRecords();
	}
	
	public void addRecord(Student student, Passes passes) {
		Map<Student, Passes> records = model.getRecords();
		records.put(student, passes);
		model.setRecords(records);
	}
	
	public void clearRecords() {
		model = new Model();
	}
	
	public int removeSelected(Map<Student, Passes> recordsToRemove) {
		int size = model.getRecords().size();
		model.getRecords().keySet().removeAll(recordsToRemove.keySet());
		return size - model.getRecords().size();
	}
	
	public Map<Student, Passes> findByGroup(String lastName, String group) {
		Map<Student, Passes> result = new HashMap<Student, Passes>();
		for (Map.Entry<Student, Passes> record: getRecords().entrySet()) {
			Student key = record.getKey();
			Passes value = record.getValue();
			if (lastName.equals(key.getLastName())) {
				result.put(key, value);
			} else if (group.equals(key.getGroup())) {
				result.put(key, value);
			}
		}
		return result;
	}
	
	public Map<Student, Passes> findByPassType(String lastName, int passType) {
		Map<Student, Passes> result = new HashMap<Student, Passes>();
		for (Map.Entry<Student, Passes> record: getRecords().entrySet()) {
			Student key = record.getKey();
			Passes value = record.getValue();
			if (lastName.equals(key.getLastName())) {
				result.put(key, value);
			} else if (passType == 0 && (value.getDiseasePasses() > 0)) {
				result.put(key, value);
			} else if (passType == 1 && (value.getOtherReasonPasses() > 0)) {
				result.put(key, value);
			} else if (passType == 2 && (value.getWithoutReasonPasses() > 0)) {
				result.put(key, value);
			} else if (passType == 3 && (value.getAllPasses() > 0)) {
				result.put(key, value);
			}
		}
		return result;
	}
	
	public Map<Student, Passes> findByPassNumber(String lastName, int passType, int min, int max) {
		Map<Student, Passes> result = new HashMap<Student, Passes>();
		for (Map.Entry<Student, Passes> record: getRecords().entrySet()) {
			Student key = record.getKey();
			Passes value = record.getValue();
			if (lastName.equals(key.getLastName())) {
				result.put(key, value);
			} else if (passType == 0 && (value.getDiseasePasses() >= min) && (value.getDiseasePasses() <= max)) {
				result.put(key, value);
			} else if (passType == 1 && (value.getOtherReasonPasses() >= min) && (value.getOtherReasonPasses() <= max)) {
				result.put(key, value);
			} else if (passType == 2 && (value.getWithoutReasonPasses() >= min) && (value.getWithoutReasonPasses() <= max)) {
				result.put(key, value);
			} else if (passType == 3 && (value.getAllPasses() >= min) && (value.getAllPasses() <= max)) {
				result.put(key, value);
			}
		}
		return result;
	}
	
	public void loadRecords() throws SAXException, IOException, ParserConfigurationException {
		//JFileChooser fileload = new JFileChooser();
		//int ret = fileload.showDialog(null, "Open file");                
		//if (ret == JFileChooser.APPROVE_OPTION) {
		//    File file = fileload.getSelectedFile();
		//	  model.setRecords(fileManager.read(file));
		//}
		
		MyFileChooser fileload = new MyFileChooser();
		int ret = fileload.showLoadDialog();
		if (ret == MyFileChooser.APPROVE_OPTION) {
			File file = fileload.getSelectedFile();
			model.setRecords(fileManager.read(file));
		}
	}
	
	public void saveStudents() throws ParserConfigurationException, TransformerException {
		//JFileChooser filesave = new JFileChooser();
		//filesave.setDialogTitle("Save file");
		//int ret = filesave.showSaveDialog(null);               
		//if (ret == JFileChooser.APPROVE_OPTION) {
		//    File file = filesave.getSelectedFile();
		//    fileManager.write(file, getRecords());
		//}
		
		MyFileChooser filesave = new MyFileChooser();
		int ret = filesave.showSaveDialog();
		if (ret == MyFileChooser.APPROVE_OPTION) {
			File file = filesave.getSelectedFile();
			fileManager.write(file, getRecords());
		}
	}
	
	public ActionListener addAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new AddWindow(mainWindow);
        }
    };
    
    public ActionListener findByGroupAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new FindByGroupWindow(mainWindow, false);
        }
    };
    
    public ActionListener findByPassTypeAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new FindByPassTypeWindow(mainWindow, false);
        }
    };
    
    public ActionListener findByPassNumberAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new FindByPassNumberWindow(mainWindow, false);
        }
    };
    
    public ActionListener removeByGroupAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new FindByGroupWindow(mainWindow, true);
        }
    };
    
    public ActionListener removeByPassTypeAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new FindByPassTypeWindow(mainWindow, true);
        }
    };
    
    public ActionListener removeByPassNumberAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new FindByPassNumberWindow(mainWindow, true);
        }
    };
}

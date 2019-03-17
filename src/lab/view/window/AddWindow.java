package lab.view.window;

import java.awt.event.*;
import javax.swing.*;
import lab.model.Passes;
import lab.model.Student;

public class AddWindow {
	
	private final int WINDOW_SIZE_X = 300;
	private final int WINDOW_SIZE_Y = 350;
	
	public AddWindow(MainWindow mainWindow) {
		
		JFrame frame = new JFrame("Add Record");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JLabel firstNameLabel = new JLabel("First name");
		JLabel middleNameLabel = new JLabel("Middle name");
		JLabel lastNameLabel = new JLabel("Last name");
		JLabel groupLabel = new JLabel("Group");
		JLabel diseasePassesLabel = new JLabel("Disease passes");
		JLabel otherReasonPassesLabel = new JLabel("Other reason passes");
		JLabel withoutReasonPassesLabel = new JLabel("Without reason passes");
		
		JTextField firstNameField = new JTextField();
		JTextField middleNameField = new JTextField();
		JTextField lastNameField = new JTextField();
		JTextField groupField = new JTextField();
		JTextField diseasePassesField = new JTextField();
		JTextField otherReasonPassesField = new JTextField();
		JTextField withoutReasonPassesField = new JTextField();
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
            	String firstName = firstNameField.getText().trim();
            	String middleName = middleNameField.getText().trim();
            	String lastName = middleNameField.getText().trim();
            	String group = groupField.getText().trim();
            	String diseasePasses = diseasePassesField.getText().trim();
            	String otherReason = otherReasonPassesField.getText().trim();
            	String withoutReason = withoutReasonPassesField.getText().trim();
            	if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() ||
            		    group.isEmpty() || diseasePasses.isEmpty() || otherReason.isEmpty() || withoutReason.isEmpty()) {
            		JOptionPane.showMessageDialog(new JFrame(), "Empty field error!", "Error", JOptionPane.ERROR_MESSAGE);
            	} else {
                	Student student = new Student(firstName, middleName, lastName, group);
                	Passes passes = new Passes(Integer.parseInt(diseasePasses), Integer.parseInt(otherReason), Integer.parseInt(withoutReason));
                	mainWindow.getController().addRecord(student, passes);
                	mainWindow.getRecordsTable().update(mainWindow.getController().getRecords());
                	frame.dispose();
            	}
            }
        });
		
		mainPanel.add(firstNameLabel);
		mainPanel.add(firstNameField);
		mainPanel.add(middleNameLabel);
		mainPanel.add(middleNameField);
		mainPanel.add(lastNameLabel);
		mainPanel.add(lastNameField);
		mainPanel.add(groupLabel);
		mainPanel.add(groupField);
		mainPanel.add(diseasePassesLabel);
		mainPanel.add(diseasePassesField);
		mainPanel.add(otherReasonPassesLabel);
		mainPanel.add(otherReasonPassesField);
		mainPanel.add(withoutReasonPassesLabel);
		mainPanel.add(withoutReasonPassesField);
		mainPanel.add(addButton);
		frame.add(mainPanel);
		frame.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
		frame.setVisible(true);
	}
}

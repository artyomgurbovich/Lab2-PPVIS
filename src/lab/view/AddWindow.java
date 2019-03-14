package lab.view;

import java.awt.event.*;
import javax.swing.*;
import lab.controller.Controller;
import lab.view.table.StudentsTable;

public class AddWindow {
	public AddWindow(Controller controller, StudentsTable studentsTable) {
		
		JFrame frame = new JFrame("Add Student");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JLabel fullnameLabel = new JLabel("Fullname");
		JTextField fullnameField = new JTextField();
		JLabel groupLabel = new JLabel("Group");
		JTextField groupField = new JTextField();
		JLabel diseasePassesLabel = new JLabel("Disease passes");
		JTextField diseasePassesField = new JTextField();
		JLabel otherReasonPassesLabel = new JLabel("Other reason passes");
		JTextField otherReasonPassesField = new JTextField();
		JLabel withoutReasonPassesLabel = new JLabel("Without reason passes");
		JTextField withoutReasonPassesField = new JTextField();
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
            	controller.addStudent(fullnameField.getText(),
            						  Integer.parseInt(groupField.getText()),
            					      Integer.parseInt(diseasePassesField.getText()),
            						  Integer.parseInt(otherReasonPassesField.getText()),
            						  Integer.parseInt(withoutReasonPassesField.getText()));
            	studentsTable.updateTableData(controller.getStudents());
            	frame.dispose();
            }
        });
		
		mainPanel.add(fullnameLabel);
		mainPanel.add(fullnameField);
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
		frame.setSize(300, 250);
		frame.setVisible(true);
	}
}

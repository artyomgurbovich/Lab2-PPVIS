package lab.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import lab.controller.Controller;
import lab.view.table.StudentsTable;

public class FindByGroupWindow {
	public FindByGroupWindow(Controller controller) {
		
		JFrame frame = new JFrame("Add Student");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		StudentsTable studentsTable = new StudentsTable(leftPanel, controller.getStudents());
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		JLabel surnameLabel = new JLabel("Surname");
		JTextField surnameField = new JTextField();
		surnameField.setMaximumSize(new Dimension(200, 20));
		JLabel groupLabel = new JLabel("Group");
		JTextField groupField = new JTextField();
		groupField.setMaximumSize(new Dimension(200, 20));
		
		JButton findButton = new JButton("Find");
		findButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
            	studentsTable.updateTableData(controller.findByGroup(surnameField.getText(), Integer.parseInt(groupField.getText())));
            }
        });
		
		rightPanel.add(surnameLabel);
		rightPanel.add(surnameField);
		rightPanel.add(groupLabel);
		rightPanel.add(groupField);
		rightPanel.add(findButton);
		
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		frame.add(mainPanel);
		frame.setSize(1300, 400);
        frame.setVisible(true);
	}
}

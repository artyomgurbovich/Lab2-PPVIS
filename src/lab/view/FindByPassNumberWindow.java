package lab.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import lab.controller.Controller;
import lab.view.table.StudentsTable;

public class FindByPassNumberWindow {
	public FindByPassNumberWindow(Controller controller) {
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

		String[] items = {"disease", "other reason", "without reason", "all"};
		JComboBox<String> comboBox = new JComboBox<String>(items);
		comboBox.setMaximumSize(new Dimension(200, 20));
		
		JLabel minLabel = new JLabel("Min");
		JTextField minField = new JTextField();
		minField.setMaximumSize(new Dimension(200, 20));
		
		JLabel maxLabel = new JLabel("Max");
		JTextField maxField = new JTextField();
		maxField.setMaximumSize(new Dimension(200, 20));
		
		JButton findButton = new JButton("Find");
		findButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
            	System.out.print(comboBox.getSelectedIndex());
            	studentsTable.updateTableData(controller.findByPassNumber(surnameField.getText(),
            			                      comboBox.getSelectedIndex(),
            			                      Integer.parseInt(minField.getText()),
            			                      Integer.parseInt(maxField.getText())));
            }
        });
		
		rightPanel.add(surnameLabel);
		rightPanel.add(surnameField);
		rightPanel.add(comboBox);
		rightPanel.add(minLabel);
		rightPanel.add(minField);
		rightPanel.add(maxLabel);
		rightPanel.add(maxField);
		rightPanel.add(findButton);
		
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		frame.add(mainPanel);
		frame.setSize(1300, 400);
        frame.setVisible(true);
	}
}
